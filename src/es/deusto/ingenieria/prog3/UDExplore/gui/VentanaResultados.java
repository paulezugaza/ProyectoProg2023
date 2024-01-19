package es.deusto.ingenieria.prog3.UDExplore.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import es.deusto.ingenieria.prog3.UDExplore.domain.Apartamento;
import es.deusto.ingenieria.prog3.UDExplore.domain.Cliente;
import es.deusto.ingenieria.prog3.UDExplore.domain.Estancia;
import es.deusto.ingenieria.prog3.UDExplore.domain.Hotel;
import es.deusto.ingenieria.prog3.UDExplore.domain.ReservaApartamento;
import es.deusto.ingenieria.prog3.UDExplore.io.BaseDeDatos;
import es.deusto.ingenieria.prog3.UDExplore.io.Logica;

public class VentanaResultados extends JFrame {

	private static final long serialVersionUID = 8210065439199121917L;
	private DefaultTableModel modeloDatosResultados;
	private JTextField txtFiltro;
	private JTable tablaResultados;
	private JScrollPane scrollPaneEstancias;
	private List<Estancia> estancias;

	private JComboBox<String> jComboDestino = new JComboBox<>();
	private JComboBox<String> jComboDiaEntrada = new JComboBox<>();
	private JComboBox<String> jComboMesEntrada = new JComboBox<>();
	private JComboBox<String> jComboAnioEntrada = new JComboBox<>();
	private JComboBox<String> jComboDiaSalida = new JComboBox<>();
	private JComboBox<String> jComboMesSalida = new JComboBox<>();
	private JComboBox<String> jComboAnioSalida = new JComboBox<>();

	private JCheckBox unaEstrella;
	private JCheckBox dosEstrellas;
	private JCheckBox tresEstrellas;
	private JCheckBox cuatroEstrellas;
	private JCheckBox cincoEstrellas;

	private JLabel jLabelInfo = new JLabel();
	private VentanaInicio ventanaInicio;
	private List<Estancia> estanciasDisponibles;

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public VentanaResultados(List<Estancia> estancias) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.estancias = estancias;

		int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
				.getWidth();
		int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
				.getHeight();
		this.setSize(anchoP, altoP);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("RESULTADOS");

		initTables();

		txtFiltro = new JTextField(20);
		txtFiltro.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				loadEstancias(estancias);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				loadEstancias(estancias);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});

		JButton btnVolverInicio = new JButton("Volver al Inicio");
		ventanaInicio = new VentanaInicio();

		btnVolverInicio.addActionListener(e -> {
			ventanaInicio.setVisible(true);
			dispose();

		});

//		JPanel pCombo = new JPanel();
//		JComboBox<String> comboBoxOrden = new JComboBox<>();
//		comboBoxOrden.addItem("Ordenar por:");
//		comboBoxOrden.addItem("De menor precio a mayor");
//		comboBoxOrden.addItem("De mayor precio a menor");
//		comboBoxOrden.addItem("De mejor puntuacion a peor");

//        comboBoxOrden.addActionListener( e -> {
//        	String seleccionado = comboBoxOrden.getSelectedItem().toString();
//        	
//        	if (seleccionado.equals("De menor precio a mayor")) {
//                estancias.sort(Comparator.comparingDouble(Estancia::getTarifaNoche));
//            } else if (seleccionado.equals("De mayor precio a menor")) {
//                estancias.sort(Comparator.comparingDouble(Estancia::getTarifaNoche).reversed());
//            } else if (seleccionado.equals("De mejor puntuaciÃ³n a peor")) {
//                estancias.sort(Comparator.comparingInt(Estancia::getCategoria).reversed());
//            }
//        	loadEstancias(estancias);
//        
//
//        });
		
//		pCombo.add(comboBoxOrden);

		JPanel panelBotones = new JPanel();
		panelBotones.add(btnVolverInicio);

		JPanel panelBuscador = new JPanel();
		panelBuscador.add(new JLabel("Buscar: "));
		panelBuscador.add(txtFiltro);

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

		JPanel pDestino = new JPanel();
		pDestino.setLayout(new BoxLayout(pDestino, BoxLayout.Y_AXIS));
		pDestino.setBounds(10, 50, 380, 30);
		JLabel label = new JLabel("Destino:");
		Font Fuente = new Font("SanSerif", Font.PLAIN, 10);
		label.setFont(Fuente);

		pDestino.add(label);
		pDestino.add(jComboDestino);

		JButton bBuscar = new JButton("Buscar");

		JPanel pSearch = new JPanel();
		pSearch.setPreferredSize(new Dimension(400, 200));

		JPanel pFechasE = new JPanel();
		pFechasE.setLayout(new BoxLayout(pFechasE, BoxLayout.Y_AXIS));
		pFechasE.add(new JLabel("Fecha de Entrada: "));
		pFechasE.setSize(50, 100);
		pFechasE.add(jComboDiaEntrada);
		pFechasE.add(jComboMesEntrada);
		pFechasE.add(jComboAnioEntrada);

		JPanel pFechasS = new JPanel();
		pFechasS.setLayout(new BoxLayout(pFechasS, BoxLayout.Y_AXIS));
		pFechasS.add(new JLabel("Fecha de Salida: "));
		pFechasE.setSize(50, 100);
		pFechasS.add(jComboDiaSalida);
		pFechasS.add(jComboMesSalida);
		pFechasS.add(jComboAnioSalida);
		
		Calendar calendar = Calendar.getInstance();
		Date fechaActual = calendar.getTime();
		String diaActual = new SimpleDateFormat("dd").format(fechaActual);
		String mesActual = new SimpleDateFormat("MM").format(fechaActual);
		String anioActual = new SimpleDateFormat("yyyy").format(fechaActual);
		
		if (Logica.fechaIni != null && Logica.fechaFin != null) {
		    Calendar calFechaIni = Calendar.getInstance();
		    calFechaIni.setTime(Logica.fechaIni);
		    jComboDiaEntrada.setSelectedItem(String.valueOf(calFechaIni.get(Calendar.DAY_OF_MONTH)));
		    jComboMesEntrada.setSelectedIndex(calFechaIni.get(Calendar.MONTH)); 
		    jComboAnioEntrada.setSelectedItem(String.valueOf(calFechaIni.get(Calendar.YEAR)));

		    Calendar calFechaFin = Calendar.getInstance();
		    calFechaFin.setTime(Logica.fechaFin);
		    jComboDiaSalida.setSelectedItem(String.valueOf(calFechaFin.get(Calendar.DAY_OF_MONTH)));
		    jComboMesSalida.setSelectedIndex(calFechaFin.get(Calendar.MONTH)); 
		    jComboAnioSalida.setSelectedItem(String.valueOf(calFechaFin.get(Calendar.YEAR)));
		    
		}else {

		jComboDiaEntrada.setSelectedItem(diaActual);
		jComboMesEntrada.setSelectedIndex(Integer.parseInt(mesActual) - 1);
		jComboAnioEntrada.setSelectedItem(anioActual);

		jComboDiaSalida.setSelectedItem(diaActual);
		jComboMesSalida.setSelectedIndex(Integer.parseInt(mesActual) - 1);
		jComboAnioSalida.setSelectedItem(anioActual);
		
		
		}
		

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
		JLabel iHotel = new JLabel(ventanaInicio.scaleImage("resources/images/hotel.jpeg", 200, 100));
		pHotel.add(lHotel);
		pHotel.add(iHotel);
		pHotel.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				loadHotelesTabla(filtrarHoteles());
			}
		});

		JPanel pApartamento = new JPanel();
		pApartamento.setLayout(new BoxLayout(pApartamento, BoxLayout.Y_AXIS));
		JLabel lApartamento = new JLabel("Apartamento");
		lApartamento.setFont(new Font("Serif", Font.PLAIN, 15));
		JLabel iApartamento = new JLabel(ventanaInicio.scaleImage("resources/images/apartamento.jpeg", 200, 100));
		pApartamento.add(lApartamento);
		pApartamento.add(iApartamento);
		pApartamento.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				loadApartamentosTabla(filtrarApartamentos());
			}
		});

		JPanel centralTipoAloj = new JPanel();
		centralTipoAloj.add(pHotel);
		centralTipoAloj.add(pApartamento);

		pPorTipoAloj.add(ptitulo, BorderLayout.NORTH);
		pPorTipoAloj.add(centralTipoAloj, BorderLayout.CENTER);

		JPanel pCategorias = new JPanel();
		pCategorias.add(new JLabel("Busque por categoria:"), BorderLayout.NORTH);
		pCategorias.setLayout(new BoxLayout(pCategorias, BoxLayout.X_AXIS));

		unaEstrella = new JCheckBox("★");
		dosEstrellas = new JCheckBox("★★");
		tresEstrellas = new JCheckBox("★★★");
		cuatroEstrellas = new JCheckBox("★★★★");
		cincoEstrellas = new JCheckBox("★★★★★");

		pCategorias.add(unaEstrella);
		pCategorias.add(dosEstrellas);
		pCategorias.add(tresEstrellas);
		pCategorias.add(cuatroEstrellas);
		pCategorias.add(cincoEstrellas);

		boolean alMenosUnaEstrellaSeleccionada = unaEstrella.isSelected() || dosEstrellas.isSelected()
				|| tresEstrellas.isSelected() || cuatroEstrellas.isSelected() || cincoEstrellas.isSelected();

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
		

				if (fin.before(inicio)) {
					JOptionPane.showMessageDialog(VentanaResultados.this,
							"Error: La fecha de salida no puede ser anterior o igual a la fecha de entrada.",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else if (inicio.before(hoy)) {
					JOptionPane.showMessageDialog(VentanaResultados.this,
							"Error: La fecha de entrada no puede ser anterior a la fecha actual. La entrada también tiene que ser a partir de mañana.",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else {

//                    List<Estancia> estanciasDisponibles = BaseDeDatos.buscarEstancia((String) jComboDestino.getSelectedItem(), inicio.getTime(), fin.getTime());

					boolean unaEstrellaSeleccionada = unaEstrella.isSelected();
					boolean dosEstrellasSeleccionada = dosEstrellas.isSelected();
					boolean tresEstrellasSeleccionada = tresEstrellas.isSelected();
					boolean cuatroEstrellasSeleccionada = cuatroEstrellas.isSelected();
					boolean cincoEstrellasSeleccionada = cincoEstrellas.isSelected();

					estancias.forEach(est -> {
						Hotel hotel = (Hotel) est;

						int categoria = hotel.getCategoria();

						if (!alMenosUnaEstrellaSeleccionada || (unaEstrellaSeleccionada && categoria == 1)
								|| (dosEstrellasSeleccionada && categoria == 2)
								|| (tresEstrellasSeleccionada && categoria == 3)
								|| (cuatroEstrellasSeleccionada && categoria == 4)
								|| (cincoEstrellasSeleccionada && categoria == 5)) {
							estanciasDisponibles.add(est);
						}

					});
					
					jLabelInfo.setText("Realizando busqueda...");

					estanciasDisponibles = BaseDeDatos.buscarEstancia((String) jComboDestino.getSelectedItem(),
							inicio.getTime(), fin.getTime());

					if (estanciasDisponibles == null || estanciasDisponibles.isEmpty()) {
						jLabelInfo.setText("No hay estancias disponibles para estas fechas y categorias en este destino.");
						modeloDatosResultados.setRowCount(0);
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

		ItemListener checkboxListener = e -> cargarEstanciasPorCategoria();

		unaEstrella.addItemListener(checkboxListener);
		dosEstrellas.addItemListener(checkboxListener);
		tresEstrellas.addItemListener(checkboxListener);
		cuatroEstrellas.addItemListener(checkboxListener);
		cincoEstrellas.addItemListener(checkboxListener);

		scrollPaneEstancias = new JScrollPane(tablaResultados);
		scrollPaneEstancias.setBorder(new TitledBorder("Resultados de Estancias"));

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		panelPrincipal.add(panelBotones);
		panelPrincipal.add(panelBuscador);
		panelPrincipal.add(pSearch);
		panelPrincipal.add(pPorTipoAloj);
		panelPrincipal.add(pCategorias);
//		panelPrincipal.add(pCombo);
		panelPrincipal.add(scrollPaneEstancias);

		add(panelPrincipal);
		loadEstancias(estancias);

		setVisible(true);
	}

	private void cargarEstanciasPorCategoria() {
		modeloDatosResultados.setRowCount(0);

		estancias.forEach(estancia -> {

			Hotel hotel = null;
			Apartamento apartamento = null;

			if (estancia instanceof Hotel) {
				hotel = (Hotel) estancia;
			} else {
				apartamento = (Apartamento) estancia;
			}

			// comprobacion de categoria
			boolean categoriaSeleccionada = false;
			if (!unaEstrella.isSelected() && !dosEstrellas.isSelected() && !tresEstrellas.isSelected()
					&& !cuatroEstrellas.isSelected() && !cincoEstrellas.isSelected()) {
				categoriaSeleccionada = true;
			} else {
				if (hotel != null) {
					int categoria = hotel.getCategoria();
					if (unaEstrella.isSelected() && categoria == 1) {
						categoriaSeleccionada = true;
					} else if (dosEstrellas.isSelected() && categoria == 2) {
						categoriaSeleccionada = true;
					} else if (tresEstrellas.isSelected() && categoria == 3) {
						categoriaSeleccionada = true;
					} else if (cuatroEstrellas.isSelected() && categoria == 4) {
						categoriaSeleccionada = true;
					} else if (cincoEstrellas.isSelected() && categoria == 5) {
						categoriaSeleccionada = true;
					}
				}
			}

			// aÃ±adir estancia a la tabla
			if (categoriaSeleccionada) {
				if (estancia instanceof Hotel) {
					modeloDatosResultados
							.addRow(new Object[] { estancia.getNombre(), estancia.getClass().getSimpleName(),
									estancia.getCiudad(), hotel.getCategoria(), hotel.getNumHabitaciones(), "",
									estancia.getFoto(), new String("Reservar ") + estancia.getNombre() });
				} else {
					modeloDatosResultados.addRow(new Object[] { estancia.getNombre(),
							estancia.getClass().getSimpleName(), estancia.getCiudad(), "",
							apartamento.getNumHabitaciones(), apartamento.getTarifaNoche() + "€", estancia.getFoto(),
							new String("Reservar ") + estancia.getNombre()

					});
				}

			}
		});
	}

	private void initTables() {
		Vector<String> cabeceraResultados = new Vector<>();
		cabeceraResultados.add("Nombre");
		cabeceraResultados.add("Tipo de Estancia");
		cabeceraResultados.add("Ciudad");
		cabeceraResultados.add("Categoria");
		cabeceraResultados.add("Numero de Habitaciones");
		cabeceraResultados.add("Tarifa por Noche");
		cabeceraResultados.add("Imagen");
		cabeceraResultados.add("Reserva");

		modeloDatosResultados = new DefaultTableModel(new Vector<>(), cabeceraResultados);
		tablaResultados = new JTable(modeloDatosResultados);
		tablaResultados.setDefaultEditor(Object.class, null);
		tablaResultados.setRowHeight(100);

		tablaResultados.getColumnModel().getColumn(0).setPreferredWidth(200);
		tablaResultados.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaResultados.getColumnModel().getColumn(2).setPreferredWidth(150);
		tablaResultados.getColumnModel().getColumn(3).setPreferredWidth(100);
		tablaResultados.getColumnModel().getColumn(4).setPreferredWidth(100);
		tablaResultados.getColumnModel().getColumn(5).setPreferredWidth(100);
		tablaResultados.getColumnModel().getColumn(6).setCellRenderer(imageRenderer);
		tablaResultados.getColumnModel().getColumn(7).setCellRenderer(createButtonRenderer());

		tablaResultados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					int row = tablaResultados.getSelectedRow();
					int col = tablaResultados.getSelectedColumn();

					try {
						Date inicio = sdf.parse("" + ((String) jComboDiaEntrada.getSelectedItem()) + "/"
								+ (jComboMesEntrada.getSelectedIndex() + 1) + "/"
								+ ((String) jComboAnioEntrada.getSelectedItem()));
						Date fin = sdf.parse("" + ((String) jComboDiaSalida.getSelectedItem()) + "/"
								+ (jComboMesSalida.getSelectedIndex() + 1) + "/"
								+ ((String) jComboAnioSalida.getSelectedItem()));
						if (col == 7) {
							if (estancias.get(row) instanceof Hotel) {
								Hotel hotel = (Hotel) estancias.get(row);
								new VentanaHabitaciones(hotel, inicio, fin).setVisible(true);

							} else {
								Apartamento a = (Apartamento) estancias.get(row);
								if (Logica.usuario != null) {
									ReservaApartamento reserva = new ReservaApartamento(inicio, fin,
											(Cliente) Logica.usuario);
									reserva.setApartamento(a);
									BaseDeDatos.anyadirApartamento(reserva);

								}

							}
						}

						if (row >= 0) {
							if (estancias.get(row) instanceof Hotel) {
								Hotel hotel = (Hotel) estancias.get(row);
								new VentanaHabitaciones(hotel, inicio, fin).setVisible(true);
							} else {
								Apartamento a = (Apartamento) estancias.get(row);
								if (Logica.usuario != null) {
									ReservaApartamento reserva = new ReservaApartamento(inicio, fin,
											(Cliente) Logica.usuario);
									reserva.setApartamento(a);
									BaseDeDatos.anyadirApartamento(reserva);

									new VentanaReservaApartamento(a).setVisible(true);

								} else {
									JOptionPane.showMessageDialog(null,
											"No has iniciado sesión. Por favor, inicia sesión.", "Advertencia",
											JOptionPane.WARNING_MESSAGE);
								}
							}
						}
					} catch (ParseException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

				}
			}
		});

	}

	// IA
	private TableCellRenderer createButtonRenderer() {
		return (table, value, isSelected, hasFocus, row, column) -> {
			if (column == 7) {
				JButton button = new JButton("Ver habitaciones");
				if (estancias.get(row) instanceof Hotel) {
					button.setText("Ver habitaciones");
				} else {
					button.setText("Reservar");
				}
				button.addActionListener(e -> {
					try {
						Date inicio = Logica.fechaIni;
						Date fin = Logica.fechaFin;

						if (inicio == null || fin == null) {
							JOptionPane.showMessageDialog(null, "Las fechas son nulas.", "Advertencia",
									JOptionPane.WARNING_MESSAGE);
							return;
						}if (row >= 0) {
							if (estancias.get(row) instanceof Hotel) {
								Hotel hotel = (Hotel) estancias.get(row);
								new VentanaHabitaciones(hotel, inicio, fin).setVisible(true);
							} else {
								Apartamento a = (Apartamento) estancias.get(row);
								if (Logica.usuario != null) {
									ReservaApartamento reserva = new ReservaApartamento(inicio, fin,
											(Cliente) Logica.usuario);
									reserva.setApartamento(a);
									BaseDeDatos.anyadirApartamento(reserva);

									new VentanaReservaApartamento(a).setVisible(true);

								} else {
									JOptionPane.showMessageDialog(null,
											"No has iniciado sesión. Por favor, inicia sesión.", "Advertencia",
											JOptionPane.WARNING_MESSAGE);
									VentanaLogin vl = new VentanaLogin();
									vl.setVisible(true);
								}
							}
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				});

				return button;
			}

			return null;
		};
	}

	TableCellRenderer imageRenderer = (table, value, isSelected, hasFocus, row, column) -> {
		JLabel result = new JLabel();

		if (value instanceof String) {
			String imagePath = (String) value;

			ImageIcon imageIcon = new ImageIcon(imagePath);

			if (imageIcon.getIconWidth() > 100 || imageIcon.getIconHeight() > 100) {
				imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
			}

			result.setIcon(imageIcon);
		}

		return result;
	};

	private void loadEstancias(List<Estancia> estanciasFiltradas) {
		modeloDatosResultados.setRowCount(0);

		String filtro = txtFiltro.getText().toLowerCase();

		System.out.println(estancias);

		estancias.forEach(e -> {
			if (e.getNombre().toLowerCase().contains(filtro) || filtro.isEmpty()) {
				if (e instanceof Apartamento) {
					Apartamento apar = (Apartamento) e;
					modeloDatosResultados.addRow(new Object[] { e.getNombre(), e.getClass().getSimpleName(),
							e.getCiudad(), "", apar.getNumHabitaciones(), apar.getTarifaNoche() + "€", e.getFoto(),
							new String("Reservar ") + e.getNombre()

					});
				} else {
					Hotel hotel = (Hotel) e;
					modeloDatosResultados.addRow(new Object[] { e.getNombre(), e.getClass().getSimpleName(),
							e.getCiudad(), hotel.getCategoria(), BaseDeDatos.contarHabitacionesHotel(hotel.getId()), "Segun la habitación", e.getFoto(),
							new String("Ver habitaciones") });

				}
			}
		});
	}

	private void loadHotelesTabla(ArrayList<Hotel> estanciasFiltradas) {
		modeloDatosResultados.setRowCount(0);

		estanciasFiltradas.forEach(e -> {

			Hotel hotel = (Hotel) e;
			modeloDatosResultados.addRow(
					new Object[] { e.getNombre(), e.getClass().getSimpleName(), e.getCiudad(), hotel.getCategoria(),
							BaseDeDatos.contarHabitacionesHotel(hotel.getId()), "Segun la habitación", e.getFoto(), new String("Ver habitaciones") });

		});
	}

	private void loadApartamentosTabla(ArrayList<Apartamento> estanciasFiltradas) {
		modeloDatosResultados.setRowCount(0);

		estanciasFiltradas.forEach(e -> {

			Apartamento apar = (Apartamento) e;
			modeloDatosResultados.addRow(new Object[] { e.getNombre(), e.getClass().getSimpleName(), e.getCiudad(), "",
					apar.getNumHabitaciones(), apar.getTarifaNoche() + "€", e.getFoto(),
					new String("Reservar ") + e.getNombre()

			});
		});
	}

	public ArrayList<Hotel> filtrarHoteles() {
		ArrayList<Hotel> hoteles = new ArrayList<>();
		estancias.forEach(est -> {
			if (est instanceof Hotel) {
				hoteles.add(((Hotel) est));
			}
		});
		return hoteles;
	}

	public ArrayList<Apartamento> filtrarApartamentos() {
		ArrayList<Apartamento> apartamentos = new ArrayList<>();
		estancias.forEach(est -> {
			if (est instanceof Apartamento) {
				apartamentos.add((Apartamento) est);
			}
		});
		return apartamentos;
	}

}
