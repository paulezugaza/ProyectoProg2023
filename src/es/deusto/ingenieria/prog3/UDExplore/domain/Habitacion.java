package es.deusto.ingenieria.prog3.UDExplore.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class Habitacion implements Serializable{
	
    private static final long serialVersionUID = 1L;
	private int numero;
    private int capacidadMaxima;
    private double precioPorNoche;

 

    // Constructor
    public Habitacion( int capacidadMaxima, double precioPorNoche) {
        this.capacidadMaxima = capacidadMaxima;
        this.precioPorNoche = precioPorNoche;
   
    }

    // Getters
    public int getNumero() {
        return numero;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public double getPrecioPorNoche() {
        return precioPorNoche;
    }

  

    // Setters 
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public void setPrecioPorNoche(double precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }


}