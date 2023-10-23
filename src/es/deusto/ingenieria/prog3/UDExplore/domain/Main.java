package es.deusto.ingenieria.prog3.UDExplore.domain;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.prog3.UDExplore.domain.Habitacion;
import es.deusto.ingenieria.prog3.UDExplore.gui.VentanaInicio;
import es.deusto.ingenieria.prog3.UDExplore.gui.VentanaResultados;

public class Main {
	
	private static ArrayList<Object> listaEstancias;

	public static void main(String[] args) {
		
		 		List<Estancia> estancias = new ArrayList<>();
		 		estancias.add(hotelMadrid);
		 		estancias.add(hotelBarcelona);
	            VentanaInicio ventana = new VentanaInicio();
	            ventana.setVisible(true);
	            
	            
	        };
	
	
		static Hotel hotelMadrid = new Hotel("Hotel Madrid Centro", Ciudad.Madrid, 100, 150.0,"", 4, CadenaHotelera.GRANDSPLENDOUR,new ArrayList<>()) ;
		static Hotel hotelBarcelona = new Hotel("Hotel Barcelona Playa", Ciudad.Barcelona, 80, 120.0,"", 3, CadenaHotelera.LUXURYRESORTS, new ArrayList<>());
		Hotel hotelSevilla = new Hotel("Hotel Sevilla Histórico", Ciudad.Sevilla, 60, 100.0, "", 4, CadenaHotelera.SUNSETRETREAT, new ArrayList<>());
/**
		// Crear apartamentos
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
