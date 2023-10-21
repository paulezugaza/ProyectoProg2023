package es.deusto.ingenieria.prog3.UDExplore.domain;

import java.util.ArrayList;
import java.util.List;

public class Hotel extends Estancia {
	
	
    public Hotel(String nombre, Ciudad ciudad, int numeroHabitaciones, double tarifaNoche, int categoria,
			CadenaHotelera cadenaHotelera, List<Habitacion> habitaciones) {
		super(nombre, ciudad, numeroHabitaciones, tarifaNoche);
		this.categoria = categoria;
		this.cadenaHotelera = cadenaHotelera;
		this.habitaciones = habitaciones;
	}


	


	private int categoria; 
    private CadenaHotelera cadenaHotelera;
    private List<Habitacion> habitaciones;
	
	



	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return super.getNombre();
	}

	
	@Override
	public int getNumeroHabitaciones() {
		// TODO Auto-generated method stub
		return super.getNumeroHabitaciones();
	}

	@Override
	public double getTarifaNoche() {
		// TODO Auto-generated method stub
		return super.getTarifaNoche();
	}


	public int getCategoria() {
		return categoria;
	}


	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}


	@Override
	public void setNombre(String nombre) {
		// TODO Auto-generated method stub
		super.setNombre(nombre);
	}


	@Override
	public void setNumeroHabitaciones(int numeroHabitaciones) {
		// TODO Auto-generated method stub
		super.setNumeroHabitaciones(numeroHabitaciones);
	}

	@Override
	public void setTarifaNoche(double tarifaNoche) {
		// TODO Auto-generated method stub
		super.setTarifaNoche(tarifaNoche);
	}

	
	@Override
	public double calcularPrecioTotal(int numNoches) {
		// TODO Auto-generated method stub
		return super.calcularPrecioTotal(numNoches);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}





	public CadenaHotelera getCadenaHotelera() {
		return cadenaHotelera;
	}


	public void setCadenaHotelera(CadenaHotelera cadenaHotelera) {
		this.cadenaHotelera = cadenaHotelera;
	}
	public List<Habitacion> getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(List<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}

	

}

