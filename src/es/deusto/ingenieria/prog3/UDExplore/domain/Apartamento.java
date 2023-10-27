package es.deusto.ingenieria.prog3.UDExplore.domain;

public class Apartamento extends Estancia{
	
	private int id;
	private String nombre;
	private Ciudad ciudad;
	private int numeroHabitaciones;
	private double tarifaNoche;
	private String foto;
	private double puntuacion;
	

	public Apartamento(int id, String nombre, Ciudad ciudad, int numeroHabitaciones, double tarifaNoche, String foto,
			double puntuacion) {
		super(id, nombre, ciudad, numeroHabitaciones, tarifaNoche, foto, isDisponible());
		this.puntuacion = puntuacion;
	}

	@Override
	public String getNombre() {
		return super.getNombre();
	}


	@Override
	public int getNumeroHabitaciones() {
		return super.getNumeroHabitaciones();
	}

	@Override
	public double getTarifaNoche() {
		return super.getTarifaNoche();
	}

	@Override
	public void setNombre(String nombre) {
		super.setNombre(nombre);
	}

	@Override
	public void setNumeroHabitaciones(int numeroHabitaciones) {
		super.setNumeroHabitaciones(numeroHabitaciones);
	}

	@Override
	public void setTarifaNoche(double tarifaNoche) {
		super.setTarifaNoche(tarifaNoche);
	}

	@Override
	public double calcularPrecioTotal(int numNoches) {
		return super.calcularPrecioTotal(numNoches);
	}


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