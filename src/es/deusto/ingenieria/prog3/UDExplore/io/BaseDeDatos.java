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
import es.deusto.ingenieria.prog3.UDExplore.domain.CadenaHotelera;
import es.deusto.ingenieria.prog3.UDExplore.domain.Cliente;
import es.deusto.ingenieria.prog3.UDExplore.domain.Estancia;
import es.deusto.ingenieria.prog3.UDExplore.domain.Habitacion;
import es.deusto.ingenieria.prog3.UDExplore.domain.Hotel;
import es.deusto.ingenieria.prog3.UDExplore.domain.Usuario;



public class BaseDeDatos {
	
	public static Connection conexion;
	private static Logger logger = Logger.getLogger( "BaseDeDatos" );
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public static HashMap<String,Usuario> users = new HashMap<String, Usuario>();
	
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
				
				sent = "CREATE TABLE IF NOT EXISTS Apartamento(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre varchar(10), ciudad varchar(10), numHabitacion integer, precioPorNoche integer);";
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
			sent = "insert reserva (fechaIni, fechaFin, usuarioId ) values (" + fechaIni + ",'" + fechaFin + "', " + usuarioId + ");";
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
		try (Statement statement = conexion.createStatement()){
			String sent = "SELECT Hotel.*, CadenaHotelera.nombre nombreC FROM Hotel, CadenaHotelera WHERE Hotel.idCadenaHotelera = CadenaHotelera.id AND ciudad = '" + ciudad +"' AND Hotel.id IN (SELECT idHotel FROM Habitacion WHERE id NOT IN(SELECT idHabitacion FROM Reserva WHERE fechaInicio >= '" + fechaIni + "' AND fechaFin <= '" + fechaFin + "'));";
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
	
}
