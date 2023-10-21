package es.deusto.ingenieria.prog3.UDExplore.gui;


import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import es.deusto.ingenieria.prog3.UDExplore.domain.Apartamento;
import es.deusto.ingenieria.prog3.UDExplore.domain.Ciudad;
import es.deusto.ingenieria.prog3.UDExplore.domain.Estancia;
import es.deusto.ingenieria.prog3.UDExplore.domain.Hotel;



public class VentanaResultados {
	
	public class VentanaInicio  extends JFrame{
		private static final long serialVersionUID = 1L;
		
		private JTable tablaResultados;
		private DefaultTableModel modeloDatosResultados;
		private JScrollPane scrollPanePersonajes;
		private JTextField txtFiltro;

		private List<Estancia> estancias;
		
	
		
		public void JFramePrincipal(List<Estancia> estancias) {
			//Asignamos la lista de comics a la varaible local
			this.estancias = estancias;

			//Se inicializan las tablas y sus modelos de datos
			this.initTables();
			//Se cargan los comics en la tabla de comics
			this.loadEstancias();
			
			//La tabla de comics se inserta en un panel con scroll
			JScrollPane scrollPaneComics = new JScrollPane(this.tablaResultados);
			scrollPaneComics.setBorder(new TitledBorder("Resultados:"));
			this.tablaResultados.setFillsViewportHeight(true);
			
		
			//Se define el comportamiento del campo de texto del filtro
			this.txtFiltro = new JTextField(20);	
			this.txtFiltro.getDocument().addDocumentListener((DocumentListener) new DocumentListener() {

				@Override
				public void insertUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					loadEstancias();
					
					
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					loadEstancias();
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					
					
				}
				
			});;
		}
		private void initTables() {
			Vector<String> cabeceraResultados = new Vector<String>(Arrays.asList( "NOMBRE", "TPO de ESTANCIA","CIUDAD", "TARIFA/NOCHE", "CATEGORIA"));
			this.modeloDatosResultados = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraResultados);
			//Se crea la tabla de comics con el modelo de datos
			this.tablaResultados = new JTable(this.modeloDatosResultados) {
				private static final long serialVersionUID = 1L;
			
			
				
				public boolean isCellEditable(int row, int column) {
					if (column == 0 || column == 3) {
						return false;
					}else {
						return true;
					}
				}
			};
			TableCellRenderer cellRenderer = (table, value, isSelected, hasFocus, row, column) -> {
				JLabel result = new JLabel(value.toString());
							
				//Si el valor es de tipo Editorial: se renderiza con la imagen centrada
				if (value instanceof Hotel) {
					result.setText("Hotel");	
					result.setHorizontalAlignment(JLabel.CENTER);
				}else if (value instanceof Apartamento){
					result.setText("Hotel");
	
				//Si el valor es numérico se renderiza centrado
				} else if (value instanceof Number) {
					result.setHorizontalAlignment(JLabel.CENTER);
				} else {
					try {
						Integer.parseInt(value.toString());
						result.setHorizontalAlignment(JLabel.CENTER);				
					} catch(Exception ex) {
						result.setText(value.toString());
					}		
				}
			
				result.setOpaque(true);
				
				return result;
			};
			//Se crea un CellEditor a partir de un JComboBox()
			JComboBox<Ciudad> jComboEditorial = new JComboBox<>(Ciudad.values());		
			DefaultCellEditor editorialEditor = new DefaultCellEditor(jComboEditorial);
			
			//Se define la altura de las filas de la tabla de comics
			this.tablaResultados.setRowHeight(26);
			
			//Se deshabilita la reordenación de columnas
			this.tablaResultados.getTableHeader().setReorderingAllowed(false);
			//Se deshabilita el redimensionado de las columna
			this.tablaResultados.getTableHeader().setResizingAllowed(false);
			//Se definen criterios de ordenación por defecto para cada columna
			this.tablaResultados.setAutoCreateRowSorter(true);
			
			
			//Se establece el editor específico para la Editorial		
			this.tablaResultados.getColumnModel().getColumn(1).setCellEditor(editorialEditor);
			
			//Se define la anchura de la columna Título
			this.tablaResultados.getColumnModel().getColumn(2).setPreferredWidth(400);
			
			//Se modifica el modelo de selección de la tabla para que se pueda selecciona únicamente una fila
			this.tablaResultados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			//Se define el comportamiento el evento de selección de una fila de la tabla
			
			
			}
		
	

		private void loadEstancias() {
			//Se borran los datos del modelo de datos
			this.modeloDatosResultados.setRowCount(0);
			//Se añaden los comics uno a uno al modelo de datos
			this.estancias.forEach(c -> {
				if (c.getNombre().contains(txtFiltro.getText())|| txtFiltro.getText().isBlank()) {
					this.modeloDatosResultados.addRow(new Object[] {c.getNombre(), c.getClass().toString(),c.getCiudad(), c.getTarifaNoche()});
				}
			}
				
			
			);
		}}
		

}
