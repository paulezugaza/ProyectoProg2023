package es.deusto.ingenieria.prog3.UDExplore.domain;

import java.util.Date;
import java.util.List;

public class ReservaHotel extends Reserva{

	private List<Habitacion> habitaciones;

	public ReservaHotel(int numeroReserva, Date fechaInicio, Date fechaFin, Cliente cliente) {
		super(numeroReserva, fechaInicio, fechaFin, cliente);
	}

	public List<Habitacion> getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(List<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}
	
	
}
