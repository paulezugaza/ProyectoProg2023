package es.deusto.ingenieria.prog3.UDExplore.domain;

public class Administrador extends Usuario{
	
    private Estancia tipoEstancia;

    // Constructor
	public Administrador(String nombreUsuario, String nombre, String apellido, String correoElectronico,
			String numeroTelefono, Estancia tipoEstancia) {
		super(nombreUsuario, nombre, apellido, correoElectronico, numeroTelefono);
		this.tipoEstancia = tipoEstancia;
	}    

    
    //Getter
	public Estancia getTipoEstancia() {
		return tipoEstancia;
	}


	//Setter
	public void setTipoEstancia(Estancia tipoEstancia) {
		this.tipoEstancia = tipoEstancia;
	}


	//Métodos
	@Override
	public String toString() {
		return "Administrador " + super.toString() + "[tipoEstancia=" + tipoEstancia + "]";
	}
    
}