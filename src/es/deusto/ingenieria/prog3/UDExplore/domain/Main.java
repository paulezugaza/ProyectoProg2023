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
		
		 		List<Estancia> estancias = new ArrayList<>();
		 		
		 		HashMap<Cliente ,Reserva> reservas = new HashMap<>();
		 		List<Habitacion> habitaciones = obtenerHabitacionesDeEjemplo();
	            

		 	    Hotel hotelMadrid = new Hotel("Hotel Madrid Centro", Ciudad.Madrid, 4, 100, 150.0,
		 	            "Resources/images/madrid.jpg",  reservas, CadenaHotelera.HOTELCO,habitaciones);
		 	    Hotel hotelBarcelona = new Hotel("Hotel Barcelona Playa", Ciudad.Barcelona, 5, 80, 120.0, "Resources/images/BarcelonaHotel.jpg",
		 	            reservas, CadenaHotelera.LUXURYRESORTS, habitaciones);
		 	    Hotel hotelSevilla = new Hotel("Hotel Sevilla Histórico", Ciudad.Sevilla, 3, 60, 100.0, "Resources/images/hotelSevilla.jpg",
		             reservas,  CadenaHotelera.TRAVELHUBHOTELS, habitaciones);

		 	    Apartamento apartamentoValencia = new Apartamento("Apartamento Valencia Beach", Ciudad.Valencia,(int) 4.5, 2, 80.0, "", reservas);
		 	    

		 	    String nombreArchivo = "resources/data/estancias.dat";
		 	    
		 	 
		 	
				
	           VentanaInicio ventana = new VentanaInicio();
	           ventana.setVisible(true);
	           
	           //IA generated
	           
	           Hotel hotel1 = new Hotel("Hotel Madrid Estrella", Ciudad.Madrid, 4, 100, 150.0,
	                   "Resources/images/hotel8.jpg", reservas, CadenaHotelera.HOTELCO, obtenerHabitacionesDeEjemplo());
	           Hotel hotel2 = new Hotel("Hotel Barcelona Luxe", Ciudad.Barcelona, 5, 80, 120.0,
	                   "Resources/images/hotel1.jpg", reservas, CadenaHotelera.LUXURYRESORTS, obtenerHabitacionesDeEjemplo());
	           Hotel hotel3 = new Hotel("Hotel Sevilla Histórico", Ciudad.Sevilla, 3, 60, 100.0,
	                   "Resources/images/hotel2.jpg", reservas, CadenaHotelera.TRAVELHUBHOTELS, obtenerHabitacionesDeEjemplo());
	           Hotel hotel4 = new Hotel("Hotel Valencia Beach", Ciudad.Valencia, 4, 90, 110.0,
	                   "Resources/images/hotel3.jpg", reservas, CadenaHotelera.HOTELCO, obtenerHabitacionesDeEjemplo());
	           Hotel hotel5 = new Hotel("Hotel Granada View", Ciudad.Granada, 2, 70, 80.0,
	                   "Resources/images/hotel.jpeg", reservas, CadenaHotelera.LUXURYRESORTS, obtenerHabitacionesDeEjemplo());
	           Hotel hotel6 = new Hotel("Hotel Malaga Paradise", Ciudad.Málaga, 4, 85, 130.0,
	                   "Resources/images/hotel4.png", reservas, CadenaHotelera.TRAVELHUBHOTELS, obtenerHabitacionesDeEjemplo());
	           Hotel hotel7 = new Hotel("Hotel Bilbao Bliss", Ciudad.Bilbao, 3, 70, 95.0,
	                   "Resources/images/hotel5.jpg", reservas, CadenaHotelera.HOTELCO, obtenerHabitacionesDeEjemplo());
	           Hotel hotel8 = new Hotel("Hotel Toledo Heritage", Ciudad.Toledo, 2, 55, 75.0,
	                   "Resources/images/hotel6.jpg", reservas, CadenaHotelera.LUXURYRESORTS, obtenerHabitacionesDeEjemplo());
	           Hotel hotel9 = new Hotel("Hotel Salamanca Splendor", Ciudad.Salamanca, 3, 60, 100.0,
	                   "Resources/images/hotel7.jpg", reservas, CadenaHotelera.HOTELCO, obtenerHabitacionesDeEjemplo());
	           Hotel hotel10 = new Hotel("Hotel Alicante Paradise", Ciudad.Alicante, 4, 80, 120.0,
	                   "Resources/images/alicante.jpg", reservas, CadenaHotelera.LUXURYRESORTS, obtenerHabitacionesDeEjemplo());

	           	estancias.add(hotelMadrid);
		 	    estancias.add(hotelBarcelona);
		 	    estancias.add(hotelSevilla);
		 	    estancias.add(apartamentoValencia);
		 	    
			  
	           Apartamento apartamento1 = new Apartamento("Apartamento Valencia luxe", Ciudad.Valencia, 4, 2, 80.0, "apartamento1.jpg", reservas);
	           Apartamento apartamento2 = new Apartamento("Apartamento Barcelona Downtown", Ciudad.Barcelona, 3, 3, 90.0, "apartamento2.jpg", reservas);
	           Apartamento apartamento3 = new Apartamento("Apartamento Mallorca Paradise", Ciudad.Mallorca, 2, 2, 70.0, "apartamento3.jpg", reservas);
	           Apartamento apartamento4 = new Apartamento("Apartamento Tenerife View", Ciudad.Tenerife, 3, 4, 100.0, "apartamento4.jpg", reservas);
	           Apartamento apartamento5 = new Apartamento("Apartamento Girona Retreat", Ciudad.Girona, 2, 2, 75.0, "apartamento5.png", reservas);
	           Apartamento apartamento6 = new Apartamento("Apartamento Córdoba Comfort", Ciudad.Córdoba, 3, 3, 85.0, "apartamento6.jpeg", reservas);
	           Apartamento apartamento7 = new Apartamento("Apartamento Valladolid Lodges", Ciudad.Valladolid, 2, 2, 70.0, "", reservas);
	           Apartamento apartamento8 = new Apartamento("Apartamento Cádiz Paradise", Ciudad.Cádiz, 3, 4, 95.0, "", reservas);
	           Apartamento apartamento9 = new Apartamento("Apartamento Murcia Harbor", Ciudad.Murcia, 2, 2, 80.0, "", reservas);
	           Apartamento apartamento10 = new Apartamento("Apartamento Oviedo View", Ciudad.Oviedo, 4, 3, 110.0, "", reservas);

	           estancias.add(hotel1);
	           estancias.add(hotel2);
	           estancias.add(hotel3);
	           estancias.add(hotel4);
	           estancias.add(hotel5);
	           estancias.add(hotel6);
	           estancias.add(hotel7);
	           estancias.add(hotel8);
	           estancias.add(hotel9);
	           estancias.add(hotel10);

	           estancias.add(apartamento1);
	           estancias.add(apartamento2);
	           estancias.add(apartamento3);
	           estancias.add(apartamento4);
	           estancias.add(apartamento5);
	           estancias.add(apartamento6);
	           estancias.add(apartamento7);
	           estancias.add(apartamento8);
	           estancias.add(apartamento9);
	           estancias.add(apartamento10);
	           Logica.setEstanciasHistoricas(estancias);
		 	   Logica.cargarEstancias(nombreArchivo);
	           
	        };

	        public static List<Habitacion> obtenerHabitacionesDeEjemplo() {
	            List<Habitacion> habitaciones = new ArrayList<>();
	            habitaciones.add(new Habitacion( 2, 100.0));
	            habitaciones.add(new Habitacion( 2, 120.0));
	            habitaciones.add(new Habitacion( 4, 180.0));
	            habitaciones.add(new Habitacion( 4, 200.0));
	            habitaciones.add(new Habitacion( 1, 80.0));
	            habitaciones.add(new Habitacion( 1, 80.0));
	            habitaciones.add(new Habitacion( 1, 80.0));
	            habitaciones.add(new Habitacion( 1, 80.0));
	            return habitaciones;
	        }
	 		

	
	public static ArrayList<Object> getList() {
		return listaEstancias; 
		
		
	}


}
