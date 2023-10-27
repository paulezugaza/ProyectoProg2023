package es.deusto.ingenieria.prog3.UDExplore.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import es.deusto.ingenieria.prog3.UDExplore.domain.Estancia;

public class VentanaReserva extends JDialog {
	
    private static final long serialVersionUID = 1L;
	Estancia estancia;
    private JTextField nombre;
    private JTextField numTarjeta;
    private JTextField fechaCaducidad;
	
	public VentanaReserva(Estancia estancia) {
		this.estancia =estancia;
		
		
		setTitle("Reserva de estancia");
		setSize(800,400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JPanel pHotelInfo = new JPanel(new BorderLayout());
        JLabel hotelInfoLabel = new JLabel("Hotel: " + estancia.getNombre() + " | Precio por noche: " + estancia.getTarifaNoche() + "€", SwingConstants.CENTER);
        pHotelInfo.add(hotelInfoLabel, BorderLayout.CENTER);
        
        JPanel pHotelFoto = new JPanel(new BorderLayout());
        ImageIcon hotelImage = new ImageIcon(estancia.getFoto()); // Reemplaza con la ubicación de tu imagen
        JLabel hotelImageLabel = new JLabel(new ImageIcon(hotelImage.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH)));
        pHotelFoto.add(hotelImageLabel, BorderLayout.CENTER);

        pHotelInfo.add(pHotelFoto, BorderLayout.NORTH);
		
		JPanel pPrincipal = new JPanel(new GridLayout(4,2,10,10));
		add(pPrincipal);
		
		pPrincipal.add(new JLabel("Titular de la tarjeta:"));
		nombre = new JTextField(20);
		pPrincipal.add(nombre);
		
		pPrincipal.add(new JLabel("Numero de tarjeta:"));
		numTarjeta = new JTextField(20);
		pPrincipal.add(numTarjeta);
		
		pPrincipal.add(new JLabel("Fecha de caducidad:"));
		fechaCaducidad = new JTextField(6);
		pPrincipal.add(fechaCaducidad);
		
		JPanel pBoton = new JPanel();
		JButton bCancelar = new JButton("Cancelar");
		JButton bConfirmarDatos = new JButton("Confirmar operación");
		pBoton.setLayout(new FlowLayout(FlowLayout.CENTER));
		pBoton.add(bCancelar);
		pBoton.add(bConfirmarDatos);
		pBoton.add(bConfirmarDatos, BorderLayout.CENTER);

		
		add(pHotelInfo, BorderLayout.NORTH); 
	    add(pPrincipal, BorderLayout.CENTER);
	    add(pBoton, BorderLayout.SOUTH); 
		
		
		bCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
			
		});
		
		bConfirmarDatos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String mensaje = "¡Su reserva ha sido guardada con éxito!\n\n" +
                        "Detalles de la estancia:\n" +
                        "Hotel: " + estancia.getNombre() + "\n" +
                        "Precio por noche: " + estancia.getTarifaNoche() + "€\n";

				JOptionPane.showMessageDialog(null, mensaje, "Reserva Exitosa", JOptionPane.INFORMATION_MESSAGE);

				dispose();
				
			}
			
		});
		
	}
	
	

}
