package es.deusto.ingenieria.prog3.UDExplore.domain;

public class Cliente extends Usuario{
 
    private String direccion;
    private String ciudad;
   

    // Constructor  
    public Cliente(String nombreUsuario, String nombre, String apellido, String correoElectronico,
			String numeroTelefono, String direccion, String ciudad, String pais) {
		super(nombreUsuario, nombre, apellido, correoElectronico, numeroTelefono);
		this.direccion = direccion;
		this.ciudad = ciudad;
		
	}
    
    
    // Getters
    public String getDireccion() {
        return direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

   

    // Setters (opcional)
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    
	
    // Otros métodos (según necesidad)
	@Override
	public String toString() {
		return "Cliente" + super.toString() + "[direccion=" + direccion + ", ciudad=" + ciudad + "]";
	}
 
}