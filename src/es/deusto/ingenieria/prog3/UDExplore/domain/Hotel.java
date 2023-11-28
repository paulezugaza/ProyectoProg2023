package es.deusto.ingenieria.prog3.UDExplore.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hotel extends Estancia implements Serializable {



    private static final long serialVersionUID = 1L;
    private int id=0;
	private CadenaHotelera cadenaHotelera;
    private List<Habitacion> habitaciones;
    private HashMap<Habitacion, List<Reserva>> mapaReservasHabitacion;
    
    



	public Hotel(String nombre, Ciudad ciudad, int categoria, int numeroHabitaciones, double tarifaNoche, String foto,
			Map<Cliente, List<Reserva>> reservas, CadenaHotelera cadenaHotelera, List<Habitacion> habitaciones ) {
		super(nombre, ciudad, categoria, numeroHabitaciones, tarifaNoche, foto, reservas);
	
		    this.reservas = reservas;
		    this.cadenaHotelera = cadenaHotelera;
		    this.habitaciones = habitaciones;
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

    
   

	public CadenaHotelera getCadenaHotelera() {
		return cadenaHotelera;
	}
	
	
	
	
	public List<Habitacion> getHabitaciones() {
		return habitaciones;
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
		return "Hotel " + super.toString() +  ", cadenaHotelera=" + cadenaHotelera + 
				", habitaciones=" + habitaciones + "]";
	}

	@Override
	public double calcularPrecioTotal(int numNoches) {
		return super.calcularPrecioTotal(numNoches);
	}

	public void actualizarMapaReservas(Habitacion habitacion, Reserva reserva) {
	    	mapaReservasHabitacion.putIfAbsent(habitacion, new ArrayList<Reserva>());
	    	mapaReservasHabitacion.get(habitacion).add(reserva);
	    	
	 }


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}




}


