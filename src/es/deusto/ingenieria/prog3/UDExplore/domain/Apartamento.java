package es.deusto.ingenieria.prog3.UDExplore.domain;


import java.io.Serializable;
import java.util.HashMap;


public class Apartamento extends Estancia implements Serializable {
	
	

	private static final long serialVersionUID = 1L;
	

	public Apartamento(String nombre, Ciudad ciudad, int categoria, int numeroHabitaciones, double tarifaNoche,
			String foto, HashMap<Cliente, Reserva> reservas) {
		super(nombre, ciudad, categoria, numeroHabitaciones, tarifaNoche, foto, reservas);
		// TODO Auto-generated constructor stub
	}


	public String getNombre() {
		return super.getNombre();
	}


	@Override
	public int getNumeroHabitaciones() {
		return super.getNumeroHabitaciones();
	}

	@Override
	public double getTarifaNoche() {
		return super.getTarifaNoche();
	}

	@Override
	public void setNombre(String nombre) {
		super.setNombre(nombre);
	}

	@Override
	public void setNumeroHabitaciones(int numeroHabitaciones) {
		super.setNumeroHabitaciones(numeroHabitaciones);
	}

	@Override
	public void setTarifaNoche(double tarifaNoche) {
		super.setTarifaNoche(tarifaNoche);
	}

	@Override
	public double calcularPrecioTotal(int numNoches) {
		return super.calcularPrecioTotal(numNoches);
	}



}