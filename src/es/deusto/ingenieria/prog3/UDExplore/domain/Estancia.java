package es.deusto.ingenieria.prog3.UDExplore.domain;

import java.util.ArrayList;
import java.util.List;

public class Estancia  {
    private String nombre;
    private Ciudad ciudad;
    private int numeroHabitaciones;
    private double tarifaNoche;
   

    


    public Estancia(String nombre, Ciudad ciudad, int numeroHabitaciones, double tarifaNoche) {
		super();
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.numeroHabitaciones = numeroHabitaciones;
		this.tarifaNoche = tarifaNoche;
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
        return "Hotel [nombre=" + nombre + ", ciudad=" + ciudad + ", numeroHabitaciones=" + numeroHabitaciones
                + ", tarifaNoche=" + tarifaNoche + "]";
    }



	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}



}