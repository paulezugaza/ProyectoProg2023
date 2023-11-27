package es.deusto.ingenieria.prog3.UDExplore.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import es.deusto.ingenieria.prog3.UDExplore.domain.Cliente;
import es.deusto.ingenieria.prog3.UDExplore.domain.Estancia;
import es.deusto.ingenieria.prog3.UDExplore.domain.Reserva;

	public class VentanaPersonal extends JFrame {
	    private static final long serialVersionUID = 1L;
		private Cliente cliente;
	    private DefaultTableModel tableModel;

	    public VentanaPersonal(Cliente cliente, HashMap<Cliente, Reserva> hashMap) {
	        this.setCliente(cliente);

	        setTitle("Ventana Personal del Cliente");
	        setSize(800, 600);
	        setLocationRelativeTo(null);

	        JPanel panelSuperior = new JPanel();
	        JLabel datosClienteLabel = new JLabel("Datos del Cliente:");
	        panelSuperior.add(datosClienteLabel);

	        JTextArea datosClienteNom = new JTextArea("Usuario:" +  cliente.getNombreUsuario());
	        datosClienteNom.setBackground(getForeground());
	        JTextArea datosClienteCorreo = new JTextArea("Correo electrónico:" +  cliente.getCorreoElectronico());
	        datosClienteCorreo.setBackground(getForeground());
	        datosClienteNom.setEditable(false);
	        datosClienteCorreo.setEditable(false);
	        panelSuperior.add(datosClienteNom);
	        panelSuperior.add(datosClienteCorreo);
	        panelSuperior.setPreferredSize(new Dimension(panelSuperior.getPreferredSize().width, 50));
            panelSuperior.setBackground(Color.LIGHT_GRAY);
	        add(panelSuperior, BorderLayout.NORTH);
	        
	        int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
			int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
			this.setSize(anchoP, altoP);
			this.setExtendedState(MAXIMIZED_BOTH);
			this.setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setTitle("VENTANA PERSONAL");
	        JPanel panelCentral = new JPanel();
	        panelCentral.add(new JLabel("Mis Reservas:"));
	        add(panelCentral, BorderLayout.CENTER);

	     
	        String[] columnas = {"Número de Reserva", "Fecha de Inicio", "Fecha de Fin", "Nombre Estancia", "Ciudad", "Tarifa Noche", "Categoría"};
	        tableModel = new DefaultTableModel(columnas, 0);
	        JTable reservasTable = new JTable(tableModel);

	        JScrollPane scrollPane = new JScrollPane(reservasTable);
	        add(scrollPane, BorderLayout.CENTER);

	 
	      /**  for (Reserva reserva : hashMap.values()) {
	            if (reserva.getCliente() == cliente) {
	                Estancia estancia = reserva.getTipo();
	                Object[] rowData = {
	                    reserva.getNumeroReserva(),
	                    reserva.getFechaInicio(),
	                    reserva.getFechaFin(),
	                    estancia.getNombre(),
	                    estancia.getCiudad(),
	                    estancia.getTarifaNoche(),
	                    estancia.getCategoria()
	                };
	                tableModel.addRow(rowData);
	            }
	        }**/

	        pack();
	        setVisible(true);
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


