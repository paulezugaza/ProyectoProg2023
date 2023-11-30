package es.deusto.ingenieria.prog3.UDExplore.domain;

import java.util.ArrayList;
import java.util.List;

public class CadenaHotelera {
	
	private int id;
	private String nombre;

	
	public CadenaHotelera(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	

}
