package es.deusto.ingenieria.prog3.UDExplore.gui;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import es.deusto.ingenieria.prog3.UDExplore.domain.Apartamento;
import es.deusto.ingenieria.prog3.UDExplore.domain.Estancia;
import es.deusto.ingenieria.prog3.UDExplore.domain.Hotel;
import es.deusto.ingenieria.prog3.UDExplore.io.Logica;



public class VentanaResultados extends JFrame {
    private static final long serialVersionUID = 8210065439199121917L;
	private DefaultTableModel modeloDatosResultados;
    private JTextField txtFiltro;
    private JTable tablaResultados;
    private JScrollPane scrollPaneEstancias;
    private List<Estancia> estancias;
    JComboBox<String> jComboDiaEntrada = new JComboBox<>();
    JComboBox<String> jComboMesEntrada = new JComboBox<>();
    JComboBox<String> jComboAnioEntrada = new JComboBox<>();
    JComboBox<String> jComboDiaSalida = new JComboBox<>();
    JComboBox<String> jComboMesSalida = new JComboBox<>();
    JComboBox<String> jComboAnioSalida = new JComboBox<>();
	private JLabel jLabelInfo = new JLabel();
    

    public VentanaResultados(List<Estancia> estancias) {
    	
    	

    	
    	SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
    	this.estancias = estancias;
    	
    	int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
		int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
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
      

        btnVolverInicio.addActionListener(e -> {
        	new VentanaInicio().setVisible(true); 
			dispose();
		
        });
        
       
        
        JPanel pCombo = new JPanel();
        JComboBox<String> comboBoxOrden = new JComboBox<>();
        comboBoxOrden.addItem("Ordenar por:");
        comboBoxOrden.addItem("De menor precio a mayor");
        comboBoxOrden.addItem("De mayor precio a menor");
        comboBoxOrden.addItem("De mejor puntuación a peor");
        
        comboBoxOrden.addActionListener( e -> {
        	String seleccionado = comboBoxOrden.getSelectedItem().toString();
        	
        	if (seleccionado.equals("De menor precio a mayor")) {
                estancias.sort(Comparator.comparingDouble(Estancia::getTarifaNoche));
            } else if (seleccionado.equals("De mayor precio a menor")) {
                estancias.sort(Comparator.comparingDouble(Estancia::getTarifaNoche).reversed());
            } else if (seleccionado.equals("De mejor puntuación a peor")) {
                estancias.sort(Comparator.comparingInt(Estancia::getCategoria).reversed());
            }
        	loadEstancias(estancias);
        

        });
        pCombo.add(comboBoxOrden);
        
        
        
        
       
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnVolverInicio);
       

        JPanel panelBuscador = new JPanel();
        panelBuscador.add(new JLabel("Buscar: "));
        panelBuscador.add(txtFiltro);
        
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
		JPanel pFechas = new JPanel();
		pFechas.setPreferredSize(new Dimension(200,100));
		
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
        
        pFechas.add(pFechasE);
        pFechas.add(pFechasS);
        JButton bBuscar = new JButton("Buscar");
        pFechas.add(bBuscar, BorderLayout.SOUTH);
        
      
        JPanel pCategorias  = new JPanel();
        pCategorias.add(new JLabel("Busque por categoría:"), BorderLayout.NORTH);
        pCategorias.setLayout(new BoxLayout(pCategorias, BoxLayout.X_AXIS));
        JCheckBox unaEstrella = new JCheckBox("★");
        JCheckBox dosEstrellas = new JCheckBox("★★");
        JCheckBox tresEstrellas = new JCheckBox("★★★");
        JCheckBox cuatroEstrellas = new JCheckBox("★★★★");
        JCheckBox cincoEstrellas = new JCheckBox("★★★★★");
        
        pCategorias.add(unaEstrella);
        pCategorias.add(dosEstrellas);
        pCategorias.add(tresEstrellas);
        pCategorias.add(cuatroEstrellas);
        pCategorias.add(cincoEstrellas);
        
        
        
        bBuscar.addActionListener(e -> {
            try {
                Date inicio = sdf.parse("" + ((String) jComboDiaEntrada.getSelectedItem()) + "/" + (jComboMesEntrada.getSelectedIndex() + 1) + "/" + ((String) jComboAnioEntrada.getSelectedItem()));
                Date fin = sdf.parse("" + ((String) jComboDiaSalida.getSelectedItem()) + "/" + (jComboMesSalida.getSelectedIndex() + 1) + "/" + ((String) jComboAnioSalida.getSelectedItem()));
     
              
                if (fin.before(inicio)) {
                    
                } else {
                   
                    List<Estancia> estanciasDisponibles = new ArrayList<>();
                   
                    boolean alMenosUnaEstrellaSeleccionada = unaEstrella.isSelected() || dosEstrellas.isSelected() || tresEstrellas.isSelected() || cuatroEstrellas.isSelected() || cincoEstrellas.isSelected();

  
                    boolean unaEstrellaSeleccionada = unaEstrella.isSelected();
                    boolean dosEstrellasSeleccionada = dosEstrellas.isSelected();
                    boolean tresEstrellasSeleccionada = tresEstrellas.isSelected();
                    boolean cuatroEstrellasSeleccionada = cuatroEstrellas.isSelected();
                    boolean cincoEstrellasSeleccionada = cincoEstrellas.isSelected();
           
                    estancias.forEach(est -> {
                    	
                        if (Logica.estanciaDisponibleEnFechas(est, inicio, fin)) {
                            
                            int categoria = est.getCategoria();

                            if ( !alMenosUnaEstrellaSeleccionada || 
                            	(unaEstrellaSeleccionada && categoria == 1) ||
                                (dosEstrellasSeleccionada && categoria == 2) ||
                                (tresEstrellasSeleccionada && categoria == 3) ||
                                (cuatroEstrellasSeleccionada && categoria == 4) ||
                                (cincoEstrellasSeleccionada && categoria == 5)) {
                                estanciasDisponibles.add(est);
                            }
                        }
                    });

                    if (estanciasDisponibles.isEmpty()) {
                        jLabelInfo.setText("No hay estancias disponibles para estas fechas y categorías en este destino.");
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
        });

        
 

        scrollPaneEstancias = new JScrollPane(tablaResultados);
        scrollPaneEstancias.setBorder(new TitledBorder("Resultados de Estancias"));

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.add(panelBotones);
        panelPrincipal.add(panelBuscador);
        panelPrincipal.add(pFechas);
        panelPrincipal.add(pCategorias);
        panelPrincipal.add(pCombo);  
        panelPrincipal.add(scrollPaneEstancias);
        
       

        add(panelPrincipal);
        loadEstancias(estancias);

  
        setVisible(true);
    }

    private void initTables() {
        Vector<String> cabeceraResultados = new Vector<>();
        cabeceraResultados.add("Nombre");
        cabeceraResultados.add("Tipo de Estancia");
        cabeceraResultados.add("Ciudad");
        cabeceraResultados.add("Categoria");
        cabeceraResultados.add("Número de Habitaciones");
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
        tablaResultados.getColumnModel().getColumn(7).setPreferredWidth(100);
        
        tablaResultados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = tablaResultados.getSelectedRow();
                    int col = tablaResultados.getSelectedColumn();
                    

                    if (col == 7) { 
                    	if (estancias.get(row) instanceof Hotel) {
                    		Hotel hotel = (Hotel) estancias.get(row);
                    		System.out.println("hola");
                    		new VentanaHabitaciones(hotel).setVisible(true);
                    		
                    	} else {
                    	Estancia estancia = estancias.get(row);
                        new VentanaReserva(estancia).setVisible(true);
                       
                    	}
                    }
                }
            }
        });
        
     

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
            	if ( e instanceof Apartamento) {
            	
                modeloDatosResultados.addRow(new Object[]{
                        e.getNombre(),
                        e.getClass().getSimpleName(),
                        e.getCiudad(),
                        e.getCategoria(),
                        e.getNumeroHabitaciones(),
                        e.getTarifaNoche() + "€",
                        e.getFoto(),
                        new String("Reservar ")+e.getNombre()
                 
                
                     
                });
            }else{
            	 modeloDatosResultados.addRow(new Object[]{
                         e.getNombre(),
                         e.getClass().getSimpleName(),
                         e.getCiudad(),
                         e.getCategoria(),
                         e.getNumeroHabitaciones(),
                         e.getTarifaNoche() + "€",
                         e.getFoto(),
                         new String("Ver habitaciones")
            	 });
            	
            	}
            }
        });
    }
    
    
  
}

