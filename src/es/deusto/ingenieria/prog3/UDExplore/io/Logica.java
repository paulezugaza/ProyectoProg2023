package es.deusto.ingenieria.prog3.UDExplore.io;


	import java.io.BufferedReader;
	import java.io.BufferedWriter;
	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.FileReader;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.io.ObjectInputStream;
	import java.io.ObjectOutputStream;
	import java.io.Serializable;
	import java.text.ParseException;
	import java.text.SimpleDateFormat;
	import java.util.ArrayList;
	import java.util.Date;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	import java.util.logging.Level;
	import java.util.logging.Logger;
	import javax.swing.JComboBox;

	import es.deusto.ingenieria.prog3.UDExplore.domain.Apartamento;
	import es.deusto.ingenieria.prog3.UDExplore.domain.Cliente;
	import es.deusto.ingenieria.prog3.UDExplore.domain.Estancia;
	import es.deusto.ingenieria.prog3.UDExplore.domain.Hotel;
	import es.deusto.ingenieria.prog3.UDExplore.domain.Reserva;
	import es.deusto.ingenieria.prog3.UDExplore.domain.Usuario;



	public class Logica implements Serializable{
		
		private static final long serialVersionUID = 1L;
		public static List<Estancia>  estanciasHistoricas = new ArrayList<>();
		private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		public static Usuario usuario=null;
		public static Date fechaIni;
		public static Date fechaFin;
		
		public static List<Estancia> getEstanciasHistoricas() {
			return estanciasHistoricas;
		}

		public static boolean UsuarioComprador(String email) {
			if(BaseDeDatos.getUsuarios().get(email) instanceof Cliente ) return true;
			else return false;
		}
		

		public static void setEstanciasHistoricas(List<Estancia> estanciasHistoricas) {
			Logica.estanciasHistoricas = estanciasHistoricas;
		}

		private static Logger logger = Logger.getLogger( "Logica" );

		
		public Logica() {
			super();
		}


		
		public static void guardarEstancias(String nombreFic) {
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreFic));
				oos.writeObject(estanciasHistoricas);
				logger.log( Level.INFO, "Estancias guardadas correctamente en: " + nombreFic );
				oos.close();
			}catch(IOException e){
				logger.log( Level.INFO, "Ha habido un error en la escritura del fichero: " + nombreFic + e);
			}
		}
		
		
		public static void cargarEstancias(String nombreFic) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreFic));
				@SuppressWarnings("unchecked")
				List<Estancia> cCargado = (ArrayList<Estancia>) ois.readObject();
				estanciasHistoricas=cCargado;
				ois.close();
				logger.log( Level.INFO, "Estancias cargadas correctamente desde: " + nombreFic );
			} catch (IOException | ClassNotFoundException e) {
				logger.log( Level.INFO, "ERROR EN LA CARGA de fichero: " + nombreFic + e);
			}
		}
		
		public static boolean existeUsuario(String email) {
			if(BaseDeDatos.getUsuarios().containsKey(email)) return true;
			else return false;
		}
		public static void crearUsuario(String nombre, String email, String contrasenya) {
			Cliente c1= new Cliente(nombre, email,contrasenya, 0); 
			BaseDeDatos.getUsuarios().put(c1.getCorreoElectronico(),c1);
			
		}
		public static Usuario usuarioCorrecto(String email, String contrasenya) {
			if(BaseDeDatos.getUsuarios().get(email).getContraseña().equals(contrasenya)){
				Logica.usuario=BaseDeDatos.getUsuarios().get(email);
				logger.log( Level.INFO, "Existe usuario en la BD");
				return BaseDeDatos.getUsuarios().get(email);
			}else return null;
		}
		
		private static final String RESERVATIONS_FILE = "resources/data/reservas.csv";
		private static final String HOTELES_FILE = "resources/data/hoteles.csv";
		private static final String APARTAMENTOS_FILE = "resources/data/apartamentos.csv";
		
		public static void guardarReserva(Reserva reserva) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(RESERVATIONS_FILE, true))) {
				String line = String.format("%s#%s#%s#%s", 
										reserva.getCliente().toString(),
										String.valueOf(reserva.getFechaInicio()),
										String.valueOf(reserva.getFechaFin()));
				writer.write(line);
				writer.newLine();
	        } catch (Exception ex) {
	        	logger.warning(String.format("%s - Error guardando reserva: %s", reserva , ex.getMessage()));
	        }		
		}
		
		public static Map<Cliente, List<Reserva>> cargarMapaReservas() {
			 Map<Cliente, List<Reserva>> reservasCliente = new HashMap<>();
			
			try (BufferedReader reader = new BufferedReader(new FileReader(RESERVATIONS_FILE))) {
				String line = reader.readLine();
				Reserva reserva;

				while ((line = reader.readLine()) != null) {					reserva = Reserva.parseCSV(line);
					reservasCliente.putIfAbsent(reserva.getCliente(), new ArrayList<>());
					reservasCliente.get(reserva.getCliente()).add(reserva);
				}
			} catch (Exception ex) {
				logger.warning(String.format("%s - Error cargando reservas: %s",  ex.getMessage()));
			}
			
			return reservasCliente;

		}
		
		public static void guardarHotel(Hotel hotel) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(HOTELES_FILE, true))) {
				String line = String.format("%s#%s#%s#%s#%s#%s#%s#%s#%s", 
										hotel.getNombre(),
										hotel.getCiudad(),
										hotel.getCategoria(),
										hotel.getNumeroHabitaciones(),
										hotel.getTarifaNoche(),
										hotel.getFoto(),
										hotel.getReservas(),
										hotel.getCadenaHotelera(),
										hotel.getHabitaciones());
					
				
				writer.write(line);
				writer.newLine();
	        } catch (Exception ex) {
	        	logger.warning(String.format("%s - Error guardando reserva: %s", hotel , ex.getMessage()));
	        }		
		}
		
		
		public static void guardarApartamento(Apartamento apartamento) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(APARTAMENTOS_FILE, true))) {
				String line = String.format("%s#%s#%s#%s#%s#%s#%s", 
										apartamento.getNombre(),
										apartamento.getCiudad(),
										apartamento.getCategoria(),
										apartamento.getNumeroHabitaciones(),
										apartamento.getTarifaNoche(),
										apartamento.getFoto(),
										apartamento.getReservas());
										
					
				
				writer.write(line);
				writer.newLine();
	        } catch (Exception ex) {
	        	logger.warning(String.format("%s - Error guardando reserva: %s", apartamento, ex.getMessage()));
	        }		
		}
		
		public static boolean estanciaDisponibleEnFechas(Estancia estancia, Date inicio, Date fin) {
		    if (estancia.getReservas() == null) {
		        return true; 
		    }

		    for (List<Reserva> reserva : estancia.getReservas().values()) {
		        if (!fin.before(((Reserva) reserva).getFechaInicio()) && !inicio.after(((Reserva) reserva).getFechaFin())) {
		            return false; 
		        }
		    }
		    return true; 
		}

		public static long parsear(String anyo, String mes, String dia) {
			String fecha = dia + "/" + mes + "/" + anyo;
			try {
				return sdf.parse(fecha).getTime();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}
		public static Date obtenerFechaSeleccionadaIni(JComboBox<String> comboDia, JComboBox<String> comboMes, JComboBox<String> comboAnio) {
		    try {
		        String fechaString = String.format("%s/%s/%s",
		                comboDia.getSelectedItem(),
		                comboMes.getSelectedIndex() + 1,
		                comboAnio.getSelectedItem());
		        fechaIni = sdf.parse(fechaString);
		        return sdf.parse(fechaString);
		    } catch (ParseException e) {
		        e.printStackTrace();
		        return null;
		    }
		}
		public static Date obtenerFechaSeleccionadaFin(JComboBox<String> comboDia, JComboBox<String> comboMes, JComboBox<String> comboAnio) {
		    try {
		        String fechaString = String.format("%s/%s/%s",
		                comboDia.getSelectedItem(),
		                comboMes.getSelectedIndex() + 1,
		                comboAnio.getSelectedItem());
		        fechaFin = sdf.parse(fechaString);
		        return sdf.parse(fechaString);
		    } catch (ParseException e) {
		        e.printStackTrace();
		        return null;
		    }
		}
		
		
	
	}