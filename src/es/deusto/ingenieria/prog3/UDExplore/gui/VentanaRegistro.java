package es.deusto.ingenieria.prog3.UDExplore.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistro {

    public VentanaRegistro() {
        JFrame frame = new JFrame("Registro de usuario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));  
 
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(7, 2, 5, 5));  

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();
        JLabel lblApellidos = new JLabel("Apellidos:");
        JTextField txtApellidos = new JTextField();
        JLabel lblTelefono = new JLabel("Número de Teléfono:");
        JTextField txtTelefono = new JTextField();
        JLabel lblDireccion = new JLabel("Direccion:");
        JTextField txtDireccion = new JTextField();
        JLabel lblCiudad = new JLabel("Ciudad:");
        JTextField txtCiudad = new JTextField();
        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();
        JLabel lblContraseña = new JLabel("Contraseña:");
        JTextField txtContraseña = new JTextField();

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
        inputPanel.add(lblContraseña);
        inputPanel.add(txtContraseña);

 
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));  

        JButton btnRegistrar = new JButton("Registrarse");
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

        JButton btnCerrar = new JButton("Cerrar");

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
}
