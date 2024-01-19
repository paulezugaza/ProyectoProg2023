package es.deusto.ingenieria.prog3.UDExplore.domain;

import java.util.Date;

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

//	public static Reserva parseCSV(String csvLine) {
//		String[] res = csvLine.split(",");
//		
//		if (res.length != 4) {
//			throw new Exception("Linea no válida de CSV");
//		}
//		
//		try {
//			int numeroReserva = Integer.parseInt(res[0].trim());
//	        Date fechaInicio = new SimpleDateFormat("dd/MM/yyyy").parse(res[1].trim());
//	        Date fechaFin = new SimpleDateFormat("dd/MM/yyyy").parse(res[2].trim());
////	        String cliente = cliente.toString();
//	        
//	        return new Reserva(numeroReserva, fechaInicio, fechaFin, cliente);
//		} catch(NumberFormatException | ParseException e) {
//			throw new Exception("Error al parserar la linea CSV");
//		}
//	}
//   
}