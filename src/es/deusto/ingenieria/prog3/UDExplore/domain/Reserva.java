package es.deusto.ingenieria.prog3.UDExplore.domain;

import java.util.Date;

public class Reserva {
	
    private int numeroReserva;
    private Date fechaInicio;
    private Date fechaFin;
    private Estancia tipo;
    private Cliente cliente;

    // Constructor
    public Reserva(int numeroReserva, Date fechaInicio, Date fechaFin, Estancia tipo, Cliente cliente) {
        this.numeroReserva = numeroReserva;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipo = tipo;
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

    public Estancia getTipo() {
        return tipo;
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

    public void setHotel(Estancia hotel) {
        this.tipo = tipo;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    @Override
    public String toString() {
        return "Reserva [numeroReserva=" + numeroReserva + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
                + ", tipo=" + tipo.getNombre() + ", cliente=" + cliente.getNombre() + " " + cliente.getApellido() + "]";
    }
}