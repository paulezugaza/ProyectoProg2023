package es.deusto.ingenieria.prog3.UDExplore.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import es.deusto.ingenieria.prog3.UDExplore.domain.Estancia;

import es.deusto.ingenieria.prog3.UDExplore.domain.Main;

import es.deusto.ingenieria.prog3.UDExplore.domain.Ciudad;

public class VentanaInicio  extends JFrame{
		private static final long serialVersionUID = 1L;
		
		//JTable de tipos estancia
		private JTable jTableEstancias= new JTable();	
		
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
		        
		        
		    	//Inicialización de la tabla de  estancias
		        jTableEstancias.setRowHeight(30);
				jTableEstancias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
				((DefaultTableCellRenderer) jTableEstancias.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		        

				
				//Inicialización del label de información
				jLabelInfo.setHorizontalAlignment(JLabel.RIGHT);
		     
		        JPanel pDestino = new JPanel();
		        pDestino.setBounds(10, 50, 380, 30);
		        JLabel label = new JLabel("¿A donde desea viajar? ");
		        Font Fuente = new Font("SanSerif", Font.PLAIN, 20);
		        label.setFont(Fuente);
		     
		        pDestino.add(label);
		        pDestino.add(jComboDestino);
		        
		        JPanel pFechasE = new JPanel();
		        pFechasE.add(new JLabel("Fecha de Entrada: "));
		        pFechasE.setBounds(10, 90, 380, 30);
		        pFechasE.add(jComboDiaEntrada);
		        pFechasE.add(jComboMesEntrada);
		        pFechasE.add(jComboAnioEntrada);
		        
		        JPanel pFechasS = new JPanel();
		        pFechasS.setBounds(10, 130, 380, 30);
		        pFechasS.add(new JLabel("Fecha de Salida: "));
		        pFechasS.add(jComboDiaSalida);
		        pFechasS.add(jComboMesSalida);
		        pFechasS.add(jComboAnioSalida);

		        JPanel pSearch = new JPanel();
		        
		        pSearch.add(pDestino);
		        pSearch.add(pFechasE);
		        pSearch.add(pFechasS);
		        pSearch.setLayout(new GridBagLayout());
		    
		        setIconImage(new ImageIcon("resources/images/imagenHotel.png").getImage());	
		    	add(pSearch, BorderLayout.NORTH);
				add(new JScrollPane(jTableEstancias), BorderLayout.CENTER);
				add(jLabelInfo, BorderLayout.SOUTH);
		    

		        setVisible(true);
		    }
		
			
		public void updateEstancias() {
			
		}
	
		
	


	    public static void main(String[] args) {
	        VentanaInicio ventana = new VentanaInicio();
	        ventana.updateEstancias(); 
	    }
		 
	  
	
		
	}

