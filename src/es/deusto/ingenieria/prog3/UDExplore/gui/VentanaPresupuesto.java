package es.deusto.ingenieria.prog3.UDExplore.gui;

import javax.swing.*;
import java.awt.GridBagConstraints;

import javax.swing.table.DefaultTableModel;

import es.deusto.ingenieria.prog3.UDExplore.domain.Apartamento;
import es.deusto.ingenieria.prog3.UDExplore.domain.Habitacion;
import es.deusto.ingenieria.prog3.UDExplore.domain.Hotel;
import es.deusto.ingenieria.prog3.UDExplore.io.BaseDeDatos;
import es.deusto.ingenieria.prog3.UDExplore.io.Logica;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map; 

public class VentanaPresupuesto extends JFrame {
    private JTextField presupuestoTextField;
    private JCheckBox hotelCheckBox;
    private JCheckBox apartamentoCheckBox;
    private JButton buscarButton;
    private JTable resultadosTable;
    private VentanaInicio ventanaInicio;

    public VentanaPresupuesto() {
        setTitle("Ventana presupuesto");
        int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
        this.setSize(anchoP, altoP);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        

   
        JLabel mensajeLabel = new JLabel("Nosotros te encontramos todas las opciones personalizadas a tu presupuesto.");
        JLabel presupuestoLabel = new JLabel("Indique su presupuesto máximo:");
        presupuestoTextField = new JTextField(10);
        presupuestoTextField.setMaximumSize(new Dimension(150, Integer.MAX_VALUE));
        hotelCheckBox = new JCheckBox("Hotel");
        apartamentoCheckBox = new JCheckBox("Apartamento");
        buscarButton = new JButton("Buscar");


        setLayout(new BorderLayout());


        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        JButton volverButton = new JButton("Volver");

        
        panelPrincipal.add(volverButton, gbc);

       
        ventanaInicio = new VentanaInicio();

		volverButton.addActionListener(e -> {
			ventanaInicio.setVisible(true);
			dispose();

		});
        panelPrincipal.add(volverButton, gbc);
        gbc.gridy++;
        panelPrincipal.add(mensajeLabel, gbc);
        gbc.gridy++;
        panelPrincipal.add(presupuestoLabel, gbc);
        gbc.gridy++;
        panelPrincipal.add(presupuestoTextField, gbc);
        gbc.gridy++;
        panelPrincipal.add(createCheckBoxPanel(hotelCheckBox, apartamentoCheckBox), gbc);
        gbc.gridy++;
        panelPrincipal.add(buscarButton, gbc);

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double presupuesto = Double.parseDouble(presupuestoTextField.getText());
                boolean esHotel = hotelCheckBox.isSelected();
                boolean esApartamento = apartamentoCheckBox.isSelected();

                if (esHotel && !esApartamento) {
                	List<Habitacion> habitaciones = Logica.getAllHabitaciones();
                	if (habitaciones == null) {
                	    JOptionPane.showMessageDialog(VentanaPresupuesto.this, "Error al obtener las habitaciones.");
                	    return;
                	}
                    Map<Habitacion, Integer>  opcionesHabitaciones = Logica.obtenerOpcionesHabitacionesRecursivo(presupuesto, Logica.getAllHabitaciones());
                    System.out.println(opcionesHabitaciones);
                  
                    mostrarResultadosEnTabla(opcionesHabitaciones);
                } else if (esApartamento && !esHotel) {
                	List<Apartamento> apartamentos = BaseDeDatos.cargarApartamentos();
                	if (apartamentos == null) {
                	    JOptionPane.showMessageDialog(VentanaPresupuesto.this, "Error al cargar los apartamentos.");
                	    return;
                	}
                    Map<Apartamento, Integer> opcionesApartamentos = Logica.buscarOpcionesApartamentosRecursivo(presupuesto, BaseDeDatos.cargarApartamentos());

       
                    mostrarResultadosEnTablaApartamentos(opcionesApartamentos);
                } else {
                 
                    JOptionPane.showMessageDialog(VentanaPresupuesto.this, "Por favor, seleccione solo una opción.");
                    return; 
                }
            }
        });
        
        

        

        // Tabla para mostrar resultados
        resultadosTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(resultadosTable);

        // Añadir todo al contenido principal
        add(panelPrincipal, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    // Método para crear un panel con CheckBox centrados
    private JPanel createCheckBoxPanel(JCheckBox checkBox1, JCheckBox checkBox2) {
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        checkBoxPanel.add(checkBox1);
        checkBoxPanel.add(checkBox2);
        return checkBoxPanel;
    }
    
    private void mostrarResultadosEnTabla(Map<Habitacion, Integer> opcionesHabitaciones) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Hotel");
        model.addColumn("Número de habitación");
        model.addColumn("Noches");

        for (Habitacion h : opcionesHabitaciones.keySet()) {
            Hotel hotel = BaseDeDatos.getHotelPorHabitacion(h.getId());
            System.out.println("Hotel: " + hotel); // Agrega esta línea
            System.out.println("Habitación: " + h); 
       
            if (hotel == null) {
                JOptionPane.showMessageDialog(VentanaPresupuesto.this, "Error al obtener el hotel para la habitación.");
                return;
            }

            Object[] rowData = {hotel.getNombre(), h.getNumero(), opcionesHabitaciones.get(h)};
            model.addRow(rowData);
        }

        resultadosTable.setModel(model);
        JOptionPane.showMessageDialog(VentanaPresupuesto.this, "Resultados obtenidos");
    }

    
    private void mostrarResultadosEnTablaApartamentos(Map<Apartamento, Integer> opcionesApartamentos) {
    	 DefaultTableModel model = (DefaultTableModel) resultadosTable.getModel();
         model.setRowCount(0); 

         for (Apartamento a : opcionesApartamentos.keySet()) {
          
             String[] rowData = {a.getNombre(), a.getCiudad(),opcionesApartamentos.get(a).toString() };
             model.addRow(rowData);
         }

     
        JOptionPane.showMessageDialog(VentanaPresupuesto.this, "Resultados obtenidos");
    }

    public static void main(String[] args) {
        new VentanaPresupuesto();
        
    }
}

