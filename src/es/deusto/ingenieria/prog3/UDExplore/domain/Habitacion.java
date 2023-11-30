package es.deusto.ingenieria.prog3.UDExplore.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Habitacion implements Serializable{
	
    private static final long serialVersionUID = 1L;
    
    private int id;
	private int numero;
    private int capacidadMaxima;
    private double precioPorNoche;
    
    private Hotel hotel;
    private List<ReservaHotel> reservas;
 

    public Habitacion(int id, int numero, int capacidadMaxima, double precioPorNoche) {
		super();
		this.id = id;
		this.numero = numero;
		this.capacidadMaxima = capacidadMaxima;
		this.precioPorNoche = precioPorNoche;
	}

    
    
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public List<ReservaHotel> getReservas() {
		return reservas;
	}

	public void setReservas(List<ReservaHotel> reservas) {
		this.reservas = reservas;
	}

	public void addReserva(ReservaHotel reserva) {
		if(reservas == null) {
			reservas = new ArrayList<>();
		}
		reservas.add(reserva);
	}
    

}