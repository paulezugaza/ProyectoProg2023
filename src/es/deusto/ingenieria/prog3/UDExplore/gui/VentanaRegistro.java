package es.deusto.ingenieria.prog3.UDExplore.gui;

import javax.swing.*;

import es.deusto.ingenieria.prog3.UDExplore.domain.Cliente;
import es.deusto.ingenieria.prog3.UDExplore.io.BaseDeDatos;
import es.deusto.ingenieria.prog3.UDExplore.io.Logica;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class VentanaRegistro extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JFrame frame = new JFrame("Registro de usuario");
	private JTextField txtNombre;
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
        JTextField txtNombre = new JTextField();
        JLabel lblApellido = new JLabel("Apellido:");
        JTextField txtApellido = new JTextField();
        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();
        JLabel lblContrasenya = new JLabel("Contraseña:"); 
        txtContrasenya = new JPasswordField();
        JLabel lblContrasenyaRep = new JLabel("Confrima tu contraseña:");
        txtContrasenyaRep = new JPasswordField();
       

        inputPanel.add(lblNombre);
        inputPanel.add(txtNombre);
        inputPanel.add(lblApellido);
        inputPanel.add(txtApellido);
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
                if (Logica.existeUsuario(txtEmail.getText())) {
                    JOptionPane.showMessageDialog(null, "ERROR: Ya existe una cuenta con ese email. Utilice otro");
                } else if (!txtEmail.getText().equals("") && !txtContrasenya.getText().equals("")) {
                    String er = "[a-zA-Z]{1,}.{0,}[a-zA-Z]{0,}@[a-zA-Z]{1,}.[a-z]{2,}";
                    String email = txtEmail.getText();
                    if (Pattern.matches(er, email)) {
                        Logica.crearUsuario(txtNombre.getText(), txtApellido.getText(), txtEmail.getText(), txtContrasenya.getText());
                        // Cerrar la ventana de registro
                        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
                        frame.dispose();
                        // Crear y mostrar la ventana de inicio de sesión
                        new VentanaLogin();
                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR: El formato del email no es correcto");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR: Rellene todos los campos");
                }
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
