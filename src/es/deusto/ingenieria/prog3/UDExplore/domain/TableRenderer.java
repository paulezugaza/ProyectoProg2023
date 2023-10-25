package es.deusto.ingenieria.prog3.UDExplore.domain;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import es.deusto.ingenieria.prog3.UDExplore.gui.VentanaReserva;

public class TableRenderer implements TableCellRenderer{
	
	

	private static final Estancia Estancia = null;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		JLabel label = new JLabel();
		//El color de fondo es el color por defecto de la tabla
		label.setBackground(table.getBackground());
		//Por defecto el label se centra
		label.setHorizontalAlignment(JLabel.CENTER);
		if (column == 6) {
			
			if (isSelected) {
				VentanaReserva v= new VentanaReserva(Estancia);
			}
		}
		return null;
	}

}
