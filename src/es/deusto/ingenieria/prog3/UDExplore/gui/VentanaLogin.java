package es.deusto.ingenieria.prog3.UDExplore.gui;

import javax.swing.JFrame;

	

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

	

		public class VentanaLogin extends JFrame{
			
			private static final long serialVersionUID = 1L;
			
				//Componentes del Log In
				private JLabel email;
				private JTextField txtemail;
				private JLabel contrasenya;
				private JPasswordField txtcontrasenya;
				private JButton registrarse;
				private JButton entrar;
				private JPanel panelCentral;
				private JPanel botonera;
				
				//Componentes del registro, de momento ocultos
				private JLabel infoCuenta;
				private JLabel nombreRegistro;
				private JTextField txtnombreRegistro;
				private JLabel emailRegistro;
				private JTextField txtemailRegistro;
				private JLabel contrasenyaRegistro;
				private JTextField txtcontrasenyaRegistro;
				private JPanel datosCuenta;
				private JButton guardarDatos;
				
				private JPanel botoneraRegistro;
				
				public VentanaLogin()  {
					inicializar();
				}
				
				private void inicializar() {
					JFrame v = this;
					
					email= new JLabel("Email: ");
					contrasenya= new JLabel("Contraseña: ");
					txtemail= new JTextField(25);
					txtcontrasenya= new JPasswordField(25);
					entrar= new JButton("Entrar");
					panelCentral= new JPanel();
					botonera= new JPanel();
					entrar.setFont(new Font("Serif", Font.PLAIN, 20));
					infoCuenta=new JLabel("DATOS DE LA CUENTA:");
					
					
					panelCentral.add(email);
					panelCentral.add(txtemail);
					panelCentral.add(contrasenya);
					panelCentral.add(txtcontrasenya);
					botonera.add(entrar);
					
					entrar.addActionListener(new ActionListener() {
				            @Override
				            public void actionPerformed(ActionEvent e) {
				                new VentanaInicio();
				            }
				      });
				
					setSize(500,200);
					setLocationRelativeTo(null);
					setTitle("INICIO DE SESIÓN");
					getContentPane().setLayout(new GridLayout(2,1));
					panelCentral.setLayout(new GridLayout(2,2));
					this.add(panelCentral);
					this.add(botonera);
					setVisible(true);
					
					this.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							System.out.println("Cerrando");
							System.exit(0);
						}
					});
		
	}
	
	
	

}
