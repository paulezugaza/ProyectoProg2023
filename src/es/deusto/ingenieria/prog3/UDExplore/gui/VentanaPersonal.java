package es.deusto.ingenieria.prog3.UDExplore.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import es.deusto.ingenieria.prog3.UDExplore.domain.Cliente;
import es.deusto.ingenieria.prog3.UDExplore.domain.Estancia;
import es.deusto.ingenieria.prog3.UDExplore.domain.Hotel;
import es.deusto.ingenieria.prog3.UDExplore.domain.Reserva;
import es.deusto.ingenieria.prog3.UDExplore.domain.ReservaConEstancia;
import es.deusto.ingenieria.prog3.UDExplore.domain.Reseña;
import es.deusto.ingenieria.prog3.UDExplore.io.BaseDeDatos;

public class VentanaPersonal extends JFrame {
    private static final long serialVersionUID = 1L;
    private Cliente cliente;
    private DefaultTableModel tableModel;
    private DefaultTableModel resenasTableModel;

    public VentanaPersonal(Cliente cliente, HashMap<Cliente, Reserva> hashMap) {
        this.setCliente(cliente);
        
      
      
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

        String[] columnas = { "Número de Reserva", "Fecha de Inicio", "Fecha de Fin", "Nombre Estancia", "Ciudad" };
        tableModel = new DefaultTableModel(columnas, 0);
        JTable reservasTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(reservasTable);
        panelReservas.add(scrollPane, BorderLayout.CENTER);

        panelSuperior.add(panelDatosCliente, BorderLayout.CENTER);
        panelSuperior.add(panelReservas, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);
        JComboBox<String> comboEstancias = new JComboBox<>();
        JButton enviarResenaButton = new JButton("Enviar Reseña");

        JPanel panelResenas = new JPanel(new BorderLayout());
        JLabel misResenasLabel = new JLabel("Mis Reseñas:");
        misResenasLabel.setFont(new Font("Serif", Font.BOLD, 16));
        panelResenas.add(misResenasLabel, BorderLayout.NORTH);

        JTextArea textAreaResenas = new JTextArea();
        textAreaResenas.setEditable(false);
        panelResenas.add(textAreaResenas, BorderLayout.CENTER);

        enviarResenaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaEnviarResena(cliente).setVisible(true);
            }
        });
        cargarReservas(cliente.getCodigoUsuario());

        reservasTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int filaSeleccionada = reservasTable.getSelectedRow();
                    if (filaSeleccionada != -1) {
                        int opcion = JOptionPane.showConfirmDialog(
                                VentanaPersonal.this,
                                "¿Desea cancelar la reserva?",
                                "Confirmar cancelación",
                                JOptionPane.YES_NO_OPTION);

                        if (opcion == JOptionPane.YES_OPTION) {
                  
                            Integer idReserva = ((Integer) reservasTable.getValueAt(filaSeleccionada, 0));
                            BaseDeDatos.borrarReserva(idReserva);
                            cargarReservas(cliente.getCodigoUsuario());
                          
                            
                        }
                    }
                }
            }
        });
        
        

        JPanel panelInferior = new JPanel(new BorderLayout());
        JButton verReseñas = new JButton("¿Quieres saber las reseñas que has hecho ?");
        verReseñas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarResenasCliente(cliente);
            }
        });

        panelInferior.add(verReseñas, BorderLayout.CENTER);
        panelInferior.add(enviarResenaButton, BorderLayout.SOUTH);

        JPanel contenedorPrincipal = new JPanel(new BorderLayout());
        contenedorPrincipal.add(panelSuperior, BorderLayout.NORTH);
        contenedorPrincipal.add(panelInferior, BorderLayout.SOUTH);
        
        add(contenedorPrincipal);
        

        pack();
      

        setVisible(true);
    }
    
    


   

    
    private void mostrarResenasCliente(Cliente cliente) {
        JTextArea textAreaResenas = new JTextArea();
        textAreaResenas.setEditable(false);

        String rutaArchivo = "Resources/data/resenas.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] partes = line.split(",");
                if (partes.length == 3 && partes[0].equals(cliente.getNombreUsuario())) {
                    String nombreEstancia = partes[1].trim();
                    String reseña = partes[2].trim();
                    textAreaResenas.append("Estancia: " + nombreEstancia + "\nReseña: " + reseña + "\n\n");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        JOptionPane.showMessageDialog(this, new JScrollPane(textAreaResenas),
                "Reseñas de " + cliente.getNombreUsuario(), JOptionPane.PLAIN_MESSAGE);
    }


 
    private void cargarReservas(int idCliente) {
        tableModel.setRowCount(0);

       List<ReservaConEstancia> reservasConEstancia = BaseDeDatos.cargarReservasPorUsuario(idCliente);
       
       if (reservasConEstancia != null && !reservasConEstancia.isEmpty()) {
           SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
           

        if (reservasConEstancia != null && !reservasConEstancia.isEmpty()) {
            for (ReservaConEstancia reservaConEstancia : reservasConEstancia) {
                Reserva reserva = reservaConEstancia.getReserva();
                Estancia estancia = reservaConEstancia.getEstancia();
                if (estancia instanceof Hotel) {
                	
                	
                }

                Object[] rowData = {
                        reserva.getNumeroReserva(),
                        reserva.getFechaInicio(),
                        reserva.getFechaFin(),
                        estancia.getNombre(), 
                        estancia.getCiudad(), 
                       
                };
                tableModel.addRow(rowData);
            }
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


