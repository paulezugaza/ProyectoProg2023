package es.deusto.ingenieria.prog3.UDExplore.domain;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Apartamento extends Estancia implements Serializable {
	
	

	private static final long serialVersionUID = 1L;
    private int id=0;
	List<Reserva> reservasLista;
	
	public Apartamento() {
		super();
		
	}


	public Apartamento(String nombre, Ciudad ciudad, int categoria, int numeroHabitaciones, double tarifaNoche,
			String foto, Map<Cliente, List<Reserva>> reservas) {
		super(nombre, ciudad, categoria, numeroHabitaciones, tarifaNoche, foto, reservas);
		
	}


	public Map<Cliente, List<Reserva>> getReservas() {
		return reservas;
	}


	public void setReservas(Map<Cliente, List<Reserva>> reservas) {
		this.reservas = reservas;
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