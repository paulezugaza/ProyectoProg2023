package es.deusto.ingenieria.prog3.UDExplore.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistro extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame frame = new JFrame("Registro de usuario");
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JPasswordField txtContrasenya;
	private JPasswordField txtContrasenyaRep;
	
    public VentanaRegistro() {
        frame = new JFrame("Registro de usuario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));  
 
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(8, 2, 5, 5));  

        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();
        JLabel lblApellidos = new JLabel("Apellidos:");
        txtApellidos = new JTextField();
        JLabel lblTelefono = new JLabel("Número de Teléfono:");
        JTextField txtTelefono = new JTextField();
        JLabel lblDireccion = new JLabel("Direccion:");
        JTextField txtDireccion = new JTextField();
        JLabel lblCiudad = new JLabel("Ciudad:");
        JTextField txtCiudad = new JTextField();
        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();
        JLabel lblContrasenya = new JLabel("Contraseña:"); 
        txtContrasenya = new JPasswordField();
        JLabel lblContrasenyaRep = new JLabel("Confrima tu contraseña:");
        txtContrasenyaRep = new JPasswordField();
       

        inputPanel.add(lblNombre);
        inputPanel.add(txtNombre);
        inputPanel.add(lblApellidos);
        inputPanel.add(txtApellidos);
        inputPanel.add(lblDireccion);
        inputPanel.add(txtDireccion);
        inputPanel.add(lblCiudad);
        inputPanel.add(txtCiudad);
        inputPanel.add(lblTelefono);
        inputPanel.add(txtTelefono);
        inputPanel.add(lblEmail);
        inputPanel.add(txtEmail);
        inputPanel.add(lblContrasenya);
        inputPanel.add(txtContrasenya);
        inputPanel.add(lblContrasenyaRep);
        inputPanel.add(txtContrasenyaRep);

 
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));  

        JButton btnRegistrar = new JButton("Registrarse");
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNombre.getText();
                txtApellidos.getText();
                txtEmail.getText();
                txtTelefono.getText();
                txtContrasenya.getPassword();
                txtContrasenyaRep.getPassword();
                	
                checkFields();

            }
        });

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	VentanaInicio ventanaInicio = new VentanaInicio();
                ventanaInicio.setVisible(true);
                frame.dispose();
                
         }

			
        });

        buttonPanel.add(btnRegistrar);
        buttonPanel.add(btnCerrar);

       
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaRegistro();
            }
        });
    }
    
    public boolean checkFields() {
		if (isEmpty()) {
			JOptionPane.showMessageDialog(null, "Faltan algunos campos por rellenar");
			return false;
		} else {
			if (isWrong()) {
				JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
				return false;
			} else{
				JOptionPane.showMessageDialog(frame, "Registro exitoso");
			}
		}
		return true;
	}
    
    private boolean isEmpty() {
		return (txtNombre.getText().equals("") || (String.valueOf(txtContrasenya.getPassword()).equals(""))
				|| (String.valueOf(txtContrasenyaRep.getPassword()).equals("")));

	}

	private boolean isWrong() {
		return (!String.valueOf(txtContrasenya.getPassword()).equals(String.valueOf(txtContrasenyaRep.getPassword())));
	}

}
