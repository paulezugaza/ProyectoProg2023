package es.deusto.ingenieria.prog3.UDExplore.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Hotel extends Estancia implements Serializable {

    private static final long serialVersionUID = 1L;
  
	private int categoria;
    
	private CadenaHotelera cadenaHotelera;
    private List<Habitacion> habitaciones;
    
	
	public Hotel(int id, String nombre, String ciudad, String foto, int categoria) {
		super(id, nombre, ciudad, foto);
		this.categoria = categoria;
		this.habitaciones = new ArrayList<>();
	}
	
	
	public Hotel() {
		
	}


	public int getCategoria() {
		return categoria;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
	public void addHabitacion(Habitacion habitacion) {
		if(habitaciones == null) {
			habitaciones = new ArrayList<>();
		}
		habitaciones.add(habitacion);
	}
	
	public int getNumHabitaciones() {
		return habitaciones.size();
	}
	
}


