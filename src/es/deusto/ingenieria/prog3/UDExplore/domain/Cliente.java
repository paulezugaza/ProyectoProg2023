package es.deusto.ingenieria.prog3.UDExplore.domain;

public class Cliente {
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String numeroTelefono;
    private String direccion;
    private String ciudad;
    private String pais;

    // Constructor
    public Cliente(String nombre, String apellido, String correoElectronico, String numeroTelefono, String direccion, String ciudad, String pais) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.numeroTelefono = numeroTelefono;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.pais = pais;
    }

    // Getters
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
        return "Cliente [nombre=" + nombre + ", apellido=" + apellido + ", correoElectronico=" + correoElectronico
                + ", numeroTelefono=" + numeroTelefono + ", direccion=" + direccion + ", ciudad=" + ciudad + ", pais=" + pais + "]";
    }
}