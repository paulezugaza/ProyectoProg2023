package es.deusto.ingenieria.prog3.UDExplore.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public abstract class VentanaReserva extends JDialog {
	
    private static final long serialVersionUID = 1L;
    private JTextField nombre;
    private JTextField numTarjeta;
    private JTextField fechaCaducidad;
  

	public VentanaReserva() {
		
		
		setTitle("Reserva de estancia");
		setSize(800,400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JPanel pHotelInfo = new JPanel(new BorderLayout());
		Font nuevaFuente = new Font("Arial", Font.ROMAN_BASELINE, 17);
		JLabel labelReser = new JLabel("Desea reservar esta estancia?", SwingConstants.CENTER);
        JLabel hotelInfoLabel = new JLabel("");
        hotelInfoLabel.setFont(nuevaFuente);
        labelReser.setFont(nuevaFuente);
        pHotelInfo.add(hotelInfoLabel, BorderLayout.NORTH);
   
        JPanel pHotelFoto = new JPanel(new BorderLayout());
        ImageIcon hotelImage = new ImageIcon("");
        JLabel hotelImageLabel = new JLabel(new ImageIcon(hotelImage.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH)));
        pHotelFoto.add(hotelImageLabel, BorderLayout.CENTER);

        pHotelInfo.add(pHotelFoto, BorderLayout.CENTER);
        
		JPanel pPrincipal = new JPanel(new GridLayout(4,2,10,10));
		add(pPrincipal);
		
		pPrincipal.add(new JLabel("Titular de la tarjeta:"));
		nombre = new JTextField(20);
		pPrincipal.add(nombre);
		
		pPrincipal.add(new JLabel("Numero de tarjeta:"));
		numTarjeta = new JTextField(20);
		numTarjeta.addKeyListener(new KeyAdapter() {
			public void  keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
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
		
		setLayout(new BorderLayout());
	    
		add(pHotelInfo, BorderLayout.NORTH); 
	    add(pPrincipal, BorderLayout.CENTER);
	    add(pBoton, BorderLayout.SOUTH); 
		
		
		bCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
			
		});
		
		
		
	}
	
	
	

}
