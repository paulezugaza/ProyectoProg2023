package es.deusto.ingenieria.prog3.UDExplore.domain;

public class Cliente extends Usuario{
 
    private String direccion;
    private String ciudad;
    private String pais;

    // Constructor  
    public Cliente(String nombreUsuario, String nombre, String apellido, String correoElectronico,
			String numeroTelefono, String direccion, String ciudad, String pais) {
		super(nombreUsuario, nombre, apellido, correoElectronico, numeroTelefono);
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.pais = pais;
	}
    
    
    // Getters
    public String getDireccion() {
        return direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getPais() {
        return pais;
    }

    // Setters (opcional)
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

	
    // Otros métodos (según necesidad)
	@Override
	public String toString() {
		return "Cliente" + super.toString() + "[direccion=" + direccion + ", ciudad=" + ciudad + ", pais=" + pais + "]";
	}
 
}