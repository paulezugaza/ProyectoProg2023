package es.deusto.ingenieria.prog3.UDExplore.domain;

public abstract class Usuario {
	
	private int codigoUsuario;
	private String nombreUsuario;
	private String apellido;
    private String correoElectronico;
    private String contrasenya;
    

	public Usuario(int codigoUsuario, String nombreUsuario, String apellido, String correoElectronico,
			String contrasenya) {
		super();
		this.codigoUsuario = codigoUsuario;
		this.nombreUsuario = nombreUsuario;
		this.apellido = apellido;
		this.correoElectronico = correoElectronico;
		this.contrasenya = contrasenya;
	}

	public Usuario(String nombreUsuario, String apellido, String correoElectronico,
			String contrasenya) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.apellido = apellido;
		this.correoElectronico = correoElectronico;
		this.contrasenya = contrasenya;
	}

	public int getCodigoUsuario() {
		return codigoUsuario;
	}



	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}





	public String getNombreUsuario() {
		return nombreUsuario;
	}





	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}





	public String getApellido() {
		return apellido;
	}





	public void setApellido(String apellido) {
		this.apellido = apellido;
	}





	public String getCorreoElectronico() {
		return correoElectronico;
	}





	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}





	public String getContrasenya() {
		return contrasenya;
	}





	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}





	@Override
	public String toString() {
		return "Usuario [nombreUsuario=" + nombreUsuario + ", correoElectronico=" + correoElectronico + ", contraseña="
				+ contrasenya + "]";
	}
}

