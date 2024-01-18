package es.deusto.ingenieria.prog3.UDExplore.gui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import es.deusto.ingenieria.prog3.UDExplore.domain.Cliente;
import es.deusto.ingenieria.prog3.UDExplore.domain.Habitacion;
import es.deusto.ingenieria.prog3.UDExplore.domain.Hotel;
import es.deusto.ingenieria.prog3.UDExplore.domain.ReservaHotel;
import es.deusto.ingenieria.prog3.UDExplore.io.BaseDeDatos;
import es.deusto.ingenieria.prog3.UDExplore.io.Logica;

public class VentanaReservaHabitaciones extends JFrame {

    private static final long serialVersionUID = 1L;
	private Habitacion habitacion;
	private Hotel esteHotel;

    public VentanaReservaHabitaciones(Habitacion habitacion) {
        super();
        this.habitacion = habitacion;
        inicializarVentana();
        this.setSize(500, 200);
        JPanel pBoton = new JPanel();
		JButton bCancelar = new JButton("Cancelar");
		JButton bConfirmarDatos = new JButton("Confirmar operacion");
		JPanel pInfo = new JPanel();
		pBoton.setLayout(new FlowLayout(FlowLayout.CENTER));
		setLocationRelativeTo(null);
		pBoton.add(bCancelar);
		pBoton.add(bConfirmarDatos);
		
	      
        JLabel labelHotel = new JLabel("Hotel: ");
        JLabel labelCiudad = new JLabel("Ciudad: ");
        JLabel labelNumeroHabitacion = new JLabel("Numero de Habitacion: ");
        JLabel labelPrecioPorNoche = new JLabel("Precio por Noche: ");
        
        pInfo.add(labelHotel);
        pInfo.add(labelCiudad);
        pInfo.add(labelNumeroHabitacion);
        pInfo.add(labelPrecioPorNoche);
        
        add(pInfo, BorderLayout.NORTH);
        add(pBoton, BorderLayout.SOUTH);
 
    	   			
        Hotel esteHotel = BaseDeDatos.getHotelPorHabitacion(habitacion.getId());
        labelHotel.setText("Hotel: " + esteHotel.getNombre());
        labelCiudad.setText("Ciudad: " + esteHotel.getCiudad());
        labelNumeroHabitacion.setText("Numero de Habitacion: " + habitacion.getNumero());
        labelPrecioPorNoche.setText("Precio total: " + habitacion.getPrecioPorNoche()* Logica.calcularDiferenciaEnDias(Logica.fechaIni,Logica.fechaFin)  + "€");
        
        bCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	dispose();
            }
            
            });

        
        bConfirmarDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 if (Logica.fechaIni == null || Logica.fechaFin == null) {
                     JOptionPane.showMessageDialog(null, "Por favor, seleccione las fechas de inicio y fin.", "Error", JOptionPane.ERROR_MESSAGE);
                     return; 
                 }else {
                	 try {
                   
	                    int id = BaseDeDatos.anyadirReserva(Logica.fechaIni, Logica.fechaFin, Logica.usuario.getCodigoUsuario());
	                    habitacion.addReserva(new ReservaHotel(id, Logica.fechaIni, Logica.fechaFin,(Cliente) Logica.usuario));
	                   
	                    String mensaje = "¡Su reserva ha sido guardada con Exito!\n\n" +
	                            "Detalles de la estancia:\n" +
	                            "Hotel: " + BaseDeDatos.getHotelPorHabitacion(habitacion.getId()).getNombre() + "\n" +
	                            "Precio total: " + habitacion.getPrecioPorNoche()* Logica.calcularDiferenciaEnDias(Logica.fechaIni, Logica.fechaFin)+ "€\n";
	                    
	
	                   
	                    JOptionPane.showMessageDialog(null, mensaje, "Reserva Exitosa", JOptionPane.INFORMATION_MESSAGE);
	
	                    
	                    dispose();

                } catch (Exception ex) {
                   
                    ex.printStackTrace(); 
                    JOptionPane.showMessageDialog(null, "Error al realizar la reserva", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            }
        });

    }

    private void inicializarVentana() {

        setTitle("Reserva de habitación");
       
    }

}