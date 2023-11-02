package es.deusto.ingenieria.prog3.UDExplore.domain;

public class Administrador extends Usuario{
	
	private Estancia estanciaAdmin;

	
	public Administrador(String nombreUsuario, String nombre, String apellido, String correoElectronico,
			String contraseña, String numeroTelefono, Estancia estanciaAdmin) {
		super(nombreUsuario, nombre, apellido, correoElectronico, contraseña, numeroTelefono);
		this.estanciaAdmin = estanciaAdmin;
	}

	public Estancia getEstanciaAdmin() {
		return estanciaAdmin;
	}

	public void setEstanciaAdmin(Estancia estanciaAdmin) {
		this.estanciaAdmin = estanciaAdmin;
	}

	@Override
	public String toString() {
		return "Administrador: " + super.toString() + "[estanciaAdmin=" + estanciaAdmin + "]";
	}	

}
