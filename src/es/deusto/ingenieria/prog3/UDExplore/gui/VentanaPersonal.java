package es.deusto.ingenieria.prog3.UDExplore.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import es.deusto.ingenieria.prog3.UDExplore.domain.Cliente;
import es.deusto.ingenieria.prog3.UDExplore.domain.Reserva;
import es.deusto.ingenieria.prog3.UDExplore.io.BaseDeDatos;

public class VentanaPersonal extends JFrame {
    private static final long serialVersionUID = 1L;
    private Cliente cliente;
    private DefaultTableModel tableModel;

    public VentanaPersonal(Cliente cliente, HashMap<Cliente, Reserva> hashMap) {
        this.setCliente(cliente);
        cargarReservas(cliente.getCodigoUsuario());

        setTitle("Ventana Personal del Cliente");
        int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
				.getWidth();
		int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
				.getHeight();
		this.setSize(anchoP, altoP);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel panelSuperior = new JPanel(new BorderLayout());

        JButton volverButton = new JButton("Volver");
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  

                VentanaInicio ventanaInicio = new VentanaInicio();
                ventanaInicio.setVisible(true);
            }

        });

        panelSuperior.add(volverButton, BorderLayout.WEST);


        JPanel panelDatosCliente = new JPanel();
        JLabel datosClienteLabel = new JLabel("Datos del Cliente:");
        panelDatosCliente.add(datosClienteLabel);

        JTextArea datosClienteText = new JTextArea(cliente.toString());
        datosClienteText.setEditable(false);
        Border border = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        datosClienteText.setBorder(border);
        panelDatosCliente.add(datosClienteText);

        JPanel panelReservas = new JPanel(new BorderLayout());
        JLabel misReservasLabel = new JLabel("Mis Reservas");
        misReservasLabel.setFont(new Font("Serif", Font.BOLD, 16));
        panelReservas.add(misReservasLabel, BorderLayout.NORTH);

        String[] columnas = {"Número de Reserva", "Fecha de Inicio", "Fecha de Fin", "Nombre Estancia", "Ciudad", "Tarifa Noche", "Categoría"};
        tableModel = new DefaultTableModel(columnas, 0);
        JTable reservasTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(reservasTable);
        panelReservas.add(scrollPane, BorderLayout.CENTER);

        panelSuperior.add(panelDatosCliente, BorderLayout.CENTER);
        panelSuperior.add(panelReservas, BorderLayout.SOUTH);


        add(panelSuperior, BorderLayout.NORTH);

        pack();
        setVisible(true);
    }
    private void cargarReservas(int idCliente) {
       
        tableModel.setRowCount(0);

       
        List<Reserva> reservas = BaseDeDatos.cargarReservasPorUsuario(idCliente);

      
        if (reservas != null && !reservas.isEmpty()) {
         
            for (Reserva reserva : reservas) {
                Object[] rowData = {
                        reserva.getNumeroReserva(),
                        reserva.getFechaInicio(),
                        reserva.getFechaFin(),
                       
                };
                tableModel.addRow(rowData);
            }
        }
    }
    

    public static void main(String[] args) {
        
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}


