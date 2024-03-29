package es.deusto.ingenieria.prog3.UDExplore.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import es.deusto.ingenieria.prog3.UDExplore.domain.Apartamento;
import es.deusto.ingenieria.prog3.UDExplore.domain.Cliente;
import es.deusto.ingenieria.prog3.UDExplore.domain.Estancia;
import es.deusto.ingenieria.prog3.UDExplore.domain.Hotel;
import es.deusto.ingenieria.prog3.UDExplore.domain.Main;
import es.deusto.ingenieria.prog3.UDExplore.domain.Reserva;
import es.deusto.ingenieria.prog3.UDExplore.io.BaseDeDatos;
import es.deusto.ingenieria.prog3.UDExplore.io.Logica;

public class VentanaInicio extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTable jTableEstancias = new JTable();

	private JComboBox<String> jComboDestino = new JComboBox<>();
	private JComboBox<String> jComboDiaEntrada = new JComboBox<>();
	private JComboBox<String> jComboMesEntrada = new JComboBox<>();
	private JComboBox<String> jComboAnioEntrada = new JComboBox<>();
	private JComboBox<String> jComboDiaSalida = new JComboBox<>();
	private JComboBox<String> jComboMesSalida = new JComboBox<>();
	private JComboBox<String> jComboAnioSalida = new JComboBox<>();

	private JLabel jLabelInfo = new JLabel();

	private List<Estancia> estanciasDisponibles = new ArrayList<>();

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public ArrayList<Object> getEstancias() {
		ArrayList<Object> listaEnMain = Main.getList();
		return listaEnMain;

	}

	ArrayList<Object> lista = this.getEstancias();

	public VentanaInicio() {

		setTitle("UDExplore");
		int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
				.getWidth();
		int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
				.getHeight();
		this.setSize(anchoP, altoP);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Set<String> ciudades = BaseDeDatos.getCiudades();
		for (String c : ciudades) {
			jComboDestino.addItem(c);
		}

		for (int dia = 1; dia <= 31; dia++) {
			jComboDiaEntrada.addItem(String.valueOf(dia));
			jComboDiaSalida.addItem(String.valueOf(dia));
		}

		String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
				"Octubre", "Noviembre", "Diciembre" };

		for (String mes : meses) {
			jComboMesEntrada.addItem(mes);
			jComboMesSalida.addItem(mes);
		}

		for (int anio = 2023; anio <= 2030; anio++) {
			jComboAnioEntrada.addItem(String.valueOf(anio));
			jComboAnioSalida.addItem(String.valueOf(anio));
		}

		jTableEstancias.setRowHeight(30);
		jTableEstancias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		((DefaultTableCellRenderer) jTableEstancias.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(JLabel.CENTER);

		jLabelInfo.setHorizontalAlignment(JLabel.RIGHT);

		JPanel contenedorPrincipal = new JPanel(new BorderLayout());
		JPanel contenedorCentral = new JPanel(new BorderLayout());

		JPanel pBotones = new JPanel();
		JButton bRegistro = new JButton("Registrarse");
		JButton bInicioS = new JButton("Iniciar sesion");

		bRegistro.addActionListener(e -> {
			new VentanaRegistro();

		});

		bInicioS.addActionListener(e -> {
			new VentanaLogin();

		});

		Calendar calendar = Calendar.getInstance();
		Date fechaActual = calendar.getTime();
		String diaActual = new SimpleDateFormat("dd").format(fechaActual);
		String mesActual = new SimpleDateFormat("MM").format(fechaActual);
		String anioActual = new SimpleDateFormat("yyyy").format(fechaActual);

		jComboDiaEntrada.setSelectedItem(diaActual);
		jComboMesEntrada.setSelectedIndex(Integer.parseInt(mesActual) - 1);
		jComboAnioEntrada.setSelectedItem(anioActual);

		jComboDiaSalida.setSelectedItem(diaActual);
		jComboMesSalida.setSelectedIndex(Integer.parseInt(mesActual) - 1);
		jComboAnioSalida.setSelectedItem(anioActual);

		JPanel pPersonal = new JPanel();
		JLabel icono = new JLabel(scaleImage("resources/images/usuario.png", 60, 60));
		pPersonal.add(icono);
		pPersonal.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (Logica.usuario != null && Logica.usuario instanceof Cliente) {
						VentanaPersonal vp = new VentanaPersonal((Cliente) Logica.usuario, new HashMap<Cliente, Reserva>());
						vp.setVisible(true);
					}
					if (Logica.usuario == null) {
						String mensaje = "Parece que no ha iniciado sesion.";

						JOptionPane.showMessageDialog(null, mensaje, "ERROR", JOptionPane.INFORMATION_MESSAGE);

					}

				}

			}
		});

		pBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pBotones.add(bRegistro);
		pBotones.add(bInicioS);
		pBotones.add(pPersonal);

		JPanel pfoto = new JPanel();
		JLabel iIcono = new JLabel(scaleImage("resources/images/icono.png", 170, 170));
		pfoto.add(iIcono);

		JPanel pDestino = new JPanel();
		pDestino.setLayout(new BoxLayout(pDestino, BoxLayout.Y_AXIS));
		pDestino.setBounds(10, 50, 380, 30);
		JLabel label = new JLabel("¿A donde desea viajar? ");
		Font Fuente = new Font("SanSerif", Font.PLAIN, 20);
		label.setFont(Fuente);

		pDestino.add(label);
		pDestino.add(jComboDestino);

		JPanel pFechasE = new JPanel();
		pFechasE.setLayout(new BoxLayout(pFechasE, BoxLayout.Y_AXIS));
		pFechasE.add(new JLabel("Fecha de Entrada: "));
		pFechasE.setBounds(10, 90, 380, 30);
		pFechasE.add(jComboDiaEntrada);
		pFechasE.add(jComboMesEntrada);
		pFechasE.add(jComboAnioEntrada);

		JPanel pFechasS = new JPanel();
		pFechasS.setLayout(new BoxLayout(pFechasS, BoxLayout.Y_AXIS));
		pFechasS.setBounds(10, 130, 380, 30);
		pFechasS.add(new JLabel("Fecha de Salida: "));
		pFechasS.add(jComboDiaSalida);
		pFechasS.add(jComboMesSalida);
		pFechasS.add(jComboAnioSalida);

		JButton bBuscar = new JButton("Buscar");

		JPanel pSearch = new JPanel();
		pSearch.setPreferredSize(new Dimension(400, 200));

		pSearch.add(pfoto);
		pSearch.add(pDestino);
		pSearch.add(pFechasE);
		pSearch.add(pFechasS);
		pSearch.add(bBuscar);
		pSearch.setLayout(new GridBagLayout());


		JPanel pPorTipoAloj = new JPanel();
		pPorTipoAloj.setLayout(new BoxLayout(pPorTipoAloj, BoxLayout.X_AXIS));
		pPorTipoAloj.setPreferredSize(new Dimension(200, 200));

		JPanel ptitulo = new JPanel();
		JLabel ltitulo = new JLabel("Buscar por tipo de alojamiento:");
		ltitulo.setFont(new Font("Serif", Font.PLAIN, 20));
		ptitulo.add(ltitulo);

		JPanel pHotel = new JPanel();
		pHotel.setLayout(new BoxLayout(pHotel, BoxLayout.Y_AXIS));
		JLabel lHotel = new JLabel("Hotel");
		lHotel.setFont(new Font("Serif", Font.PLAIN, 15));
		JLabel iHotel = new JLabel(scaleImage("resources/images/hotel.jpeg", 200, 100));
		pHotel.add(lHotel);
		pHotel.add(iHotel);
		pHotel.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				List<Hotel> hoteles = filtrarHoteles();
				ArrayList<Estancia> estanciasFiltradas = new ArrayList<>();
				hoteles.forEach(h -> {
					estanciasFiltradas.add(h);
				});
				if (e.getClickCount() == 2) {

					VentanaResultados ventana = new VentanaResultados(estanciasFiltradas);
					ventana.setVisible(true);
					setVisible(false);
				}

			}
		});


		bBuscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					Date hoy = new Date();
					Date inicio = sdf.parse("" + ((String) jComboDiaEntrada.getSelectedItem()) + "/"
							+ (jComboMesEntrada.getSelectedIndex() + 1) + "/"
							+ ((String) jComboAnioEntrada.getSelectedItem()));
					Date fin = sdf.parse("" + ((String) jComboDiaSalida.getSelectedItem()) + "/"
							+ (jComboMesSalida.getSelectedIndex() + 1) + "/"
							+ ((String) jComboAnioSalida.getSelectedItem()));
					Logica.obtenerFechaSeleccionadaIni(jComboDiaEntrada, jComboMesEntrada, jComboAnioEntrada);
					Logica.obtenerFechaSeleccionadaFin(jComboDiaSalida, jComboMesSalida, jComboAnioSalida);
			

					if (fin.before(inicio) || fin.equals(inicio)) {
						JOptionPane.showMessageDialog(VentanaInicio.this,
								"Error: La fecha de salida no puede ser anterior o igual a la fecha de entrada.",
								"Error", JOptionPane.ERROR_MESSAGE);
					} else if (inicio.before(hoy)) {
						JOptionPane.showMessageDialog(VentanaInicio.this,
								"Error: La fecha de entrada no puede ser anterior a la fecha actual. La entrada también tiene que ser a partir de mañana.",
								"Error", JOptionPane.ERROR_MESSAGE);
					} else {
						jLabelInfo.setText("Realizando busqueda...");

						estanciasDisponibles = BaseDeDatos.buscarEstancia((String) jComboDestino.getSelectedItem(),
								inicio.getTime(), fin.getTime());

						if (estanciasDisponibles == null || estanciasDisponibles.isEmpty()) {
							jLabelInfo.setText("No hay estancias disponibles para estas fechas en este destino.");

						} else {
							jLabelInfo.setText("Se encontraron " + estanciasDisponibles.size() + " estancias disponibles.");

							VentanaResultados ventanaResultados = new VentanaResultados(estanciasDisponibles);
							ventanaResultados.setVisible(true);
							dispose();
						}
					}
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
			}
			

		});


		JPanel pApartamento = new JPanel();
		pApartamento.setLayout(new BoxLayout(pApartamento, BoxLayout.Y_AXIS));
		JLabel lApartamento = new JLabel("Apartamento");
		lApartamento.setFont(new Font("Serif", Font.PLAIN, 15));
		JLabel iApartamento = new JLabel(scaleImage("resources/images/apartamento.jpeg", 200, 100));
		pApartamento.add(lApartamento);
		pApartamento.add(iApartamento);
		pApartamento.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				List<Apartamento> apartamentos = filtrarApartamentos();
				ArrayList<Estancia> estanciasFiltradas = new ArrayList<>();
				apartamentos.forEach(a -> {
					estanciasFiltradas.add(a);
				});
				if (e.getClickCount() == 2) {
					VentanaResultados ventana = new VentanaResultados(estanciasFiltradas);
					ventana.setVisible(true);
					dispose();
				}

			}

		});

		JPanel centralTipoAloj = new JPanel();
		centralTipoAloj.add(pHotel);
		centralTipoAloj.add(pApartamento);

		pPorTipoAloj.add(ptitulo, BorderLayout.NORTH);
		pPorTipoAloj.add(centralTipoAloj, BorderLayout.CENTER);


		JPanel pDestinosPopulares = new JPanel();
		JLabel lDestinosPopulares = new JLabel("Nuestros destinos más populares:");
		lDestinosPopulares.setFont(new Font("Serif", Font.PLAIN, 20));
		pDestinosPopulares.add(lDestinosPopulares, BorderLayout.NORTH);
		pDestinosPopulares.setPreferredSize(new Dimension(anchoP, 200));

		agregarDestinoPopular(pDestinosPopulares, "Barcelona", "resources/images/Barcelona.jpeg");
		agregarDestinoPopular(pDestinosPopulares, "Madrid", "resources/images/Madril.jpg");
		agregarDestinoPopular(pDestinosPopulares, "Valencia", "resources/images/Valencia.jpg");
		agregarDestinoPopular(pDestinosPopulares, "Malaga", "resources/images/Malaga.jpg");
		agregarDestinoPopular(pDestinosPopulares, "Bilbao", "resources/images/Bilbao.jpg");
		agregarDestinoPopular(pDestinosPopulares, "Ibiza", "resources/images/ibiza.jpg");

		contenedorPrincipal.add(pBotones, BorderLayout.NORTH);
		contenedorPrincipal.add(pSearch, BorderLayout.CENTER);

		contenedorCentral.add(pDestinosPopulares, BorderLayout.CENTER);

		add(contenedorPrincipal, BorderLayout.NORTH);
		add(contenedorCentral, BorderLayout.CENTER);
		
		JPanel contenedorInferior = new JPanel();
		contenedorInferior.setLayout(new BorderLayout());

		JPanel pPresupuesto = new JPanel();
		JLabel lPregunta = new JLabel("<html><div style='text-align: center;'>¿Tienes presupuesto? Nosotros te damos todas las opciones.</div></html>");
		lPregunta.setFont(new Font("Serif", Font.PLAIN, 20));
		JButton bPresupuesto = new JButton("<html><font color='blue'>Click aquí</font></html>");
		bPresupuesto.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        new VentanaPresupuesto();
		        dispose();
		    }
		});

		pPresupuesto.add(lPregunta, BorderLayout.NORTH);
		pPresupuesto.add(bPresupuesto, BorderLayout.CENTER);

		contenedorInferior.add(pPresupuesto, BorderLayout.SOUTH);
		contenedorInferior.add(pDestinosPopulares, BorderLayout.CENTER);

		add(contenedorPrincipal, BorderLayout.NORTH);
		add(contenedorInferior, BorderLayout.CENTER);

    
	}

	// IA generated

	public ImageIcon scaleImage(String imagePath, int width, int height) {
		try {
			BufferedImage img = ImageIO.read(new File(imagePath));
			Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			return new ImageIcon(scaledImg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void agregarDestinoPopular(JPanel panelDestinos, String ciudad, String imageRuta) {
		JPanel panelCiudad = new JPanel();
		panelCiudad.setLayout(new BoxLayout(panelCiudad, BoxLayout.Y_AXIS));

		panelCiudad.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Date inicio;
				Date fin;
				try {
					inicio = sdf.parse("" + ((String) jComboDiaEntrada.getSelectedItem()) + "/"
							+ (jComboMesEntrada.getSelectedIndex() + 1) + "/"
							+ ((String) jComboAnioEntrada.getSelectedItem()));
					fin = sdf.parse("" + ((String) jComboDiaSalida.getSelectedItem()) + "/"
							+ (jComboMesSalida.getSelectedIndex() + 1) + "/"
							+ ((String) jComboAnioSalida.getSelectedItem()));

					List<Estancia> estanciasFiltradas = new ArrayList<>();
					estanciasFiltradas = BaseDeDatos.buscarEstancia(ciudad.toUpperCase(), inicio.getTime(),
							fin.getTime());
					if (e.getClickCount() == 2) {
						VentanaResultados ventana = new VentanaResultados(estanciasFiltradas);
						ventana.setVisible(true);
					}
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

			}
		});

		JLabel labelNombreCiudad = new JLabel(ciudad.toString());
		labelNombreCiudad.setFont(new Font("Serif", Font.PLAIN, 15));
		JLabel labelImagen = new JLabel(scaleImage(imageRuta, 150, 150));
		panelCiudad.add(labelNombreCiudad);
		panelCiudad.add(labelImagen);

		panelDestinos.add(panelCiudad);
	}


	public ArrayList<Hotel> filtrarHoteles() {
		ArrayList<Hotel> hoteles = new ArrayList<>();
		estanciasDisponibles.forEach(est -> {
			if (est instanceof Hotel) {
				hoteles.add(((Hotel) est));
			}
		});
		return hoteles;
	}

	public ArrayList<Apartamento> filtrarApartamentos() {
		ArrayList<Apartamento> apartamentos = new ArrayList<>();
		estanciasDisponibles.forEach(est -> {
			if (est instanceof Apartamento) {
				apartamentos.add((Apartamento) est);
			}
		});
		return apartamentos;
	}


}