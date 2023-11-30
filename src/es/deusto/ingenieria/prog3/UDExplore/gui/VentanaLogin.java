package es.deusto.ingenieria.prog3.UDExplore.gui;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import es.deusto.ingenieria.prog3.UDExplore.domain.Cliente;
import es.deusto.ingenieria.prog3.UDExplore.io.Logica;


public class VentanaLogin extends JFrame {
	private static final long serialVersionUID = 1L;
	
	
	private JLabel email;
	private JTextField txtEmail;
	private JLabel contrasenya;
	private JPasswordField txtContrasenya;
	Cliente cliente;
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
				if(!txtEmail.getText().equals("") && !txtContrasenya.getText().equals("")) {
					if(Logica.existeUsuario(txtEmail.getText())) {
						if(Logica.usuarioCorrecto(txtEmail.getText(), txtContrasenya.getText())){
							if(Logica.esUsuarioCliente(txtEmail.getText())){
								dispose();
								new VentanaInicio();
								
							}else {
								//EN ESTA LUEGO HAY QUE PONER LA DE ADMIN
								new VentanaInicio();
								dispose();
							}
							}else JOptionPane.showMessageDialog(null, "ERROR: Contraseña incorrecta. Vuelva a intentarlo");
						}else JOptionPane.showMessageDialog(null, "ERROR: No existe ninguna cuenta con ese email. REGISTRESE");
					}else JOptionPane.showMessageDialog(null, "ERROR: Rellene todos los datos");
				txtEmail.setText("");
				txtContrasenya.setText("");
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