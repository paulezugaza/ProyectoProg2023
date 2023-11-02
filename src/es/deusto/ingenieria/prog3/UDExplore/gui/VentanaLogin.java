package es.deusto.ingenieria.prog3.UDExplore.gui;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class VentanaLogin extends JFrame {
	private static final long serialVersionUID = 1L;

	JFrame frameLog = new JFrame("Inicio de sesión");
	private JLabel email;
	private JTextField txtEmail;
	private JLabel contrasenya;
	private JPasswordField txtContrasenya;
	private JButton registrarse;
	private JPanel panelCentral;
	private JPanel botonera;
	

	// Componentes del registro, de momento ocultos
	private JLabel infoCuenta;
	private JLabel nombreRegistro;
	private JTextField txtnombreRegistro;
	private JLabel emailRegistro;
	private JTextField txtemailRegistro;
	private JLabel contrasenyaRegistro;
	private JTextField txtcontrasenyaRegistro;
	private JButton guardarDatos;
	
	private JPanel datosCuenta;
	private JPanel botoneraRegistro;
	
	public VentanaLogin() {
		JFrame frameLog = new JFrame("Iniciar Sesión");
        frameLog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLog.setSize(600, 300);
        frameLog.setLocationRelativeTo(null);
        frameLog.setLayout(new BorderLayout(10, 10)); 
        
        // Componentes del Log In
        email = new JLabel("Email: ");
		contrasenya = new JLabel("Contraseña: ");
		txtEmail = new JTextField(25);
		txtContrasenya = new JPasswordField(25);
		registrarse = new JButton("Registrarse");
		JPanel panelCentral = new JPanel();
		JPanel botonera = new JPanel();
		panelCentral.setLayout(new GridLayout(2, 2, 2, 2));  
		infoCuenta = new JLabel("DATOS DE LA CUENTA:");
        
    	panelCentral.add(email);
    	panelCentral.add(txtEmail);
    	panelCentral.add(contrasenya);
    	panelCentral.add(txtContrasenya);
    	
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText();
                char[] cont = txtContrasenya.getPassword();
                	
                isEmpty();

            }
        });
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	VentanaInicio ventanaInicio = new VentanaInicio();
                ventanaInicio.setVisible(true);
                frameLog.dispose();
                
         }

			private void dispose() {
				this.dispose();
				
			}
        });
        
        JButton btnRegistrarse = new JButton("Registrarse");
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	VentanaRegistro ventanaRegis = new VentanaRegistro();
                ventanaRegis.setVisible(true);
                frameLog.dispose();         
            }
        });
        
        botonera.add(btnRegistrarse);
        botonera.add(btnCerrar);
        botonera.add(btnEntrar);
        
		frameLog.add(panelCentral, BorderLayout.CENTER);
		frameLog.add(botonera, BorderLayout.SOUTH);

		frameLog.setVisible(true);
		
		setVisible(true);
	

//		this.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				System.out.println("Cerrando");
//				System.exit(0);
//			}
//		});

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