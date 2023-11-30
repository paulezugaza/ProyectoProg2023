package es.deusto.ingenieria.prog3.UDExplore.gui;



import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import es.deusto.ingenieria.prog3.UDExplore.domain.Apartamento;

public class VentanaReservaApartamento extends VentanaReserva {

    private static final long serialVersionUID = 1L;
	private Apartamento apartamento;

    public VentanaReservaApartamento(Apartamento apartamento) {
        super();
        this.apartamento = apartamento;
        inicializarVentana();
        JPanel pBoton = new JPanel();
		JButton bCancelar = new JButton("Cancelar");
		JButton bConfirmarDatos = new JButton("Confirmar operación");
		pBoton.setLayout(new FlowLayout(FlowLayout.CENTER));
		pBoton.add(bCancelar);
		pBoton.add(bConfirmarDatos);
        bConfirmarDatos.addActionListener(new ActionListener() {

    		@Override
    		public void actionPerformed(ActionEvent e) {
    			
    			String mensaje = "¡Su reserva ha sido guardada con éxito!\n\n" +
                        "Detalles de la estancia:\n" +
                        "Apartamento: " + apartamento.getNombre() + "\n" +
                        "Precio por noche: " + apartamento.getTarifaNoche() + "€\n";

    			JOptionPane.showMessageDialog(null, mensaje, "Reserva Exitosa", JOptionPane.INFORMATION_MESSAGE);

    			dispose();
    			
    		}
    		
    	});
    }

    private void inicializarVentana() {

        setTitle("Reserva de apartamento");
       
    }

}

