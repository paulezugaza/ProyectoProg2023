package es.deusto.ingenieria.prog3.UDExplore.domain;

import java.util.Date;
import java.util.List;

public abstract class Reserva {
	
    private int numeroReserva;
    private Date fechaInicio;
    private Date fechaFin;
    
    private Cliente cliente;

    // Constructor
    public Reserva(int numeroReserva, Date fechaInicio, Date fechaFin, Cliente cliente) {
        this.numeroReserva = numeroReserva;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cliente = cliente;
    }

    // Getters
    public int getNumeroReserva() {
        return numeroReserva;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public Cliente getCliente() {
        return cliente;
    }


    public void setNumeroReserva(int numeroReserva) {
        this.numeroReserva = numeroReserva;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }



    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    

	@Override
	public String toString() {
		return "Reserva [numeroReserva=" + numeroReserva + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ ", cliente=" + cliente + "]";
	}


   
}