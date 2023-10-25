package es.deusto.ingenieria.prog3.UDExplore.domain;

import java.util.ArrayList;
import java.util.List;

public class Hotel extends Estancia {
<<<<<<< HEAD

	private int categoria; 
    private CadenaHotelera cadenaHotelera;
    private List<Habitacion> habitaciones;
	
	

	

	public Hotel(int id, String nombre, Ciudad ciudad, int numeroHabitaciones, double tarifaNoche, String foto,
			int categoria, CadenaHotelera cadenaHotelera, List<Habitacion> habitaciones) {
		super(id, nombre, ciudad, numeroHabitaciones, tarifaNoche, foto);
		this.categoria = categoria;
		this.cadenaHotelera = cadenaHotelera;
		this.habitaciones = habitaciones;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}

	@Override
	public void setId(int id) {
		// TODO Auto-generated method stub
		super.setId(id);
	}

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


=======
	
	private int categoria; 
    private CadenaHotelera cadenaHotelera;
    private List<Habitacion> habitaciones;
    
    
    //Constructor
    public Hotel(String nombre, Ciudad ciudad, int numeroHabitaciones, double tarifaNoche, String foto, int categoria, 
			CadenaHotelera cadenaHotelera, List<Habitacion> habitaciones) {
		super(nombre, ciudad, numeroHabitaciones, tarifaNoche, foto);
		this.categoria = categoria;
		this.cadenaHotelera = cadenaHotelera;
		this.habitaciones = habitaciones;
	}


    //Getters
>>>>>>> branch 'master' of git@github.com:paulezugaza/PruebaRepo.git
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
		// TODO Auto-generated method stub
		return super.calcularPrecioTotal(numNoches);
	}	

}

