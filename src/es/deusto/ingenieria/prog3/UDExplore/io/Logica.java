package es.deusto.ingenieria.prog3.UDExplore.io;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComboBox;

import es.deusto.ingenieria.prog3.UDExplore.domain.Apartamento;
import es.deusto.ingenieria.prog3.UDExplore.domain.Cliente;
import es.deusto.ingenieria.prog3.UDExplore.domain.Habitacion;
import es.deusto.ingenieria.prog3.UDExplore.domain.Hotel;
import es.deusto.ingenieria.prog3.UDExplore.domain.Usuario;

public class Logica implements Serializable{
		
		private static final long serialVersionUID = 1L;
		public static List<Hotel>  hotelesHistoricos = new ArrayList<>();
		private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		public static Usuario usuario=null;
		public static Date fechaIni;
		public static Date fechaFin;
		
//		public static List<Estancia> getEstanciasHistoricas() {
//			return estanciasHistoricas;
//		}

		public static boolean esUsuarioCliente(String email) {
			return BaseDeDatos.users.get(email) instanceof Cliente;
		}
		

//		public static void setEstanciasHistoricas(List<Estancia> estanciasHistoricas) {
//			Logica.estanciasHistoricas = estanciasHistoricas;
//		}

		private static Logger logger = Logger.getLogger( "Logica" );

		
		public Logica() {
			super();
		}


		
//		public static void guardarEstancias(String nombreFic) {
//			try {
//				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreFic));
//				oos.writeObject(estanciasHistoricas);
//				logger.log( Level.INFO, "Estancias guardadas correctamente en: " + nombreFic );
//				oos.close();
//			}catch(IOException e){
//				logger.log( Level.INFO, "Ha habido un error en la escritura del fichero: " + nombreFic + e);
//			}
//		}
//		
//		
//		public static void cargarEstancias(String nombreFic) {
//			try {
//				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreFic));
//				@SuppressWarnings("unchecked")
//				List<Estancia> cCargado = (ArrayList<Estancia>) ois.readObject();
//				estanciasHistoricas=cCargado;
//				ois.close();
//				logger.log( Level.INFO, "Estancias cargadas correctamente desde: " + nombreFic );
//			} catch (IOException | ClassNotFoundException e) {
//				logger.log( Level.INFO, "ERROR EN LA CARGA de fichero: " + nombreFic + e);
//			}
//		}
		
		public static boolean existeUsuario(String email) {
			return BaseDeDatos.users.containsKey(email);
		}
		
		public static void crearUsuario(String nombre, String apellido, String email, String contrasenya) {
			Cliente c1 = new Cliente(nombre, apellido, email, contrasenya);
			int id = BaseDeDatos.anyadirCliente(c1);
			c1.setCodigoUsuario(id);
			BaseDeDatos.users.put(c1.getCorreoElectronico(),c1);
			
		}
		public static boolean usuarioCorrecto(String email, String contrasenya) {
			if(BaseDeDatos.users.get(email) != null) {
				if(BaseDeDatos.users.get(email).getContrasenya().equals(contrasenya)) {
					usuario = BaseDeDatos.users.get(email);
					logger.log( Level.INFO, "Usuario correcto");
					return true;
				}
			}
			return false;
		}
//		public static boolean estanciaDisponibleEnFechas(Estancia estancia, Date inicio, Date fin) {
//		    if (estancia.getReservas() == null) {
//		        return true; 
//		    }
//
//		    for (Reserva reserva : habitacion.getReservas()) {
//		        if (!fin.before(reserva.getFechaInicio()) && !inicio.after(reserva.getFechaFin())) {
//		            return false; 
//		        }
//		    }
//		    return true; 
//		}

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


		

		 public static int calcularDiferenciaEnDias(Date fechaInicio, Date fechaFin) {
		        long diffEnMillis = fechaFin.getTime() - fechaInicio.getTime();
		        return (int) TimeUnit.DAYS.convert(diffEnMillis, TimeUnit.MILLISECONDS);
		    }
		 
		 public static Map<Apartamento, Integer> buscarOpcionesApartamentosRecursivo(double presupuesto, List<Apartamento> apartamentos) {
			    Map<Apartamento, Integer> opciones = new HashMap<>();
			    buscarOpcionesApartamentosRecursivo(presupuesto, apartamentos, opciones, 0);
			    return opciones;
			}

			private static void buscarOpcionesApartamentosRecursivo(double presupuesto, List<Apartamento> apartamentos, Map<Apartamento, Integer> opciones, int index) {
			    if (index >= apartamentos.size() || presupuesto <= 0) {
			        return;  // Condición de terminación: índice fuera de la lista o presupuesto agotado
			    }

			    Apartamento apartamento = apartamentos.get(index);
			    double tarifaNoche = apartamento.getTarifaNoche();

			    if (presupuesto >= tarifaNoche) {
			        int nochesEnApartamento = (int) Math.floor(presupuesto / tarifaNoche);
			        opciones.put(apartamento, nochesEnApartamento);

			        double presupuestoRestante = presupuesto - (nochesEnApartamento * tarifaNoche);
			        buscarOpcionesApartamentosRecursivo(presupuestoRestante, apartamentos, opciones, index + 1);
			    }

			    // Llamada recursiva sin seleccionar el apartamento actual
			    buscarOpcionesApartamentosRecursivo(presupuesto, apartamentos, opciones, index + 1);
			}

			public static Map<Habitacion, Integer> obtenerOpcionesHabitacionesRecursivo(double presupuesto, List<Habitacion> habitaciones) {
			    Map<Habitacion, Integer> opciones = new HashMap<>();
			    buscarOpcionesHabitacionesRecursivo(presupuesto, habitaciones, opciones, 0);
			    return opciones;
			}

			private static void buscarOpcionesHabitacionesRecursivo(double presupuesto, List<Habitacion> habitaciones, Map<Habitacion, Integer> opciones, int index) {
			    if (index >= habitaciones.size() || presupuesto <= 0) {
			        return;  // Condición de terminación: índice fuera de la lista o presupuesto agotado
			    }

			    Habitacion habitacion = habitaciones.get(index);
			    double precioPorNoche = habitacion.getPrecioPorNoche();

			    if (presupuesto >= precioPorNoche) {
			        int nochesEnHabitacion = (int) Math.floor(presupuesto / precioPorNoche);
			        opciones.put(habitacion, nochesEnHabitacion);

			        double presupuestoRestante = presupuesto - (nochesEnHabitacion * precioPorNoche);
			        buscarOpcionesHabitacionesRecursivo(presupuestoRestante, habitaciones, opciones, index + 1);
			    }

			    // Llamada recursiva sin seleccionar la habitación actual
			    buscarOpcionesHabitacionesRecursivo(presupuesto, habitaciones, opciones, index + 1);
			}



			public static List<Habitacion> getAllHabitaciones() {
				List<Hotel> hoteles = BaseDeDatos.cargarHotelesEnLista();
				System.out.println(hoteles);
				List<Habitacion> habitaciones = new ArrayList<>();
				hoteles.forEach( h -> {
					BaseDeDatos.getHabitaciones(h.getId()).forEach( habi -> {
						habitaciones.add(habi);
					});
				
				});
				System.out.println(habitaciones);
				return habitaciones;
			}
		    
		
		  
		



		
	
	}