package es.deusto.ingenieria.prog3.UDExplore.domain;

import java.util.List;

public class Cliente extends Usuario{
	
	private List<Reserva> reservas;

	public Cliente(int codigoUsuario, String nombreUsuario, String apellido, String correoElectronico,
			String contrasenya) {
		super(codigoUsuario, nombreUsuario, apellido, correoElectronico, contrasenya);
	}
	
	public Cliente(String nombreUsuario, String apellido, String correoElectronico,
			String contrasenya) {
		super(nombreUsuario, apellido, correoElectronico, contrasenya);
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	
	 
 
}