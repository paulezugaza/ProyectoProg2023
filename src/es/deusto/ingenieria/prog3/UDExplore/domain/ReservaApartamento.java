package es.deusto.ingenieria.prog3.UDExplore.domain;

import java.util.Date;
import java.util.List;

public class ReservaApartamento extends Reserva{

	private List<Apartamento> apartamentos;
	
	public ReservaApartamento(int numeroReserva, Date fechaInicio, Date fechaFin, Cliente cliente) {
		super(numeroReserva, fechaInicio, fechaFin, cliente);
	}

	public List<Apartamento> getApartamentos() {
		return apartamentos;
	}

	public void setApartamentos(List<Apartamento> apartamentos) {
		this.apartamentos = apartamentos;
	}
	
	
	
}
