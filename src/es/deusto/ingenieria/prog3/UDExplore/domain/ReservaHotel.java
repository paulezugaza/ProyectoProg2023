package es.deusto.ingenieria.prog3.UDExplore.domain;

import java.util.Date;
import java.util.List;

public class ReservaHotel extends Reserva{

	private Habitacion habitacion;

	public ReservaHotel(int numeroReserva, Date fechaInicio, Date fechaFin, Cliente cliente) {
		super(numeroReserva, fechaInicio, fechaFin, cliente);
	}

	public ReservaHotel(Date fechaInicio, Date fechaFin, Cliente cliente) {
		super(fechaInicio, fechaFin, cliente);
	}
	
	public Habitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	
	
	
}
