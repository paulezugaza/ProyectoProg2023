package es.deusto.ingenieria.prog3.UDExplore.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import es.deusto.ingenieria.prog3.UDExplore.domain.Cliente;
import es.deusto.ingenieria.prog3.UDExplore.domain.ReservaConEstancia;
import es.deusto.ingenieria.prog3.UDExplore.io.BaseDeDatos;

public class VentanaEnviarResena extends JFrame {
    private Cliente cliente;
    private JComboBox<String> comboEstancias;
    private JTextField txtResena;
    private JButton enviarResenaButton;
    private JButton volverButton;

    public VentanaEnviarResena(Cliente cliente) {
        this.cliente = cliente;
        setTitle("Envío de Reseñas");
        setSize(800, 600);  // Ajusta el tamaño según tus preferencias
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        JPanel panelSuperior = new JPanel(new GridLayout(5, 2, 10, 10));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Botón Volver en la esquina superior izquierda
        volverButton = new JButton("Volver");
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panelSuperior.add(volverButton, BorderLayout.NORTH);

        panelSuperior.add(new JLabel("Enviar Reseña:", SwingConstants.LEFT));

        panelSuperior.add(new JLabel()); // Espacio en blanco para separar el botón "Enviar" y "Estancia"

        panelSuperior.add(new JLabel("Estancia:", SwingConstants.LEFT));
        comboEstancias = new JComboBox<>();
        panelSuperior.add(comboEstancias);

        panelSuperior.add(new JLabel("Reseña:", SwingConstants.LEFT));

        JPanel panelTextoResena = new JPanel(new BorderLayout());
        txtResena = new JTextField();
        panelTextoResena.add(txtResena, BorderLayout.CENTER);
        panelSuperior.add(panelTextoResena);

        JPanel panelEnviarResena = new JPanel(new BorderLayout());
        enviarResenaButton = new JButton("Enviar");
        panelEnviarResena.add(enviarResenaButton, BorderLayout.EAST); // Botón Enviar a la derecha
        panelSuperior.add(panelEnviarResena);

        enviarResenaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarResena();
            }
        });

       



        panelPrincipal.add(panelSuperior, BorderLayout.CENTER);


        cargarEstancias();

        add(panelPrincipal, BorderLayout.CENTER);

        setVisible(true);
    }

    private void cargarEstancias() {
        List<ReservaConEstancia> reservasConEstancia = BaseDeDatos.cargarReservasPorUsuario(cliente.getCodigoUsuario());
        for (ReservaConEstancia estancia : reservasConEstancia) {
            comboEstancias.addItem(estancia.getEstancia().getNombre());
        }
    }

    private void enviarResena() {
        String nombreEstancia = (String) comboEstancias.getSelectedItem();
        String reseña = txtResena.getText();

        if (nombreEstancia != null && !nombreEstancia.isEmpty()) {
           
            guardarResenaEnFichero(nombreEstancia, reseña);

            txtResena.setText("");
            JOptionPane.showMessageDialog(this, "Reseña enviada y guardada exitosamente en el fichero.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error: Selecciona una estancia antes de enviar la reseña.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarResenaEnFichero(String nombreEstancia, String reseña) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Resources/data/resenas.txt", true))) {
            writer.write("Estancia: " + nombreEstancia + "\n" + "Reseña: " + reseña + "\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
