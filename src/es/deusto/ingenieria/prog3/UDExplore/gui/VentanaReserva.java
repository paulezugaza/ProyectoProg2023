package es.deusto.ingenieria.prog3.UDExplore.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import es.deusto.ingenieria.prog3.UDExplore.domain.Estancia;

public class VentanaReserva extends JDialog {
	
    private  Estancia estancia;
    private JTextField nombre;
    private JTextField numTarjeta;
	
	public VentanaReserva(Estancia estancia) {
		this.estancia =estancia;
		
		setTitle("Reserva de estancia");
		setSize(400,200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel pPrincipal = new JPanel(new GridLayout(3,2));
		add(pPrincipal);
		
		pPrincipal.add(new JLabel("Titular de la tarjeta:"));
		nombre = new JTextField();
		pPrincipal.add(nombre);
		
		pPrincipal.add(new JLabel("Numero de tarjeta:"));
		nombre = new JTextField();
		pPrincipal.add(numTarjeta);
		
		pPrincipal.add(new JLabel("Fecha de caducidad:"));
		nombre = new JTextField();
		pPrincipal.add(numTarjeta);
		
		
		JButton bConfirmarDatos = new JButton("Confirmar operación");
		pPrincipal.add(bConfirmarDatos, BorderLayout.SOUTH);
		
		
	}
	
	

}
