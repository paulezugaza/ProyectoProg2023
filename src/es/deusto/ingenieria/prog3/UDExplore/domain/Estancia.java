package es.deusto.ingenieria.prog3.UDExplore.domain;

public abstract class Estancia {

	private int id;
    private String nombre;
    private String ciudad;
    private String foto;
    
    public Estancia() {}
	public Estancia(int id, String nombre, String ciudad, String foto) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.foto = foto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
    
    
	
}
