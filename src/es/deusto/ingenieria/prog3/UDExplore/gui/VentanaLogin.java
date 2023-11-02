package es.deusto.ingenieria.prog3.UDExplore.gui;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class VentanaLogin extends JFrame {
	private static final long serialVersionUID = 1L;

	
	private JLabel email;
	private JTextField txtEmail;
	private JLabel contrasenya;
	private JPasswordField txtContrasenya;
	public VentanaLogin() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10)); 
        
      
        email = new JLabel("Email: ");
		contrasenya = new JLabel("Contraseña: ");
		txtEmail = new JTextField(20);
		txtContrasenya = new JPasswordField(20);
		JPanel panelCentral = new JPanel();
		JPanel botonera = new JPanel();
		panelCentral.setLayout(new GridLayout(2, 2, 10, 10));  

        
    	panelCentral.add(email);
    	panelCentral.add(txtEmail);
    	panelCentral.add(contrasenya);
    	panelCentral.add(txtContrasenya);
    	
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtEmail.getText();
                txtContrasenya.getPassword();
                	
                isEmpty();

            }
        });
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	VentanaInicio ventanaInicio = new VentanaInicio();
            	ventanaInicio.setVisible(true);
                dispose();
                
         }

			
        });
        
        JButton btnRegistrarse = new JButton("Registrarse");
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	VentanaRegistro ventanaRegis = new VentanaRegistro();
                ventanaRegis.setVisible(true);
                dispose();         
            }
        });
        
        botonera.add(btnRegistrarse);
        botonera.add(btnCerrar);
        botonera.add(btnEntrar);
        
		add(panelCentral, BorderLayout.CENTER);
		add(botonera, BorderLayout.SOUTH);

		setVisible(true);
		
	

	}
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaLogin();
            }
        });
    }

	private boolean isEmpty() {
		return (txtEmail.getText().equals("") || (String.valueOf(txtContrasenya.getPassword()).equals(""))
				|| (String.valueOf(txtContrasenya.getPassword()).equals("")));
	
	}

}