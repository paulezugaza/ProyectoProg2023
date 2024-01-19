package es.deusto.ingenieria.prog3.UDExplore.domain;


import java.io.Serializable;
import java.util.List;


public class Apartamento extends Estancia implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int numHabitaciones;
	private float tarifaNoche;
	
	private List<ReservaApartamento> reservas;

	public Apartamento(int id, String nombre, String ciudad, String foto, int numHabitaciones, float tarifaNoche) {
		super(id, nombre, ciudad, foto);
		this.numHabitaciones = numHabitaciones;
		this.tarifaNoche = tarifaNoche;
	}

	public int getNumHabitaciones() {
		return numHabitaciones;
	}

	public void setNumHabitaciones(int numHabitaciones) {
		this.numHabitaciones = numHabitaciones;
	}

	public float getTarifaNoche() {
		return tarifaNoche;
	}

	public void setTarifaNoche(float tarifaNoche) {
		this.tarifaNoche = tarifaNoche;
	}

	public List<ReservaApartamento> getReservas() {
		return reservas;
	}

	public void setReservas(List<ReservaApartamento> reservas) {
		this.reservas = reservas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}