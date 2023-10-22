package es.deusto.ingenieria.prog3.UDExplore.gui;

import java.awt.Component;
import java.util.ArrayList;
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
    

    public VentanaResultados(List<Estancia> estancias) {
    	
    	this.estancias = estancias;
    	
        
    

        setTitle("Página de Estancias");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear y configurar la tabla
        initTables();

        // Crear y configurar el campo de filtro
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

        // Crear y configurar el botón "Volver al Inicio"
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

        // Agregar componentes a la ventana
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

        tablaResultados.getColumnModel().getColumn(0).setPreferredWidth(200);
        tablaResultados.getColumnModel().getColumn(1).setPreferredWidth(100);
        tablaResultados.getColumnModel().getColumn(2).setPreferredWidth(150);
        tablaResultados.getColumnModel().getColumn(3).setPreferredWidth(100);
        tablaResultados.getColumnModel().getColumn(4).setCellRenderer(new imageRenderer());
    }


 


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
                        e.getFoto()
                });
            }
        });
    }
    
    public static void main(String[] args) {
    	
       
        
    }
}


