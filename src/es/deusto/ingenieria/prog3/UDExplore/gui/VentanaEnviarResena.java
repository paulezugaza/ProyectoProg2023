package es.deusto.ingenieria.prog3.UDExplore.gui;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
    private static final long serialVersionUID = 1L;
	private Cliente cliente;
    private JComboBox<String> comboEstancias;
    private JTextField txtResena;
    private JButton enviarResenaButton;
    private JButton volverButton;

    public VentanaEnviarResena(Cliente cliente) {
        this.cliente = cliente;
        setTitle("Envío de Reseñas");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        volverButton = new JButton("Volver");
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panelPrincipal.add(volverButton, gbc);

        gbc.gridy++;
        panelPrincipal.add(new JLabel("Enviar Reseña:"), gbc);

        gbc.gridy++;
        comboEstancias = new JComboBox<>();
        panelPrincipal.add(comboEstancias, gbc);

        gbc.gridy++;
        panelPrincipal.add(new JLabel("Reseña:"), gbc);

        gbc.gridy++;
        JPanel panelTextoResena = new JPanel(new BorderLayout());
        txtResena = new JTextField();
        txtResena.setPreferredSize(new Dimension(300, 150)); 
        panelTextoResena.add(txtResena, BorderLayout.CENTER);
        panelPrincipal.add(panelTextoResena, gbc);

        gbc.gridy++;
        enviarResenaButton = new JButton("Enviar");
        panelPrincipal.add(enviarResenaButton, gbc);

        enviarResenaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarResena();
            }
        });

        cargarEstancias();

        add(panelPrincipal);

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
        String rutaArchivo = "Resources/data/resenas.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            writer.write(cliente.getNombreUsuario() + "," + nombreEstancia + "," + reseña + "\n");
            System.out.println("Reseña guardada en: " + rutaArchivo);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    

 
}


}
