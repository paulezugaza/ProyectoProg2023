package es.deusto.ingenieria.prog3.UDExplore.io;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;



import es.deusto.ingenieria.prog3.UDExplore.domain.Administrador;
import es.deusto.ingenieria.prog3.UDExplore.domain.Apartamento;
import es.deusto.ingenieria.prog3.UDExplore.domain.CadenaHotelera;
import es.deusto.ingenieria.prog3.UDExplore.domain.Cliente;
import es.deusto.ingenieria.prog3.UDExplore.domain.Estancia;
import es.deusto.ingenieria.prog3.UDExplore.domain.Habitacion;
import es.deusto.ingenieria.prog3.UDExplore.domain.Hotel;
import es.deusto.ingenieria.prog3.UDExplore.domain.Reserva;
import es.deusto.ingenieria.prog3.UDExplore.domain.ReservaApartamento;
import es.deusto.ingenieria.prog3.UDExplore.domain.ReservaHotel;
import es.deusto.ingenieria.prog3.UDExplore.domain.Reseña;
import es.deusto.ingenieria.prog3.UDExplore.domain.Usuario;
import es.deusto.ingenieria.prog3.UDExplore.domain.ReservaConEstancia;



public class BaseDeDatos {
	
	public static Connection conexion;
	private static Logger logger = Logger.getLogger( "BaseDeDatos" );
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public static HashMap<String,Usuario> users = new HashMap<String, Usuario>();
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");

	
	public static boolean abrirConexion( String nombreBD, boolean reiniciaBD ) {
		try {
			logger.log( Level.INFO, "Carga de libreria org.sqlite.JDBC" );
			Class.forName("org.sqlite.JDBC");  // Carga la clase de BD para sqlite
			logger.log( Level.INFO, "Abriendo conexion con " + nombreBD );
			conexion = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
			if (reiniciaBD) {
				Statement statement = conexion.createStatement();
				
				String sent = "CREATE TABLE IF NOT EXISTS CadenaHotelera(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre varchar(10));";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				
				sent = "CREATE TABLE IF NOT EXISTS Hotel(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre varchar(10), ciudad varchar(10), foto varchar(20), categoria integer, idCadenaHotelera INTEGER, FOREIGN KEY(idCadenaHotelera) REFERENCES CadenaHotelera(id));";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				
				sent = "CREATE TABLE IF NOT EXISTS Habitacion(id INTEGER PRIMARY KEY AUTOINCREMENT, numHabitacion integer, capacidad integer, precioPorNoche integer, idHotel INTEGER, FOREIGN KEY(idHotel) REFERENCES Hotel(id));";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				
				sent = "CREATE TABLE IF NOT EXISTS Apartamento(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre varchar(10), ciudad varchar(10), foto varchar(20), numHabitacion integer, precioPorNoche integer);";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
								
				sent = "CREATE TABLE IF NOT EXISTS Cliente(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre varchar(10), apellido varchar(10), email varchar(25), contrasenya varchar(25));";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				
				sent = "CREATE TABLE IF NOT EXISTS Reserva(numeroReserva INTEGER PRIMARY KEY AUTOINCREMENT, fechaInicio bigint, fechaFin bigint, idCliente INTEGER, idApartamento INTEGER, idHabitacion INTEGER, FOREIGN KEY(idCliente) REFERENCES Cliente(id), FOREIGN KEY(idApartamento) REFERENCES Apartamento(id), FOREIGN KEY(idHabitacion) REFERENCES Habitacion(id));";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				
				sent = "CREATE TABLE IF NOT EXISTS Administrador(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre varchar(10), apellido varchar(10), email varchar(25), contrasenya varchar(25), salario real);";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				
			}
			
			return true;
		} catch(Exception e) {
			logger.log( Level.SEVERE, "Excepcion", e );
			return false;
		}
	}	
	
	public static void cerrarConexion() {
		try {
			logger.log( Level.INFO, "Cerrando conexion" );
			conexion.close();
		} catch (SQLException e) {
			logger.log( Level.SEVERE, "Excepcion", e );
		}
	}
	
	public static void cargarHoteles() {
		//sent = "CREATE TABLE IF NOT EXISTS Hotel(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre varchar(10), ciudad varchar(10), foto varchar(20), categoria integer, idCadenaHotelera INTEGER, FOREIGN KEY(idCadenaHotelera) REFERENCES CadenaHotelera(id));";
		try (Statement statement = conexion.createStatement()){
			String sent = "select * from Hotel;";
			logger.log( Level.INFO, "Statement: " + sent );
			ResultSet rs = statement.executeQuery( sent );
			while( rs.next() ) { 
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String ciudad = rs.getString("ciudad");
				String foto = rs.getString("foto");
				Integer categoria = rs.getInt("categoria");
				Integer idCadenaHotelera = rs.getInt("idCadenaHotelera");

				Hotel h = new Hotel() ;
				h.setNombre(nombre);
				//CadenaHotelera(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre varchar(10)
				sent = "select * from CadenaHotelera WHERE id="+"'"+idCadenaHotelera+"'"+";";
				logger.log( Level.INFO, "Statement: " + sent );
				ResultSet rs2 = statement.executeQuery( sent );
				while(rs.next()) {
					int id2 = rs2.getInt("id");
					nombre = rs.getString("nombre");
					CadenaHotelera ch = new CadenaHotelera(id2, nombre);
						h.setCadenaHotelera(ch);
					}
			} 
		} catch (SQLException e) {
			 logger.log(Level.SEVERE, "Excepcion SQL", e);
		}
	}

		public static List<Hotel> cargarHotelesEnLista() {
		    List<Hotel> hoteles = new ArrayList<>();

		    try (Statement statement = conexion.createStatement()) {
		        String sent = "SELECT * FROM Hotel;";
		        logger.log(Level.INFO, "Statement: " + sent);
		        ResultSet rs = statement.executeQuery(sent);

		        while (rs.next()) {
		            int id = rs.getInt("id");
		            String nombre = rs.getString("nombre");
		            String ciudad = rs.getString("ciudad");
		            String foto = rs.getString("foto");
		            Integer categoria = rs.getInt("categoria");
		            Integer idCadenaHotelera = rs.getInt("idCadenaHotelera");

		            CadenaHotelera cadenaHotelera = obtenerCadenaHoteleraPorId(idCadenaHotelera);

		            // Buscar si ya existe un hotel con el mismo ID en la lista
		            Hotel existingHotel = buscarHotelPorId(hoteles, id);

		            // Si existe, actualizar sus datos; si no, crear uno nuevo y agregarlo a la lista
		            if (existingHotel != null) {
		                existingHotel.setNombre(nombre);
		                existingHotel.setCiudad(ciudad);
		                existingHotel.setFoto(foto);
		                existingHotel.setCategoria(categoria);
		                existingHotel.setCadenaHotelera(cadenaHotelera);
		            } else {
		                Hotel hotel = new Hotel();
		                hotel.setId(id);
		                hotel.setNombre(nombre);
		                hotel.setCiudad(ciudad);
		                hotel.setFoto(foto);
		                hotel.setCategoria(categoria);
		                hotel.setCadenaHotelera(cadenaHotelera);

		                hoteles.add(hotel);
		            }
		        }
		    } catch (SQLException e) {
		        logger.log(Level.SEVERE, "Excepcion SQL", e);
		    }

		    return hoteles;
		}

		private static Hotel buscarHotelPorId(List<Hotel> hoteles, int id) {
		    for (Hotel hotel : hoteles) {
		        if (hotel.getId() == id) {
		            return hotel;
		        }
		    }
		    return null;
		}




	 
	
	public static void cargarUsuarios() {
		try (Statement statement = conexion.createStatement()){
			String sent = "select * from Cliente;";
			logger.log( Level.INFO, "Statement: " + sent );
			ResultSet rs = statement.executeQuery( sent );
			while( rs.next() ) { 
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String email = rs.getString("email");
				String constrasenya = rs.getString("contrasenya");
				Cliente c = new Cliente(id, nombre, apellido, email, constrasenya) ;
				users.put(email,c);
			}
			String sent1 = "select * from Administrador;";
			logger.log( Level.INFO, "Statement: " + sent1 );
			ResultSet rs1 = statement.executeQuery( sent1 );
			while( rs.next() ) { // Leer el resultset
				int id = rs.getInt("id");
				String nombre = rs1.getString("nombre");
				String apellido = rs1.getString("apellido");
				String email = rs1.getString("email");
				String constrasenya = rs1.getString("contrasenya");
				float salario = rs1.getFloat("salario");
				Administrador a = new Administrador(id, nombre, apellido, email, constrasenya, salario) ;
				users.put(email,a);
			}
		 } catch (SQLException e) {
		        logger.log(Level.SEVERE, "Excepcion SQL", e);
		    }
	}
	
	
	
	
	public static int anyadirCliente(Cliente cliente) {
		String sent="";
		try(Statement statement = conexion.createStatement()) {
			sent = "insert into Cliente (nombre, apellido, email, contrasenya) values ('" + cliente.getNombreUsuario() + "', '" + cliente.getApellido() + "', '" + cliente.getCorreoElectronico() + "', '" + cliente.getContrasenya() + "');";
			logger.log( Level.INFO, "Lanzada actualizacion a base de datos: " + sent );
			int val = statement.executeUpdate( sent );
			logger.log( Level.INFO, "A�adida " + val + " fila a base de datos\t" + sent );
			sent = "select max(id) from Cliente;";
			ResultSet rs = statement.executeQuery(sent);
			if(rs.next()) {
				int id = rs.getInt("id");
				return id;
			}
		} catch (SQLException e) {
			logger.log( Level.SEVERE, "Error en insercion de base de datos\t" + e );
		}
		return 0;
	}
	
	public static int anyadirReserva( Date fechaIni, Date fechaFin, int usuarioId) {
		String sent="";
		try(Statement statement = conexion.createStatement()) {
			sent = "insert into Reserva (fechaInicio, fechaFin, idCliente ) values (" + fechaIni + ",'" + fechaFin + "', " + usuarioId + ");";
			logger.log( Level.INFO, "Lanzada actualizacion a base de datos: " + sent );
			int val = statement.executeUpdate( sent );
			logger.log( Level.INFO, "A�adida " + val + " fila a base de datos\t" + sent );
			sent = "select max(numeroReserva) from Reserva;";
			ResultSet rs = statement.executeQuery(sent);
			if(rs.next()) {
				int id = rs.getInt("numeroReserva");
				return id;
			}
		} catch (SQLException e) {
			logger.log( Level.SEVERE, "Error en insercion de base de datos\t" + e );
		}
		return 0;
	}
	
	public static int anyadirApartamento(ReservaApartamento ra) {
		String sent="";
		try(Statement statement = conexion.createStatement()) {
			sent = "insert into Reserva (fechaInicio, fechaFin, idCliente, idApartamento) values (" + ra.getFechaInicio().getTime() + ",'" + ra.getFechaFin().getTime() + "', " + ra.getCliente().getCodigoUsuario() + ", " + ra.getApartamento().getId()+ ");";
			logger.log( Level.INFO, "Lanzada actualizacion a base de datos: " + sent );
			int val = statement.executeUpdate( sent );
			logger.log( Level.INFO, "Añadida " + val + " fila a base de datos\t" + sent );
		} catch (SQLException e) {
			logger.log( Level.SEVERE, "Error en insercion de base de datos\t" + e );
		}
		return 0;
	}

	public static int anyadirHabitacion(ReservaHotel rh) {
		String sent="";
		try(Statement statement = conexion.createStatement()) {
			sent = "insert into Reserva (fechaInicio, fechaFin, idCliente, idHabitacion) values (" + rh.getFechaInicio().getTime() + ",'" + rh.getFechaFin().getTime() + "', " + rh.getCliente().getCodigoUsuario() + ", " + rh.getHabitacion().getId()+ ");";
			logger.log( Level.INFO, "Lanzada actualizacion a base de datos: " + sent );
			int val = statement.executeUpdate( sent );
			logger.log( Level.INFO, "Añadida " + val + " fila a base de datos\t" + sent );
		} catch (SQLException e) {
			logger.log( Level.SEVERE, "Error en insercion de base de datos\t" + e );
		}
		return 0;
	}
	
	public static Set<String> getCiudades(){
		Set<String> ciudades = new HashSet<>();
		try (Statement statement = conexion.createStatement()){
			String sent = "select distinct ciudad from Hotel;";
			logger.log( Level.INFO, "Statement: " + sent );
			ResultSet rs = statement.executeQuery( sent );
			while( rs.next() ) { 
				ciudades.add(rs.getString("ciudad"));
			}
			String sent1 = "select distinct ciudad from Apartamento;";
			logger.log( Level.INFO, "Statement: " + sent1 );
			ResultSet rs1 = statement.executeQuery( sent1 );
			while( rs1.next() ) { 
				ciudades.add(rs1.getString("ciudad"));
			}
			return ciudades;
		 } catch (SQLException e) {
		        logger.log(Level.SEVERE, "Excepcion SQL", e);
		        return null; 
		    }
	}
	
	public static List<Estancia> buscarEstancia(String ciudad, long fechaIni, long fechaFin){
		List<Estancia> estancias = new ArrayList<>();
		estancias.addAll(buscarHoteles(ciudad, fechaIni, fechaFin));
		estancias.addAll(buscarApartamentos(ciudad, fechaIni, fechaFin));
		return estancias;
	}
	
	public static List<Estancia> buscarHoteles(String ciudad, long fechaIni, long fechaFin){
		List<Estancia> estancias = new ArrayList<>();
		try (Statement statement = conexion.createStatement()){
			String sent = "SELECT Hotel.*, CadenaHotelera.nombre nombreC FROM Hotel, CadenaHotelera WHERE Hotel.idCadenaHotelera = CadenaHotelera.id AND ciudad = '" + ciudad +"' AND Hotel.id IN (SELECT idHotel FROM Habitacion WHERE id NOT IN(SELECT ifnull(idHabitacion, -1) FROM Reserva WHERE fechaInicio >= '" + fechaIni + "' AND fechaFin <= '" + fechaFin + "'));";
			logger.log( Level.INFO, "Statement: " + sent );
			ResultSet rs = statement.executeQuery( sent );
			while( rs.next() ) { 
				CadenaHotelera cd = new CadenaHotelera(rs.getInt("idCadenaHotelera"), rs.getString("nombreC"));
				Hotel hotel = new Hotel(rs.getInt("id"), rs.getString("nombre"), rs.getString("ciudad"), rs.getString("foto"), rs.getInt("categoria"));
				hotel.setCadenaHotelera(cd);
				estancias.add(hotel);
			}
			return estancias;
		 } catch (SQLException e) {
		        logger.log(Level.SEVERE, "Excepcion SQL", e);
		        return null; 
		    }
	}
	
	public static List<Estancia> buscarApartamentos(String ciudad, long fechaIni, long fechaFin){
		List<Estancia> estancias = new ArrayList<>();
		try (Statement statement = conexion.createStatement()){
			String sent = "SELECT Apartamento.* FROM Apartamento WHERE ciudad = '" + ciudad +"' AND Apartamento.id NOT IN(SELECT ifnull(idApartamento, -1) FROM Reserva WHERE fechaInicio >= '" + fechaIni + "' AND fechaFin <= '" + fechaFin + "');";
			logger.log( Level.INFO, "Statement: " + sent );
			ResultSet rs = statement.executeQuery( sent );
			while( rs.next() ) { 
				Apartamento a = new Apartamento(rs.getInt("id"), rs.getString("nombre"), rs.getString("ciudad"), rs.getString("foto"), rs.getInt("numHabitacion"), rs.getFloat("precioPorNoche"));
				estancias.add(a);
			}
			return estancias;
		 } catch (SQLException e) {
		        logger.log(Level.SEVERE, "Excepcion SQL", e);
		        return null; 
		    }
	}
	
	public static List<Habitacion> getHabitaciones(int id){
		List<Habitacion> habitaciones = new ArrayList<>();
		try (Statement statement = conexion.createStatement()){
			String sent = "select * from Habitacion where idHotel = '" + id + "';";
			logger.log( Level.INFO, "Statement: " + sent );
			ResultSet rs = statement.executeQuery( sent );
			while( rs.next() ) { 
				Habitacion h = new Habitacion(rs.getInt("id"),rs.getInt("numHabitacion"), rs.getInt("capacidad"), rs.getDouble("precioPorNoche"));
				habitaciones.add(h);
			}
			return habitaciones;
		 } catch (SQLException e) {
		        logger.log(Level.SEVERE, "Excepcion SQL", e);
		        return null; 
		    }
	}
	
	public static Hotel getHotelPorHabitacion(int habitacionId) {
	    try (Statement statement = conexion.createStatement()) {
	        String sent = "SELECT Hotel.* FROM Hotel, Habitacion WHERE Hotel.id = Habitacion.idHotel AND Habitacion.id = " + habitacionId + ";";
	        logger.log(Level.INFO, "Statement: " + sent);
	        ResultSet rs = statement.executeQuery(sent);

	        if (rs.next()) {
	            int id = rs.getInt("id");
	            String nombre = rs.getString("nombre");
	            String ciudad = rs.getString("ciudad");
	            String foto = rs.getString("foto");
	            Integer categoria = rs.getInt("categoria");
	            Integer idCadenaHotelera = rs.getInt("idCadenaHotelera");

	            CadenaHotelera cadenaHotelera = obtenerCadenaHoteleraPorId(idCadenaHotelera);

	            Hotel hotel = new Hotel(id, nombre, ciudad, foto, categoria);
	            hotel.setCadenaHotelera(cadenaHotelera);

	            return hotel;
	        }
	    } catch (SQLException e) {
	        logger.log(Level.SEVERE, "Excepcion SQL", e);
	    }

	    return null;
	}
	
	private static CadenaHotelera obtenerCadenaHoteleraPorId(int cadenaHoteleraId) {
	    try (Statement statement = conexion.createStatement()) {
	        String sent = "SELECT * FROM CadenaHotelera WHERE id = " + cadenaHoteleraId + ";";
	        logger.log(Level.INFO, "Statement: " + sent);
	        ResultSet rs = statement.executeQuery(sent);

	        if (rs.next()) {
	            int id = rs.getInt("id");
	            String nombre = rs.getString("nombre");
	            return new CadenaHotelera(id, nombre);
	        }
	    } catch (SQLException e) {
	        logger.log(Level.SEVERE, "Excepcion SQL", e);
	    }

	    return null;
	}
	public static List<ReservaConEstancia> cargarReservasPorUsuario(int idUsuario) {
	    List<ReservaConEstancia> reservasConEstancia = new ArrayList<>();

	    try (Statement statement = conexion.createStatement()) {
	        String sent = "SELECT * FROM Reserva WHERE idCliente = " + idUsuario + ";";
	        logger.log(Level.INFO, "Statement: " + sent);
	        ResultSet rs = statement.executeQuery(sent);

	        while (rs.next()) {
	            int numeroReserva = rs.getInt("numeroReserva");
	            long fechaInicio = rs.getLong("fechaInicio");
	            long fechaFin = rs.getLong("fechaFin");
	            int idApartamento = rs.getInt("idApartamento");
	            int idHabitacion = rs.getInt("idHabitacion");

	            Reserva reserva;
	            Estancia estancia = null;

	            if (idApartamento != 0) {
	                Apartamento apartamento = obtenerApartamentoPorId(idApartamento);
	                reserva = new ReservaApartamento(numeroReserva, new Date(fechaInicio), new Date(fechaFin), obtenerClientePorId(idUsuario));
	                estancia = apartamento;
	            } else if (idHabitacion != 0) {
	                Hotel hotel = getHotelPorHabitacion(idHabitacion);
	                reserva = new ReservaHotel(numeroReserva, new Date(fechaInicio), new Date(fechaFin), obtenerClientePorId(idUsuario));
	                estancia = hotel;
	            } else {
	                reserva = null;
	            }

	            if (reserva != null) {
	                System.out.println(numeroReserva + "\t" + DATE_FORMAT.format(new Date(fechaInicio)) + "\t" + DATE_FORMAT.format(new Date(fechaFin)) + "\t" + estancia.getNombre() + "\t" + estancia.getCiudad());
	                reservasConEstancia.add(new ReservaConEstancia(reserva, estancia));
	            }
	        }

	    } catch (SQLException e) {
	        logger.log(Level.SEVERE, "Excepcion SQL", e);
	    }

	    return reservasConEstancia;
	}
	private static Apartamento obtenerApartamentoPorId(int idApartamento) {
	    try (Statement statement = conexion.createStatement()) {
	        String sent = "SELECT * FROM Apartamento WHERE id = " + idApartamento + ";";
	        logger.log(Level.INFO, "Statement: " + sent);
	        ResultSet rs = statement.executeQuery(sent);

	        if (rs.next()) {
	        	return new Apartamento(rs.getInt("id"), rs.getString("nombre"), rs.getString("ciudad"), rs.getString("foto"), rs.getInt("numHabitacion"), rs.getFloat("precioPorNoche"));
	        }
	    } catch (SQLException e) {
	        logger.log(Level.SEVERE, "Excepcion SQL", e);
	    }

	    return null;
	}

	private static Habitacion obtenerHabitacionPorId(int idHabitacion) {
	    try (Statement statement = conexion.createStatement()) {
	        String sent = "SELECT * FROM Habitacion WHERE id = " + idHabitacion + ";";
	        logger.log(Level.INFO, "Statement: " + sent);
	        ResultSet rs = statement.executeQuery(sent);

	        if (rs.next()) {
	            // Crear y devolver el objeto Habitacion con los detalles obtenidos de la base de datos
	            return new Habitacion(rs.getInt("id"), rs.getInt("numHabitacion"), rs.getInt("capacidad"), rs.getDouble("precioPorNoche"));
	        }
	    } catch (SQLException e) {
	        logger.log(Level.SEVERE, "Excepcion SQL", e);
	    }

	    return null;
	}
	private static Cliente obtenerClientePorId(int idCliente) {
	    try (Statement statement = conexion.createStatement()) {
	        String sent = "SELECT * FROM Cliente WHERE id = " + idCliente + ";";
	        logger.log(Level.INFO, "Statement: " + sent);
	        ResultSet rs = statement.executeQuery(sent);

	        if (rs.next()) {
	          
	            return new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"), rs.getString("contrasenya"));
	        }
	    } catch (SQLException e) {
	        logger.log(Level.SEVERE, "Excepcion SQL", e);
	    }

	    return null;
	}

	public static List<Apartamento> cargarApartamentos() {
	    List<Apartamento> apartamentos = new ArrayList<>();

	    try (Statement statement = conexion.createStatement()) {
	        String sent = "SELECT * FROM Apartamento;";
	        logger.log(Level.INFO, "Statement: " + sent);
	        ResultSet rs = statement.executeQuery(sent);

	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String nombre = rs.getString("nombre");
	            String ciudad = rs.getString("ciudad");
	            String foto = rs.getString("foto");
	            int numHabitacion = rs.getInt("numHabitacion");
	            float precioPorNoche = rs.getFloat("precioPorNoche");

	            Apartamento apartamento = new Apartamento(id, nombre, ciudad, foto, numHabitacion, precioPorNoche);
	            apartamentos.add(apartamento);
	        }
	    } catch (SQLException e) {
	        logger.log(Level.SEVERE, "Excepcion SQL", e);
	    }

	    return apartamentos;
	}
	
	public static int contarHabitacionesHotel(int idHotel) {
        try (Statement statement = conexion.createStatement()) {
            String sent = "SELECT COUNT(*) AS total FROM Habitacion WHERE idHotel = " + idHotel + ";";
            logger.log(Level.INFO, "Statement: " + sent);
            ResultSet rs = statement.executeQuery(sent);

            if (rs.next()) {
                int totalHabitaciones = rs.getInt("total");
                logger.log(Level.INFO, "El hotel con ID " + idHotel + " tiene " + totalHabitaciones + " habitaciones.");
                return totalHabitaciones;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Excepcion SQL", e);
        }

        return 0; 

    }
	
	public static void borrarReserva(int numeroReserva) {
	    String sentencia = "DELETE FROM Reserva WHERE numeroReserva = " + numeroReserva + ";";

	    try (Statement statement = conexion.createStatement()) {
	        logger.log(Level.INFO, "Lanzada actualización a base de datos: " + sentencia);
	        int filasAfectadas = statement.executeUpdate(sentencia);

	        if (filasAfectadas > 0) {
	            logger.log(Level.INFO, "Reserva con número " + numeroReserva + " borrada correctamente.");
	        } else {
	            logger.log(Level.WARNING, "No se encontró ninguna reserva con el número " + numeroReserva + ".");
	        }
	    } catch (SQLException e) {
	        logger.log(Level.SEVERE, "Error en la operación de base de datos", e);
	    }
	}





	
}
