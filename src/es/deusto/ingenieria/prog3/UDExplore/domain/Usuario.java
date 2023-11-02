package es.deusto.ingenieria.prog3.UDExplore.domain;

public class Usuario {
	
	private String nombreUsuario;
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String contraseña;
    private String numeroTelefono;
    
   
    
    public Usuario(String nombreUsuario, String nombre, String apellido, String correoElectronico, String contraseña,
			String numeroTelefono) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correoElectronico = correoElectronico;
		this.contraseña = contraseña;
		this.numeroTelefono = numeroTelefono;
	}

	// Getters
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    
    // Setters (opcional)
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    
    // Otros métodos (según necesidad)
    @Override
    public String toString() {
        return "Administrador [nombreUsuario=" + nombreUsuario + ", nombre=" + nombre + ", apellido=" + apellido + ", correoElectronico=" + correoElectronico
                + ", numeroTelefono=" + numeroTelefono + "]";
    }


	public String getContraseña() {
		return contraseña;
	}


	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
}

