package es.deusto.ingenieria.prog3.UDExplore.gui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import es.deusto.ingenieria.prog3.UDExplore.domain.Estancia;
import es.deusto.ingenieria.prog3.UDExplore.domain.Habitacion;
import es.deusto.ingenieria.prog3.UDExplore.domain.Hotel;
import es.deusto.ingenieria.prog3.UDExplore.io.Logica;

public class VentanaReservaHabitaciones extends VentanaReserva {

    private static final long serialVersionUID = 1L;
	private Habitacion habitacion;
	private Hotel esteHotel;

    public VentanaReservaHabitaciones(Habitacion habitacion) {
        super();
        this.habitacion = habitacion;
        inicializarVentana();
        JPanel pBoton = new JPanel();
		JButton bCancelar = new JButton("Cancelar");
		JButton bConfirmarDatos = new JButton("Confirmar operación");
		JPanel pInfo = new JPanel();
		pBoton.setLayout(new FlowLayout(FlowLayout.CENTER));
		pBoton.add(bCancelar);
		pBoton.add(bConfirmarDatos);
		
	      
        JLabel labelHotel = new JLabel("Hotel: ");
        JLabel labelCiudad = new JLabel("Ciudad: ");
        JLabel labelNumeroHabitacion = new JLabel("Número de Habitación: ");
        JLabel labelPrecioPorNoche = new JLabel("Precio por Noche: ");
        
        pInfo.add(labelHotel);
        pInfo.add(labelCiudad);
        pInfo.add(labelNumeroHabitacion);
        pInfo.add(labelPrecioPorNoche);
        
        add(pInfo, BorderLayout.NORTH);
        for (Estancia est : Logica.estanciasHistoricas) {
            if (est instanceof Hotel) {
                Hotel hotel = (Hotel) est;
                for (Habitacion h : hotel.getHabitaciones()) {
                    if (h.equals(habitacion)) {
                        esteHotel = hotel;
                        labelHotel.setText("Hotel: " + esteHotel.getNombre());
                        labelCiudad.setText("Ciudad: " + esteHotel.getCiudad());
                        labelNumeroHabitacion.setText("Número de Habitación: " + habitacion.getNumero());
                        labelPrecioPorNoche.setText("Precio por Noche: " + habitacion.getPrecioPorNoche() + "€");
                        break; 
                    }
                }
            }
        }


        
        bConfirmarDatos.addActionListener(new ActionListener() {
     
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			
    			String mensaje = "¡Su reserva ha sido guardada con éxito!\n\n" +
                        "Detalles de la estancia:\n" +
                        "Hotel: " + esteHotel.getNombre() + "\n" +
                        "Precio por noche: " + habitacion.getPrecioPorNoche() + "€\n";

    			JOptionPane.showMessageDialog(null, mensaje, "Reserva Exitosa", JOptionPane.INFORMATION_MESSAGE);

    			dispose();
    			
    		}
    		
    	});
    }

    private void inicializarVentana() {

        setTitle("Reserva de apartamento");
       
    }

}