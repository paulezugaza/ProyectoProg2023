package es.deusto.ingenieria.prog3.UDExplore.io;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComboBox;

import es.deusto.ingenieria.prog3.UDExplore.domain.Cliente;
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
		
		
	
	}