package es.deusto.ingenieria.prog3.UDExplore.domain;

public class Habitacion {
	
    private int numero;
    private int capacidadMaxima;
    private double precioPorNoche;
    private boolean reservada;

    // Constructor
    public Habitacion(int numero, int capacidadMaxima, double precioPorNoche) {
        this.numero = numero;
        this.capacidadMaxima = capacidadMaxima;
        this.precioPorNoche = precioPorNoche;
        this.reservada = false;
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

    public boolean isReservada() {
        return reservada;
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

    public void setReservada(boolean reservada) {
        this.reservada = reservada;
    }

    // Otros métodos 
    @Override
    public String toString() {
        return "Habitacion [Número: " + numero + ", Capacidad Máxima: " + capacidadMaxima + " personas, Precio por Noche: " + precioPorNoche + "€, Reservada: " + reservada + "]";
    }
}