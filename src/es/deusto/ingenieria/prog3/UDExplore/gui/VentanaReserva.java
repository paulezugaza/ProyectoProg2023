package es.deusto.ingenieria.prog3.UDExplore.gui;

import java.text.ParseException;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

import com.sun.tools.javac.Main;

import es.deusto.ingenieria.prog3.UDExplore.domain.Apartamento;
import es.deusto.ingenieria.prog3.UDExplore.domain.Estancia;
import es.deusto.ingenieria.prog3.UDExplore.domain.Habitacion;
import es.deusto.ingenieria.prog3.UDExplore.io.Logica;

public abstract class VentanaReserva extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField nombre;
	private JTextField numTarjeta;
	private JTextField fechaCaducidad;

	public VentanaReserva() {

		try {
			setTitle("Reserva de estancia");
			setSize(800, 400);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setLocationRelativeTo(null);

			JPanel pHotelInfo = new JPanel(new BorderLayout());
			Font nuevaFuente = new Font("Arial", Font.ROMAN_BASELINE, 17);
			JLabel labelReser = new JLabel("Desea reservar esta estancia?", SwingConstants.CENTER);
			JLabel labelFechas = new JLabel(
					"Sus fechas son:" + Logica.fechaIni.toString() + "-" + Logica.fechaFin.toString() + "",
					SwingConstants.CENTER);
			JLabel hotelInfoLabel = new JLabel("");
			hotelInfoLabel.setFont(nuevaFuente);
			labelReser.setFont(nuevaFuente);
			pHotelInfo.add(labelReser, BorderLayout.NORTH);
			pHotelInfo.add(labelFechas, BorderLayout.CENTER);
			pHotelInfo.add(hotelInfoLabel, BorderLayout.SOUTH);

			JPanel pHotelFoto = new JPanel(new BorderLayout());
			ImageIcon hotelImage = new ImageIcon("");
			JLabel hotelImageLabel = new JLabel(
					new ImageIcon(hotelImage.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH)));
			pHotelFoto.add(hotelImageLabel, BorderLayout.CENTER);

			pHotelInfo.add(pHotelFoto, BorderLayout.CENTER);

			JPanel pPrincipal = new JPanel(new GridLayout(4, 2, 10, 10));
			add(pPrincipal);

			pPrincipal.add(new JLabel("Titular de la tarjeta:"));
			nombre = new JTextField(20);
			pPrincipal.add(nombre);

			pPrincipal.add(new JLabel("Numero de tarjeta:"));
			numTarjeta = createFormattedTextField("#### #### #### ####");
			pPrincipal.add(numTarjeta);

			pPrincipal.add(new JLabel("Fecha de caducidad (MM/YY):"));
			fechaCaducidad = createFormattedTextField("##/##");
			pPrincipal.add(fechaCaducidad);

			JPanel pBoton = new JPanel();
			JButton bCancelar = new JButton("Cancelar");
			JButton bConfirmarDatos = new JButton("Confirmar operación");
			pBoton.setLayout(new FlowLayout(FlowLayout.CENTER));
			pBoton.add(bCancelar);
			pBoton.add(bConfirmarDatos);

			setLayout(new BorderLayout());

			add(pHotelInfo, BorderLayout.NORTH);
			add(pPrincipal, BorderLayout.CENTER);
			add(pBoton, BorderLayout.SOUTH);

			bCancelar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();

				}

			});
			bConfirmarDatos.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (validarCampos()) {
					} else {
						JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos correctamente.",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error en la ventana de reserva.", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private JFormattedTextField createFormattedTextField(String mask) throws ParseException {
		MaskFormatter formatter = new MaskFormatter(mask);
		JFormattedTextField textField = new JFormattedTextField(formatter);
		textField.setColumns(20);
		return textField;
	}

	private boolean validarCampos() {
		if (nombre.getText().isEmpty() || numTarjeta.getText().isEmpty() || fechaCaducidad.getText().isEmpty()) {
			return false;
		}

		return true;
	}

}
