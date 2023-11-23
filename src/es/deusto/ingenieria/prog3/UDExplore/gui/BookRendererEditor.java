package es.deusto.ingenieria.prog3.UDExplore.gui;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import es.deusto.ingenieria.prog3.UDExplore.domain.Habitacion;


public class BookRendererEditor extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
	private static final long serialVersionUID = 1L;

	private Habitacion habitacion;
	private VentanaHabitaciones ventanaHabitaciones;
	
	public BookRendererEditor(VentanaHabitaciones ventanaHabitaciones) {
		this.ventanaHabitaciones = ventanaHabitaciones;
	}
	
	private JButton prepare(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		habitacion = (Habitacion) value;
		
		JButton button = new JButton("Reservar");
		button.setEnabled(true);
		button.setToolTipText(String.format("Reservar - %s", habitacion.getNumero()));				
		button.setBackground(table.getBackground());
		
		if (isSelected || hasFocus) {
			button.setBackground(table.getSelectionBackground());
		}
		
		button.addActionListener((e) -> {
			VentanaReserva vr = new VentanaReserva(habitacion);
			
		});
		
		button.setOpaque(true);
		
		return button;
	}
	
	


    @Override
    public boolean shouldSelectCell(EventObject event) {
        return true;
    }

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		// TODO Auto-generated method stub
		return null;
	}
}