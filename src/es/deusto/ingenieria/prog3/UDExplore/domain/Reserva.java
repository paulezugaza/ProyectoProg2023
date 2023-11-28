package es.deusto.ingenieria.prog3.UDExplore.domain;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Reserva {
	
	
    private int numeroReserva;
    private Date fechaInicio;
    private Date fechaFin;
    private Cliente cliente;



	// Constructor
    public Reserva(Date fechaInicio, Date fechaFin, Cliente cliente) {
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

	public static Reserva parseCSV(String data) throws Exception {
		try {
			String[] fields = data.split("#");	
			Date fechaIni = new SimpleDateFormat("dd/MM/yyyy").parse(fields[0]);
			Date fechaFin = new SimpleDateFormat("dd/MM/yyyy").parse(fields[1]);
			String[] parts = fields[2].split(", ");

	        
	        String nombreUsuario = parts[0].substring(parts[0].indexOf("=") + 1);
	        String correoElectronico = parts[1].substring(parts[1].indexOf("=") + 1);
	        String contraseña = parts[2].substring(parts[2].indexOf("=") + 1);
	        int admin = Integer.parseInt(parts[3].substring(parts[3].indexOf("=") + 1, parts[3].length() - 1));

	         

			return new Reserva(fechaIni,
					 			fechaFin,
					 			new Cliente(nombreUsuario, correoElectronico, contraseña, admin));			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(String.format("%s from CSV error: %s", Reserva.class, data));
		}
	}

   
}