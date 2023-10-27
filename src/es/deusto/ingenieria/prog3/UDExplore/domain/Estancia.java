package es.deusto.ingenieria.prog3.UDExplore.domain;

public class Estancia  {

	private int id;

    private String nombre;
    private Ciudad ciudad;
    private int numeroHabitaciones;
    private double tarifaNoche;
    private String foto;
   

    public Estancia() {
    	
    }

    //Constructor
    public Estancia(int id, String nombre, Ciudad ciudad, int numeroHabitaciones, double tarifaNoche, String foto) {
		super();
		this.id=id;
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.numeroHabitaciones = numeroHabitaciones;
		this.tarifaNoche = tarifaNoche;
		this.setFoto(foto);
	}


	// Getters
    public String getNombre() {
        return nombre;
    }

    public int getNumeroHabitaciones() {
        return numeroHabitaciones;
    }

    public double getTarifaNoche() {
        return tarifaNoche;
    }
  

    // Setters 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }  

    public void setNumeroHabitaciones(int numeroHabitaciones) {
        this.numeroHabitaciones = numeroHabitaciones;
    }

    public void setTarifaNoche(double tarifaNoche) {
        this.tarifaNoche = tarifaNoche;
    }
    
 
    // Otros métodos 
    public double calcularPrecioTotal(int numNoches) {
        return numNoches * tarifaNoche;
    }

    @Override
    public String toString() {
        return "Nombre=" + nombre + ", ciudad=" + ciudad + ", numeroHabitaciones=" + numeroHabitaciones
                + ", tarifaNoche=" + tarifaNoche + "]";
    }


	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}


	public String getFoto() {
		return foto;
	}


	public void setFoto(String foto) {
		this.foto = foto;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}


}