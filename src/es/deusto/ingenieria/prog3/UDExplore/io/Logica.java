package es.deusto.ingenieria.prog3.UDExplore.io;


	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.ObjectInputStream;
	import java.io.ObjectOutputStream;
	import java.io.Serializable;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.logging.Level;
	import java.util.logging.Logger;



import es.deusto.ingenieria.prog3.UDExplore.domain.Estancia;

	public class Logica implements Serializable{
		
		private static final long serialVersionUID = 1L;
		public static List<Estancia>  estanciasHistoricas = new ArrayList<>();
		
		
		
		public static List<Estancia> getEstanciasHistoricas() {
			return estanciasHistoricas;
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
				 String rutaArchivo = "./carpeta_destino/" + nombreFic;
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
		
	
	

		
		
	
	}
