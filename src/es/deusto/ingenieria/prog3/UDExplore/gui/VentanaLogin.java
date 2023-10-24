package es.deusto.ingenieria.prog3.UDExplore.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaLogin {
	
    public VentanaLogin() {
    	
        JFrame frame = new JFrame("Registro de Hotel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(5, 2));

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();
        JLabel lblApellidos = new JLabel("Apellidos:");
        JTextField txtApellidos = new JTextField();
        JLabel lblEmail = new JLabel("Correo Electrónico:");
        JTextField txtEmail = new JTextField();
        JLabel lblTelefono = new JLabel("Número de Teléfono:");
        JTextField txtTelefono = new JTextField();

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                String apellidos = txtApellidos.getText();
                String email = txtEmail.getText();
                String telefono = txtTelefono.getText();


                JOptionPane.showMessageDialog(frame, "Registro exitoso");
            }
        });

        frame.add(lblNombre);
        frame.add(txtNombre);
        frame.add(lblApellidos);
        frame.add(txtApellidos);
        frame.add(lblEmail);
        frame.add(txtEmail);
        frame.add(lblTelefono);
        frame.add(txtTelefono);
        frame.add(btnRegistrar);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaLogin();
            }
        });
    }
}
