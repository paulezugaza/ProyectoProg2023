package es.deusto.ingenieria.prog3.UDExplore.domain;

public abstract class Usuario {
	int codigoUsuario =0;
	private String nombreUsuario;
    private String correoElectronico;
    private String contraseña;
    private int admin;
    
   
    
    public Usuario(String nomUsuario, String email, String contrasenya, int admin) {
		super();
		this.nombreUsuario = nomUsuario;
		this.correoElectronico = email;
		this.contraseña = contrasenya;
		this.setAdmin(admin);
	}

	

	// Getters
    
    public int getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
    public String getNombreUsuario() {
        return nombreUsuario;
    }



    public String getCorreoElectronico() {
        return correoElectronico;
    }

  


    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }


    


	public String getContraseña() {
		return contraseña;
	}


	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "Usuario [nombreUsuario=" + nombreUsuario + ", correoElectronico=" + correoElectronico + ", contraseña="
				+ contraseña + ", admin=" + admin + "]";
	}
}

