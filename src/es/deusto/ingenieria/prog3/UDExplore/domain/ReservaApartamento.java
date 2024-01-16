package es.deusto.ingenieria.prog3.UDExplore.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservaApartamento extends Reserva{

	private Apartamento apartamento;
	
	public ReservaApartamento(int numeroReserva, Date fechaInicio, Date fechaFin, Cliente cliente) {
		super(numeroReserva, fechaInicio, fechaFin, cliente);
	}

	public ReservaApartamento(Date fechaInicio, Date fechaFin, Cliente cliente) {
		super(fechaInicio, fechaFin, cliente);
	}

	public Apartamento getApartamento() {
		return apartamento;
	}

	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}
	
	
	
}
