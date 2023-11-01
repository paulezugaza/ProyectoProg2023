package es.deusto.ingenieria.prog3.UDExplore.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.deusto.ingenieria.prog3.UDExplore.domain.Habitacion;
import es.deusto.ingenieria.prog3.UDExplore.gui.VentanaInicio;
import es.deusto.ingenieria.prog3.UDExplore.gui.VentanaResultados;
import es.deusto.ingenieria.prog3.UDExplore.io.Logica;

public class Main {
	
	private static ArrayList<Object> listaEstancias;

	public static void main(String[] args) {
		
		 		List<Estancia> estancias = new ArrayList<>();
		 		
		 		List<Hotel> hoteles = new ArrayList<>();
		 		List<Estancia> apartamentos = new ArrayList<>();
		 	

		 	    Hotel hotelMadrid = new Hotel("Hotel Madrid Centro", Ciudad.Madrid, 4, 100, 150.0,
		 	            "Resources/images/madrid.jpg", CadenaHotelera.GRANDSPLENDOUR, obtenerHabitacionesDeEjemplo(), new ArrayList<>());
		 	    Hotel hotelBarcelona = new Hotel("Hotel Barcelona Playa", Ciudad.Barcelona, 5, 80, 120.0, "",
		 	            CadenaHotelera.LUXURYRESORTS, obtenerHabitacionesDeEjemplo(), new ArrayList<>());
		 	    Hotel hotelSevilla = new Hotel("Hotel Sevilla Histórico", Ciudad.Sevilla, 3, 60, 100.0, "",
		             CadenaHotelera.SUNSETRETREAT, obtenerHabitacionesDeEjemplo(), new ArrayList<>());

		 	    Apartamento apartamentoValencia = new Apartamento("Apartamento Valencia Beach", Ciudad.Valencia,(int) 4.5, 2, 80.0, "", new ArrayList<>());
		 	    estancias.add(hotelMadrid);
		 	    estancias.add(hotelBarcelona);
		 	    estancias.add(hotelSevilla);
		 	    estancias.add(apartamentoValencia);
		 	  

		 	    String nombreArchivo = "estancias.dat";

		 	
		 	    Logica.setEstanciasHistoricas(estancias);
		 	    Logica.guardarEstancias(nombreArchivo);		
		 	    
		 	
				
	           VentanaInicio ventana = new VentanaInicio();
	           ventana.setVisible(true);
	           
	          
	           
	            
	        };

	 		public static List<Habitacion> obtenerHabitacionesDeEjemplo() {
	 	        List<Habitacion> habitaciones = new ArrayList<>();
	 	        habitaciones.add(new Habitacion(101, 2, 100.0));
	 	        habitaciones.add(new Habitacion(102, 2, 120.0));
	 	        habitaciones.add(new Habitacion(201, 4, 180.0));
	 	        habitaciones.add(new Habitacion(202, 4, 200.0));
	 	        habitaciones.add(new Habitacion(301, 1, 80.0));
	 	        return habitaciones;
	 	    }

/**
	 		public class GeneradorHotelesApartamentos {

	 		    public static void main(String[] args) {
	 		        List<Ciudad> ciudades = new ArrayList<>();
	 		        for (Ciudad ciudad : Ciudad.values()) {
	 		            ciudades.add(ciudad);
	 		        }

	 		        List<CadenaHotelera> cadenasHotel = new ArrayList<>();
	 		        for (CadenaHotelera cadena : CadenaHotelera.values()) {
	 		            cadenasHotel.add(cadena);
	 		        }

	 		        List<Hotel> hoteles = new ArrayList<>();
	 		        List<Apartamento> apartamentos = new ArrayList<>();
	 		        Random random = new Random();

	 		        for (Ciudad ciudad : ciudades) {
	 		            for (CadenaHotelera cadenaHotel : cadenasHotel) {
	 		                for (int i = 0; i < 10; i++) {
	 		                    // Generar datos aleatorios para los hoteles y apartamentos
	 		                    String nombreEstancia = "Nombre Estancia " + i;
	 		                    int categoria = random.nextInt(5) + 1;
	 		                    int numeroHabitaciones = random.nextInt(100) + 1;
	 		                    double tarifaNoche = random.nextDouble() * 200.0;
	 		                    String foto = "URL de la foto " + i;

	 		                    List<Habitacion> habitaciones = new ArrayList<>();
	 		                    for (int j = 0; j < numeroHabitaciones; j++) {
	 		                        int numeroHabitacion = j + 1;
	 		                        int capacidadMaxima = random.nextInt(5) + 1;
	 		                        double precioPorNoche = random.nextDouble() * 100.0;
	 		                        Habitacion habitacion = new Habitacion(numeroHabitacion, capacidadMaxima, precioPorNoche);
	 		                        habitaciones.add(habitacion);
	 		                    }


	 		                    if (random.nextBoolean()) {
	 		                        hoteles.add(new Hotel(nombreEstancia, ciudad, categoria, numeroHabitaciones, tarifaNoche, foto, cadenaHotel, habitaciones, new ArrayList<>()));
	 		                    } else {
	 		                        apartamentos.add(new Apartamento(nombreEstancia, ciudad, categoria, numeroHabitaciones, tarifaNoche, foto, List<Reserva>()));
	 		                    }
	 		                }
	 		            }
	 		        }
	 		       System.out.println(apartamentos);
		 		    System.out.println(hoteles);
	 		        // Ahora tienes listas de hoteles y apartamentos para cada ciudad y cadena de hotel.
	 		        // Puedes hacer lo que desees con estas listas.
	 		    }
	 		    
	 		}
		
	
		Apartamento apartamentoValencia = new Apartamento("Apartamento Valencia Beach", Ciudad.Valencia, 2, 80.0, 4.5);
		Apartamento apartamentoGranada = new Apartamento("Apartamento Granada Centro", Ciudad.Granada, 3, 70.0, 4.0);
		Apartamento apartamentoMallorca = new Apartamento("Apartamento Mallorca Relax", Ciudad.Mallorca, 4, 90.0, 4.2);

		// Crear más hoteles
		Hotel hotelParis = new Hotel("Hotel París Elegance", Ciudad.Bilbao, 120, 180.0, 5, CadenaHotelera.TRAVELHUBHOTELS, new ArrayList<>());
		Hotel hotelRoma = new Hotel("Hotel Roma Vistas", Ciudad.Pamplona, 90, 140.0, 4, CadenaHotelera.GRANDSPLENDOUR, new ArrayList<>());
		Hotel hotelLondres = new Hotel("Hotel Londres Clásico", Ciudad.Valladolid, 80, 130.0, 4, CadenaHotelera.LUXURYRESORTS, new ArrayList<>());

		// Crear más apartamentos
		Apartamento apartamentoBilbao = new Apartamento("Apartamento Bilbao Moderno", Ciudad.Bilbao, 2, 85.0, 4.6);
		Apartamento apartamentoMalaga = new Apartamento("Apartamento Málaga Playa", Ciudad.Málaga, 3, 75.0, 4.1);
		Apartamento apartamentoLisboa = new Apartamento("Apartamento Lisboa Histórico", Ciudad.Córdoba, 4, 95.0, 4.3);

	
		ArrayList<Object> listaEstancias = new ArrayList<>();
		listaEstancias.add(apartamentoLisboa);
		listaEstancias.add(apartamentoBilbao);
		listaEstancias.add(apartamentoMalaga);
		listaEstancias.add(apartamentoValencia);
		listaEstancias.add(apartamentoGranada);
		listaEstancias.add(apartamentoMallorca);
		listaEstancias.add(hotelMadrid);
		listaEstancias.add(hotelBarcelona);
		listaEstancias.add(hotelSevilla );
		

	}
**/
	
	public static ArrayList<Object> getList() {
		return listaEstancias; 
		
		
	}
	

       
    
	


}
