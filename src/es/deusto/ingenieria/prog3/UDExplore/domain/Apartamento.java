package es.deusto.ingenieria.prog3.UDExplore.domain;


import java.util.List;

public class Apartamento extends Estancia{
	

	public Apartamento(int id, String nombre, Ciudad ciudad, int numeroHabitaciones, double tarifaNoche, String foto,
			double puntuacion) {
		super(id, nombre, ciudad, numeroHabitaciones, tarifaNoche, foto);
		this.puntuacion = puntuacion;
=======
	private double puntuacion; 

	//Constructor
	public Apartamento(String nombre, Ciudad ciudad, int numeroHabitaciones, double tarifaNoche, String foto, double puntuacion) {
		super(nombre, ciudad, numeroHabitaciones,  tarifaNoche, foto);
		this.puntuacion =  puntuacion;

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
=======
>>>>>>> branch 'master' of git@github.com:paulezugaza/PruebaRepo.git
	
	
	//Getter
	public Double getPuntuacion() {
		return puntuacion;
	}
	
	
	//Setter
	public void setPuntuacion(Float puntuacion) {
		this.puntuacion = puntuacion;
	}

	
	//Métodos
	@Override
	public String toString() {
		return "Apartamento " + super.toString() + "[puntuacion=" + puntuacion + "]";
	}	

}
