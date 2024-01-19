package es.deusto.ingenieria.prog3.UDExplore.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import es.deusto.ingenieria.prog3.UDExplore.gui.VentanaInicio;
import es.deusto.ingenieria.prog3.UDExplore.io.BaseDeDatos;
import es.deusto.ingenieria.prog3.UDExplore.io.Logica;

public class Main {
	
	private static ArrayList<Object> listaEstancias;

	public static ArrayList<Object> getListaEstancias() {
		return listaEstancias;
	}

	public static void setListaEstancias(ArrayList<Object> listaEstancias) {
		Main.listaEstancias = listaEstancias;
	}

	public static void main(String[] args) {
		
				BaseDeDatos.abrirConexion("BaseDeDatos.db", true);
				BaseDeDatos.cargarUsuarios();
							
				
		
//		 		List<Estancia> estancias = new ArrayList<>();
//		 		
//		 		HashMap<Cliente ,Reserva> reservas = new HashMap<>();
//		 		List<Habitacion> habitaciones = obtenerHabitacionesDeEjemplo();
//	            
//		 		CadenaHotelera cadena1 = new CadenaHotelera(1, "HOTELCO");
//		 		CadenaHotelera cadena2 = new CadenaHotelera(2, "LUXURYRESORTS");
//		 		CadenaHotelera cadena3 = new CadenaHotelera(3, "TRAVELHUBHOTELS");
//
//		 	    Hotel hotelMadrid = new Hotel(1, "Hotel Madrid Centro", "Madrid","Resources/images/madrid.jpg",  4);
//		 	    hotelMadrid.setCadenaHotelera(cadena1);
//		 	    hotelMadrid.setHabitaciones(habitaciones);
//		 	    Hotel hotelBarcelona = new Hotel(2,"Hotel Barcelona Playa", "Barcelona", "Resources/images/BarcelonaHotel.jpg", 5);
//		 	    hotelBarcelona.setCadenaHotelera(cadena2);
//		 	    Hotel hotelSevilla = new Hotel(3, "Hotel Sevilla Histórico", "Sevilla", "Resources/images/hotelSevilla.jpg", 3);
//		 	    hotelSevilla.setCadenaHotelera(cadena3);
//		 	    
//		 	    cadena1.addHotel(hotelMadrid);
//		 	    cadena2.addHotel(hotelBarcelona);
//		 	    cadena3.addHotel(hotelSevilla);
//		 	    
//		 	    Apartamento apartamentoValencia = new Apartamento(1, "Apartamento Valencia Beach", "Valencia", "",2, 80.0f);
//		 	    estancias.add(hotelMadrid);
//		 	    estancias.add(hotelBarcelona);
//		 	    estancias.add(hotelSevilla);
//		 	    estancias.add(apartamentoValencia);
//			  
//
//		 	    String nombreArchivo = "resources/data/estancias.dat";
//		 	    
//		 	    Logica.setEstanciasHistoricas(estancias);
//		 	    Logica.guardarEstancias(nombreArchivo);
//		 	
//				
	           VentanaInicio ventana = new VentanaInicio();
	           ventana.setVisible(true);
	        
	           
	           
	        }

	        public static List<Habitacion> obtenerHabitacionesDeEjemplo() {
	            List<Habitacion> habitaciones = new ArrayList<>();
	            habitaciones.add(new Habitacion(1, 101, 2, 100.0));
	            habitaciones.add(new Habitacion(2, 102, 2, 120.0));
	            habitaciones.add(new Habitacion(3, 201, 4, 180.0));
	            habitaciones.add(new Habitacion(4, 202, 4, 200.0));
	            habitaciones.add(new Habitacion(5, 301, 1, 80.0));
	            habitaciones.add(new Habitacion(6, 302, 1, 80.0));
	            habitaciones.add(new Habitacion(7, 404, 1, 80.0));
	            habitaciones.add(new Habitacion(8, 420, 1, 80.0));
	            return habitaciones;
	        }
	 		

	
	public static ArrayList<Object> getList() {
		return listaEstancias; 
		
		
	}


}
