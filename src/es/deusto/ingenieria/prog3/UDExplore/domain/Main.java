package es.deusto.ingenieria.prog3.UDExplore.domain;

import java.util.ArrayList;
import java.util.List;


import es.deusto.ingenieria.prog3.UDExplore.gui.VentanaInicio;
import es.deusto.ingenieria.prog3.UDExplore.io.BaseDeDatos;


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
