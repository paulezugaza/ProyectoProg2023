package es.deusto.ingenieria.prog3.UDExplore.gui;


import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import es.deusto.ingenieria.prog3.UDExplore.domain.Cliente;
import es.deusto.ingenieria.prog3.UDExplore.domain.Habitacion;
import es.deusto.ingenieria.prog3.UDExplore.domain.Hotel;
import es.deusto.ingenieria.prog3.UDExplore.domain.ReservaHotel;
import es.deusto.ingenieria.prog3.UDExplore.io.BaseDeDatos;
import es.deusto.ingenieria.prog3.UDExplore.io.Logica;

public class VentanaHabitaciones extends JFrame {
    private static final long serialVersionUID = 8210065439199121917L;
    private DefaultTableModel modeloDatosHabitaciones;
    private JTable tablaHabitaciones;
    private JScrollPane scrollPaneHabitaciones;
    private Hotel hotel;
    
    private Date inicio;
    private Date fin;

    private VentanaInicio ventanaInicio;

    private List<Habitacion> habitaciones = new ArrayList<>();
    
    public VentanaHabitaciones(Hotel hotel, Date inicio, Date fin) {
        this.setHotel(hotel);
        this.inicio = inicio;
        this.fin = fin;
        
        habitaciones = BaseDeDatos.getHabitaciones(hotel.getId());
        

        int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
        this.setSize(anchoP, altoP);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Habitaciones de " + hotel.getNombre());

        initTables();

        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBorder(new TitledBorder("Información del Hotel"));

        JLabel labelNombre = new JLabel("Nombre: " + hotel.getNombre());
        JLabel labelCategoria = new JLabel("Categoria:"+ hotel.getCategoria()+ " estrellas");
        JLabel labelCadena = new JLabel("Cadena hotelera:"+ hotel.getCadenaHotelera().getNombre());
        JLabel labelUbicacion = new JLabel("Ubicacion: " + hotel.getCiudad());

        JPanel panelBotones = new JPanel();
        
        JButton btnVolverInicio = new JButton("Volver al Inicio");
        ventanaInicio = new VentanaInicio();

        btnVolverInicio.addActionListener(e -> {
        	ventanaInicio.setVisible(true); 
			dispose();
		
        });
        
        JButton btnVolverAnterior = new JButton("Volver a la anterior");
        btnVolverAnterior.addActionListener(e ->{
        	dispose();
        	
        });
      
        panelInfo.add(labelNombre);
        panelInfo.add(labelUbicacion);
        panelInfo.add(labelCategoria);
        panelInfo.add(labelCadena);

       
        panelBotones.add(btnVolverInicio);
        panelBotones.add(btnVolverAnterior);

        scrollPaneHabitaciones = new JScrollPane(tablaHabitaciones);
        scrollPaneHabitaciones.setBorder(new TitledBorder("Habitaciones del Hotel"));

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(panelInfo, BorderLayout.NORTH);
        panelPrincipal.add(scrollPaneHabitaciones, BorderLayout.CENTER);

        
        add(panelBotones, BorderLayout.NORTH);
        add(panelPrincipal, BorderLayout.CENTER);
        loadHabitaciones(habitaciones);
        
        setVisible(true);
      
    }

    private void initTables() {
        Vector<String> cabeceraHabitaciones = new Vector<>();
        cabeceraHabitaciones.add("Número");
        cabeceraHabitaciones.add("Capacidad Máxima");
        cabeceraHabitaciones.add("Precio por Noche");
        cabeceraHabitaciones.add("Precio total");
        cabeceraHabitaciones.add("Reservar");


        modeloDatosHabitaciones = new DefaultTableModel(new Vector<>(), cabeceraHabitaciones);
        tablaHabitaciones = new JTable(modeloDatosHabitaciones);
        tablaHabitaciones.setDefaultEditor(Object.class, null);
        tablaHabitaciones.setRowHeight(20);
        

        tablaHabitaciones.getColumnModel().getColumn(0).setPreferredWidth(100);
        tablaHabitaciones.getColumnModel().getColumn(1).setPreferredWidth(100);
        tablaHabitaciones.getColumnModel().getColumn(2).setPreferredWidth(150);
        tablaHabitaciones.getColumnModel().getColumn(3).setPreferredWidth(150);
        tablaHabitaciones.getColumnModel().getColumn(4).setPreferredWidth(150);
        
        
            
        tablaHabitaciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int col = tablaHabitaciones.getSelectedColumn();
                    int filaSeleccionada = tablaHabitaciones.getSelectedRow();

                    if (filaSeleccionada != -1 && col == 4) {
                        Habitacion habitacionSeleccionada = habitaciones.get(filaSeleccionada);

                        if (Logica.usuario != null) {
                            if (Logica.fechaIni == null || Logica.fechaFin == null) {
                            	JOptionPane.showMessageDialog(null, "No has seleccionado fechas. Por favor, selecciónalas antes de hacer una reserva.", "Advertencia", JOptionPane.WARNING_MESSAGE);

                           
                            	WindowListener windowListener = new WindowAdapter() {
                            	    @Override
                            	    public void windowClosed(WindowEvent e) {
                            	       
                            	        SwingUtilities.invokeLater(() -> new SeleccionFechas());
                            	    }
                            	};
                            	JOptionPane.getRootFrame().addWindowListener(windowListener);

                            } else {
                                ReservaHotel reserva = new ReservaHotel(inicio, fin, (Cliente) Logica.usuario);
                                reserva.setHabitacion(habitacionSeleccionada);
                                BaseDeDatos.anyadirHabitacion(reserva);

                                new VentanaReservaHabitaciones(habitacionSeleccionada).setVisible(true);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No has iniciado sesión. Por favor, inicia sesión.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }

        });


        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int i = 0; i < tablaHabitaciones.getColumnCount(); i++) {
            tablaHabitaciones.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            JTableHeader header = tablaHabitaciones.getTableHeader();
            header.getColumnModel().getColumn(i).setHeaderRenderer(centerRenderer);
        }
    }

    private void loadHabitaciones(List<Habitacion> habitaciones) {
   
    	
        modeloDatosHabitaciones.setRowCount(0);

        habitaciones.forEach(h -> {
        	
            modeloDatosHabitaciones.addRow(new Object[]{
                    h.getNumero(),
                    h.getCapacidadMaxima(),
                    h.getPrecioPorNoche() + "€",
                    h.getPrecioPorNoche()* Logica.calcularDiferenciaEnDias(Logica.fechaIni, Logica.fechaFin) + "€", 
                    "Reservar"
            });
        });
    }

    public static void main(String[] args) {
    	
    }

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}


	
}
