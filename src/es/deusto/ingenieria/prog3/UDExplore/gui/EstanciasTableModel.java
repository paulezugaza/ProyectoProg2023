package es.deusto.ingenieria.prog3.UDExplore.gui;

import java.util.Arrays;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;


public class EstanciasTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	
	private final List<String> headers = Arrays.asList(
			"NOMBRE", 
			"CIUDAD", 
			"Nº DE HABITACIONES", 
			"TARIFA POR NOCHE", 
			"CATEGORIA/PUNTUACION"
		);

}