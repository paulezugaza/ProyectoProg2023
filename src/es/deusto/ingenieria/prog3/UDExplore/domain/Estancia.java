package es.deusto.ingenieria.prog3.UDExplore.domain;

import java.io.Serializable;

public class Estancia  implements Serializable {

	private static final long serialVersionUID = 1L;

	int codigoHotel =0;

    private String nombre;
    private Ciudad ciudad;
    private int numeroHabitaciones;
    private double tarifaNoche;
    private String foto;
    public boolean disponible;
	private int categoria;
   

    public Estancia() {
    	
    }

    public Estancia( String nombre, Ciudad ciudad, int categoria,  int numeroHabitaciones, double tarifaNoche, String foto, boolean disponible) {
		super();
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.setCategoria(categoria);
		this.numeroHabitaciones = numeroHabitaciones;
		this.tarifaNoche = tarifaNoche;
		this.setFoto(foto);
		this.disponible = disponible;
	}


    public String getNombre() {
        return nombre;
    }

    public int getNumeroHabitaciones() {
        return numeroHabitaciones;
    }

    public double getTarifaNoche() {
        return tarifaNoche;
    }
  

    public static boolean isDisponible() {
    	return true;
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
    
 
    public void setDisponible() {
    	this.disponible = disponible;
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




	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}


}