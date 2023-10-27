package es.deusto.ingenieria.prog3.UDExplore.domain;

import java.util.List;

public class Hotel extends Estancia {


	private int categoria; 
    private CadenaHotelera cadenaHotelera;
    private List<Habitacion> habitaciones;
	

	public Hotel(int id, String nombre, Ciudad ciudad, int numeroHabitaciones, double tarifaNoche, String foto,
			int categoria, CadenaHotelera cadenaHotelera, List<Habitacion> habitaciones) {
		super(id, nombre, ciudad, numeroHabitaciones, tarifaNoche, foto, isDisponible());
		this.categoria = categoria;
		this.cadenaHotelera = cadenaHotelera;
		this.habitaciones = habitaciones;
	}
   

	@Override
	public int getId() {
		return super.getId();
	}

	@Override
	public void setId(int id) {
		super.setId(id);
	}

	@Override
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

    
    //Getters
	public int getCategoria() {
		return categoria;
	}

	public CadenaHotelera getCadenaHotelera() {
		return cadenaHotelera;
	}
	
	public List<Habitacion> getHabitaciones() {
		return habitaciones;
	}
	
	
	//Setter
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public void setCadenaHotelera(CadenaHotelera cadenaHotelera) {
			this.cadenaHotelera = cadenaHotelera;
	}
	
	public void setHabitaciones(List<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}
	
	
	//Métodos
	
	@Override
	public String toString() {
		return "Hotel " + super.toString() + ", categoria=" + categoria + ", cadenaHotelera=" + cadenaHotelera + 
				", habitaciones=" + habitaciones + "]";
	}

	@Override
	public double calcularPrecioTotal(int numNoches) {
		return super.calcularPrecioTotal(numNoches);
	}	

}


