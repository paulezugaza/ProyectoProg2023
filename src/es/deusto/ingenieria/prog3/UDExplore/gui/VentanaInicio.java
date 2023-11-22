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
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import es.deusto.ingenieria.prog3.UDExplore.domain.Apartamento;
import es.deusto.ingenieria.prog3.UDExplore.domain.Ciudad;
import es.deusto.ingenieria.prog3.UDExplore.domain.Estancia;
import es.deusto.ingenieria.prog3.UDExplore.domain.Hotel;
import es.deusto.ingenieria.prog3.UDExplore.domain.Main;
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


	
	private static SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );

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

	
		for (Ciudad c : Ciudad.values()) {
			jComboDestino.addItem(c.toString());
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
		JButton bInicioS = new JButton("Iniciar sesión");

		bRegistro.addActionListener(e -> {
			new VentanaRegistro();
			
		});

		bInicioS.addActionListener(e -> {
			new VentanaLogin();
			

		});
		
		
		JPanel pPersonal = new JPanel();
		JLabel icono = new JLabel(scaleImage("resources/images/usuario.png", 60, 60));
		pPersonal.add(icono);
		pPersonal.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
				
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
				List<Hotel> hoteles = filtrarHoteles(Logica.estanciasHistoricas);
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
			        Date inicio = sdf.parse("" + ((String) jComboDiaEntrada.getSelectedItem()) + "/" + (jComboMesEntrada.getSelectedIndex() + 1) + "/" + ((String) jComboAnioEntrada.getSelectedItem()));
			        Date fin = sdf.parse("" + ((String) jComboDiaSalida.getSelectedItem()) + "/" + (jComboMesSalida.getSelectedIndex() + 1) + "/" + ((String) jComboAnioSalida.getSelectedItem()));
			        System.out.println("Botón Buscar clicado."); 
			        

			        if (fin.before(inicio)) {
			            jLabelInfo.setText("La fecha de salida no puede ser anterior a la fecha de entrada.");
			        } else {
			            jLabelInfo.setText("Realizando búsqueda...");

			            List<Estancia> estanciasDisponibles = new ArrayList<>();

			            for (Estancia estancia : Logica.estanciasHistoricas) {
			                if (estancia.getCiudad().toString().equals(jComboDestino.getSelectedItem().toString())) {
			                    if (Logica.estanciaDisponibleEnFechas(estancia, inicio, fin)) {
			                        estanciasDisponibles.add(estancia);
			                    }
			                }
			            }

			            if (estanciasDisponibles.isEmpty()) {
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
				List<Apartamento> apartamentos = filtrarApartamentos(Logica.estanciasHistoricas);
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

		agregarDestinoPopular(pDestinosPopulares, Ciudad.Barcelona, "resources/images/Barcelona.jpeg");
		agregarDestinoPopular(pDestinosPopulares, Ciudad.Madrid, "resources/images/Madril.jpg");
		agregarDestinoPopular(pDestinosPopulares, Ciudad.Valencia, "resources/images/Valencia.jpg");
		agregarDestinoPopular(pDestinosPopulares, Ciudad.Málaga, "resources/images/Malaga.jpg");
		agregarDestinoPopular(pDestinosPopulares, Ciudad.Bilbao, "resources/images/Bilbao.jpg");
		agregarDestinoPopular(pDestinosPopulares, Ciudad.Ibiza, "resources/images/ibiza.jpg");

		contenedorPrincipal.add(pBotones, BorderLayout.NORTH);
		contenedorPrincipal.add(pSearch, BorderLayout.CENTER);

		contenedorCentral.add(pPorTipoAloj, BorderLayout.NORTH);
		contenedorCentral.add(pDestinosPopulares, BorderLayout.CENTER);

		add(contenedorPrincipal, BorderLayout.NORTH);
		add(contenedorCentral, BorderLayout.CENTER);

	}

	// IA generated

	private ImageIcon scaleImage(String imagePath, int width, int height) {
		try {
			BufferedImage img = ImageIO.read(new File(imagePath));
			Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			return new ImageIcon(scaledImg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void agregarDestinoPopular(JPanel panelDestinos, Ciudad ciudad, String imageRuta) {
		JPanel panelCiudad = new JPanel();
		panelCiudad.setLayout(new BoxLayout(panelCiudad, BoxLayout.Y_AXIS));

		panelCiudad.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				
				ArrayList<Estancia> estanciasFiltradas = new ArrayList<>();
				Logica.estanciasHistoricas.forEach( est ->{
					if (est.getCiudad() == ciudad) {
						estanciasFiltradas.add(est);
					}

				});
				if (e.getClickCount() == 2) {
					VentanaResultados ventana = new VentanaResultados(estanciasFiltradas);
					ventana.setVisible(true);
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

	public ArrayList<Hotel> filtrarHoteles(List<Estancia> Estancias) {
		ArrayList<Hotel> hoteles = new ArrayList<>();
		Estancias.forEach( est ->{
			if (est instanceof Hotel) {
				hoteles.add(((Hotel) est));
			}
		});
		return hoteles;
	}

	public ArrayList<Apartamento> filtrarApartamentos(List<Estancia> Estancias) {
		ArrayList<Apartamento> apartamentos = new ArrayList<>();
		Estancias.forEach( est ->{
			if (est instanceof Apartamento) {
				apartamentos.add((Apartamento) est);
			}
		});
		return apartamentos;
	}
	
	

	

}