package es.deusto.ingenieria.prog3.UDExplore.gui;

import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import es.deusto.ingenieria.prog3.UDExplore.domain.Ciudad;
import es.deusto.ingenieria.prog3.UDExplore.domain.Estancia;
import es.deusto.ingenieria.prog3.UDExplore.domain.Hotel;
import es.deusto.ingenieria.prog3.UDExplore.domain.Apartamento;
import es.deusto.ingenieria.prog3.UDExplore.domain.CadenaHotelera;

public class VentanaResultados extends JFrame {
    private DefaultTableModel modeloDatosResultados;
    private JTextField txtFiltro;
    private JTable tablaResultados;
    private JScrollPane scrollPaneEstancias;
    private JButton filtros;
    private List<Estancia> estancias;
    private JComboBox<String> comboBoxOrdenarPor;
    

    public VentanaResultados(List<Estancia> estancias) {
    	
    	this.estancias = estancias;
    	
    	int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
		int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
		this.setSize(anchoP, altoP);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("RESULTADOS");
      

        
        initTables();

      
        txtFiltro = new JTextField(20);
        txtFiltro.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                loadEstancias();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                loadEstancias();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

     
        JButton btnVolverInicio = new JButton("Volver al Inicio");

        btnVolverInicio.addActionListener(e -> {
        	VentanaInicio ventana= new VentanaInicio(); 
			dispose();
		
        });
        
        JButton bFiltros = new JButton("Filtros");
        bFiltros.addActionListener(e -> {
        	VentanaFiltros ventana = new VentanaFiltros(this);
        	dispose();
        	ventana.setVisible(true);
        });
        
        JPanel pCombo = new JPanel();
        JComboBox<String> comboBoxOrden = new JComboBox<>();
        comboBoxOrden.addItem("Ordenar por:");
        comboBoxOrden.addItem("De menor precio a mayor");
        comboBoxOrden.addItem("De mayor precio a menor");
        comboBoxOrden.addItem("De mejor puntuación a peor");
        
        comboBoxOrden.addActionListener( e -> {
        	String seleccionado = comboBoxOrden.getSelectedItem().toString();
        	
        	if(seleccionado.equals("De menor precio a mayor")) {
        		estancias.sort((estancia1,estancia2)->Double.compare(estancia1.getTarifaNoche(), estancia2.getTarifaNoche()));
        	}
        	else if(seleccionado.equals("De mayor precio a menor")) {
        		estancias.sort((estancia1,estancia2)->Double.compare(estancia2.getTarifaNoche(), estancia1.getTarifaNoche()));
        	}
        	
        	loadEstancias();
        

        });
        pCombo.add(comboBoxOrden);
        
        
       
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnVolverInicio);
        panelBotones.add(bFiltros);

        JPanel panelBuscador = new JPanel();
        panelBuscador.add(new JLabel("Buscar: "));
        panelBuscador.add(txtFiltro);

        scrollPaneEstancias = new JScrollPane(tablaResultados);
        scrollPaneEstancias.setBorder(new TitledBorder("Resultados de Estancias"));

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.add(panelBotones);
        panelPrincipal.add(pCombo);
        panelPrincipal.add(panelBuscador);
        panelPrincipal.add(scrollPaneEstancias);
        
       

        add(panelPrincipal);
        loadEstancias();

        // Configurar el cierre de la ventana
        setVisible(true);
    }

    private void initTables() {
        Vector<String> cabeceraResultados = new Vector<>();
        cabeceraResultados.add("Nombre");
        cabeceraResultados.add("Tipo de Estancia");
        cabeceraResultados.add("Ciudad");
        cabeceraResultados.add("Número de Habitaciones");
        cabeceraResultados.add("Tarifa por Noche");
        cabeceraResultados.add("Imagen");

        modeloDatosResultados = new DefaultTableModel(new Vector<>(), cabeceraResultados);
        tablaResultados = new JTable(modeloDatosResultados);
        tablaResultados.setRowHeight(100);

        tablaResultados.getColumnModel().getColumn(0).setPreferredWidth(200);
        tablaResultados.getColumnModel().getColumn(1).setPreferredWidth(100);
        tablaResultados.getColumnModel().getColumn(2).setPreferredWidth(150);
        tablaResultados.getColumnModel().getColumn(3).setPreferredWidth(100);
        tablaResultados.getColumnModel().getColumn(4).setPreferredWidth(100);
    }
    
    TableCellRenderer imageRenderer = (table, value, isSelected, hasFocus, row, column) -> {
        JLabel result = new JLabel();
        
       
        if (value instanceof String) {
            String imagePath = (String) value;
            
            
            ImageIcon imageIcon = new ImageIcon(imagePath);
            
            
            if (imageIcon.getIconWidth() > 100 || imageIcon.getIconHeight() > 100) {
                imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
            }
            
          
            result.setIcon(imageIcon);
        }
        
        return result;
    };
 


    private void loadEstancias() {
        modeloDatosResultados.setRowCount(0);

        String filtro = txtFiltro.getText().toLowerCase();


		estancias.forEach(e -> {
            if (e.getNombre().toLowerCase().contains(filtro) || filtro.isEmpty()) {
                modeloDatosResultados.addRow(new Object[]{
                        e.getNombre(),
                        e.getClass().getSimpleName(),
                        e.getCiudad(),
                        e.getNumeroHabitaciones(),
                        e.getTarifaNoche(),
                });
            }
        });
    }
    
    public static void main(String[] args) {
    	
       
        
    }
}


