package es.deusto.ingenieria.prog3.UDExplore.domain;

import java.util.List;

public class Apartamento  extends Estancia{
	
	public Apartamento(int id, String nombre, Ciudad ciudad, int numeroHabitaciones, double tarifaNoche, String foto,
			double puntuacion) {
		super(id, nombre, ciudad, numeroHabitaciones, tarifaNoche, foto);
		this.puntuacion = puntuacion;
	}

	private double puntuacion; 

	


	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return super.getNombre();
	}


	@Override
	public int getNumeroHabitaciones() {
		// TODO Auto-generated method stub
		return super.getNumeroHabitaciones();
	}

	@Override
	public double getTarifaNoche() {
		// TODO Auto-generated method stub
		return super.getTarifaNoche();
	}

	@Override
	public void setNombre(String nombre) {
		// TODO Auto-generated method stub
		super.setNombre(nombre);
	}



	@Override
	public void setNumeroHabitaciones(int numeroHabitaciones) {
		// TODO Auto-generated method stub
		super.setNumeroHabitaciones(numeroHabitaciones);
	}

	@Override
	public void setTarifaNoche(double tarifaNoche) {
		// TODO Auto-generated method stub
		super.setTarifaNoche(tarifaNoche);
	}

	@Override
	public double calcularPrecioTotal(int numNoches) {
		// TODO Auto-generated method stub
		return super.calcularPrecioTotal(numNoches);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	





	@Override
	public Ciudad getCiudad() {
		// TODO Auto-generated method stub
		return super.getCiudad();
	}

	@Override
	public void setCiudad(Ciudad ciudad) {
		// TODO Auto-generated method stub
		super.setCiudad(ciudad);
	}

	public Double getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Float puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	

}
