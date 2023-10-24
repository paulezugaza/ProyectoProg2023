package es.deusto.ingenieria.prog3.UDExplore.domain;

import java.util.List;

public class Apartamento extends Estancia{
	
	private double puntuacion; 

	//Constructor
	public Apartamento(String nombre, Ciudad ciudad, int numeroHabitaciones, double tarifaNoche, String foto, double puntuacion) {
		super(nombre, ciudad, numeroHabitaciones,  tarifaNoche, foto);
		this.puntuacion =  puntuacion;
	}
	
	
	//Getter
	public Double getPuntuacion() {
		return puntuacion;
	}
	
	
	//Setter
	public void setPuntuacion(Float puntuacion) {
		this.puntuacion = puntuacion;
	}

	
	//Métodos
	@Override
	public String toString() {
		return "Apartamento " + super.toString() + "[puntuacion=" + puntuacion + "]";
	}	

}
