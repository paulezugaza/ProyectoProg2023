package es.deusto.ingenieria.prog3.UDExplore.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

public class VentanaFiltros extends JDialog {

	private static final long serialVersionUID = 1L;
	
	public VentanaFiltros(JFrame parent) {
		super(parent, "Filtros", true);
		
		
		
		JPanel panelPrecio = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panelPuntuacion = new JPanel(new GridLayout(3, 1));
        JPanel panelTipoAlojamiento = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panelBotones = new JPanel(new GridLayout(2,1));
		
		setLayout(new GridLayout(5,2));
		
		JLabel lPrecio = new JLabel("Por precio:");
		JScrollBar scrollBarPrecio = new JScrollBar(JScrollBar.HORIZONTAL, 20, 1, 20, 350);
		JLabel lPrecioSel = new JLabel("Precio: " + scrollBarPrecio.getValue());
		
		JPanel panelMinMax = new JPanel(new BorderLayout());
		
	    JLabel lMinimo = new JLabel("Mínimo: " + scrollBarPrecio.getMinimum());
	    JLabel lMaximo = new JLabel("Máximo: " + scrollBarPrecio.getMaximum());
	    
	    JButton bAplicar = new JButton("Aplicar filtros");
	    JButton bCancelar = new JButton("Cancelar");
	    
	    panelBotones.add(bCancelar);
	    panelBotones.add(bAplicar);
	    
	    
	    panelMinMax.add(lMinimo, BorderLayout.WEST);
        panelMinMax.add(lMaximo, BorderLayout.EAST);
	    
	    scrollBarPrecio.addAdjustmentListener((AdjustmentListener) new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent e) {
				int valorSeleccionado = scrollBarPrecio.getValue();
                lPrecioSel.setText("Precio: " + valorSeleccionado);
				
			}
			
		});
		panelPrecio.add(lPrecio );
		panelPrecio.add(scrollBarPrecio);
		panelPrecio.add(panelMinMax);
		
		JLabel lTipoAlojamiento = new JLabel("Por tipo de alojamiento:");
		String[] tiposAlojamiento = {"Hotel", "Apartamento"};
		JComboBox JcomboTipoAloj = new JComboBox<>(tiposAlojamiento);
		
		
		
		panelTipoAlojamiento.add(lTipoAlojamiento);
		panelTipoAlojamiento.add(JcomboTipoAloj);
		
		
		JLabel lPuntuacion = new JLabel("Por puntuacion del hotel:");
		JCheckBox chkPuntuacion1 = new JCheckBox("★★★★★");
		JCheckBox chkPuntuacion2 = new JCheckBox("★★★★");
		JCheckBox chkPuntuacion3 = new JCheckBox("★★★");
		JCheckBox chkPuntuacion4 = new JCheckBox("★★");
		JCheckBox chkPuntuacion5= new JCheckBox("★");
		
		
		
	
		panelPuntuacion.add(lPuntuacion);
		panelPuntuacion.add(chkPuntuacion1);
		panelPuntuacion.add(chkPuntuacion2);
		panelPuntuacion.add(chkPuntuacion3);
		panelPuntuacion.add(chkPuntuacion4);
		panelPuntuacion.add(chkPuntuacion5);
		
		
		add(panelPrecio);
		add(panelPuntuacion);
		add(panelTipoAlojamiento);
		add(panelBotones);
		
		
		
		
		
		
		pack();
		setLocationRelativeTo(parent);
	
	}
	

}
