package es.deusto.ingenieria.prog3.UDExplore.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


import es.deusto.ingenieria.prog3.UDExplore.domain.Estancia;
import es.deusto.ingenieria.prog3.UDExplore.domain.Hotel;
import es.deusto.ingenieria.prog3.UDExplore.domain.Main;
import es.deusto.ingenieria.prog3.UDExplore.domain.Apartamento;
import es.deusto.ingenieria.prog3.UDExplore.domain.CadenaHotelera;
import es.deusto.ingenieria.prog3.UDExplore.domain.Ciudad;

public class VentanaInicio extends JFrame{
		private static final long serialVersionUID = 1L;
		
		//JTable de tipos estancia
		private JTable jTableEstancias= new JTable();	
		private DefaultTableModel modeloDatosEstancias;
		
		//Lista de estancias que se está visualizando en la ventana
		private List<Estancia> Estancias = new ArrayList<>();
		private JComboBox<String> jComboDestino = new JComboBox<>();
		private JComboBox<String> jComboDiaEntrada = new JComboBox<>();
		private JComboBox<String> jComboMesEntrada = new JComboBox<>();
		private JComboBox<String> jComboAnioEntrada = new JComboBox<>();
		private JComboBox<String> jComboDiaSalida = new JComboBox<>();
		private JComboBox<String> jComboMesSalida = new JComboBox<>();
		private JComboBox<String> jComboAnioSalida = new JComboBox<>();
		private JLabel jLabelInfo = new JLabel("Seleccione el destino y fechas de entrada y salida");

		private List<Estancia> listaEstancias = new ArrayList<>();
		
		
		public ArrayList<Object> getEstancias() {
	        ArrayList<Object> listaEnMain = Main.getList();
			return listaEnMain;
	        
	        // Ahora puedes trabajar con la lista listaEnMain
	    }

		ArrayList<Object> lista = this.getEstancias();
		
		public VentanaInicio() {

			
			  	setTitle("UDExplore");
		        setSize(400, 300);
		        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        setLocationRelativeTo(null);
	
		
		
		        // Configurar el JComboBox
		        for (Ciudad c : Ciudad.values()) {
		            jComboDestino.addItem(c.toString());
		        }
		     // Configurar los JComboBox para fechas
		        for (int dia = 1; dia <= 31; dia++) {
		            jComboDiaEntrada.addItem(String.valueOf(dia));
		            jComboDiaSalida.addItem(String.valueOf(dia));
		        }

		        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
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
				((DefaultTableCellRenderer) jTableEstancias.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		        

		
				jLabelInfo.setHorizontalAlignment(JLabel.RIGHT);
				
				JPanel contenedorPrincipal = new JPanel(new BorderLayout());

				JPanel pBotones = new JPanel();
				JButton bRegistro = new JButton("Registrarse");
				JButton bInicioS= new JButton("Iniciar sesión");
				pBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
				pBotones.add(bRegistro);
				pBotones.add(bInicioS);
				
				
				
				JPanel pfoto = new JPanel();
				JLabel iIcono = new JLabel(scaleImage("resources/images/imagenHotel.png",150,150));
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
		        pSearch.setPreferredSize(new Dimension(400,200));
		        
		        pSearch.add(pfoto);
		        pSearch.add(pDestino);
		        pSearch.add(pFechasE);
		        pSearch.add(pFechasS);
		        pSearch.add(bBuscar);
		        pSearch.setLayout(new GridBagLayout());
		        
		        
		        JPanel pPorTipoAloj = new JPanel();
				pPorTipoAloj.setLayout(new BoxLayout(pPorTipoAloj, BoxLayout.X_AXIS));
				pPorTipoAloj.setPreferredSize(new Dimension(200,200));
				
				JPanel ptitulo = new JPanel();
				JLabel ltitulo = new JLabel("Buscar por tipo de alojamiento:");
				ptitulo.add(ltitulo);
				
				JPanel pHotel = new JPanel();
				pHotel.setLayout(new FlowLayout());
				JLabel lHotel = new JLabel("Hotel");
				JLabel iHotel = new JLabel(scaleImage("resources/images/hotel.jpeg",200,100));
				pHotel.add(lHotel);
				pHotel.add(iHotel);
				pHotel.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						List<Hotel> hoteles= filtrarHoteles(Estancias);
						
				 		
						
						
						
						Hotel hotelMadrid = new Hotel("Hotel Madrid Centro", Ciudad.Madrid, 100, 150.0,"resources/images/madrid.jpg", 4, CadenaHotelera.GRANDSPLENDOUR,new ArrayList<>()) ;
						Hotel hotelBarcelona = new Hotel("Hotel Barcelona Playa", Ciudad.Barcelona, 80, 120.0,"", 3, CadenaHotelera.LUXURYRESORTS, new ArrayList<>());
						Hotel hotelSevilla = new Hotel("Hotel Sevilla Histórico", Ciudad.Sevilla, 60, 100.0, "", 4, CadenaHotelera.SUNSETRETREAT, new ArrayList<>());
						hoteles.add(hotelMadrid);
				 		hoteles.add(hotelBarcelona);
						ArrayList<Estancia> estanciasFiltradas = new ArrayList<>();
						for (Hotel h : hoteles) {
							estanciasFiltradas.add(h);
							
						}
						if (e.getClickCount() == 2) {
							VentanaResultados ventana = new VentanaResultados(estanciasFiltradas);
							ventana.setVisible(true);
						}
					}
				});
				
			
					
					
				
				
				JPanel pApartamento= new JPanel();
				pApartamento.setLayout(new FlowLayout());
				JLabel lApartamento = new JLabel("Apartamento");
				JLabel iApartamento = new JLabel(scaleImage("resources/images/apartamento.jpeg",200,100));
				pApartamento.add(lApartamento);
				pApartamento.add(iApartamento);
				pApartamento.addMouseListener(new MouseAdapter() {
		
						public void mouseClicked(MouseEvent e) {
							List<Apartamento> apartamentos= filtrarApartamentos(Estancias);
							ArrayList<Estancia> estanciasFiltradas = new ArrayList<>();
							for (Apartamento a:  apartamentos) {
								estanciasFiltradas.add(a);	
							}
							if (e.getClickCount() == 2) {
								VentanaResultados ventana = new VentanaResultados(estanciasFiltradas);
								ventana.setVisible(true);
							}
						
					
						
						
						
						
					}
				});
				
				
				
				
				
				pPorTipoAloj.add(ptitulo, BorderLayout.NORTH);
				pPorTipoAloj.add(pHotel, BorderLayout.CENTER);
				pPorTipoAloj.add(pApartamento, BorderLayout.CENTER);
				
			
			
				

				contenedorPrincipal.add(pBotones, BorderLayout.NORTH);
			    contenedorPrincipal.add(pSearch, BorderLayout.CENTER);
				
				
				
		    
				
		    	add(contenedorPrincipal, BorderLayout.NORTH);
				add(new JScrollPane(jTableEstancias), BorderLayout.CENTER);
				add(jLabelInfo, BorderLayout.SOUTH);
				add(pPorTipoAloj, BorderLayout.CENTER);
				
				
				
				
		    
		}
		
		//IA generated
				
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
				
		
		public ArrayList<Hotel> filtrarHoteles(List<Estancia> Estancias) {
		    ArrayList<Hotel> hoteles = new ArrayList<>();
		    for (Estancia e : Estancias) {
		        if (e instanceof Hotel) {
		            hoteles.add((Hotel) e);
		        }
		    }
		    return hoteles;
		}
		public ArrayList<Apartamento> filtrarApartamentos(List<Estancia> Estancias) {
		    ArrayList<Apartamento> apartamentos = new ArrayList<>();
		    for (Estancia e : Estancias) {
		        if (e instanceof Apartamento) {
		            apartamentos.add((Apartamento) e);
		        }
		    }
		    return apartamentos;
		}


	    public static void main(String[] args) {

	    	SwingUtilities.invokeLater(() -> {
	            VentanaInicio ventana = new VentanaInicio();
	            ventana.setVisible(true);
	        });
	    }
	  
	
		
	}

