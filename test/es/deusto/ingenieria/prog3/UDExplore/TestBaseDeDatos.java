package es.deusto.ingenieria.prog3.UDExplore;

import static org.junit.Assert.*;

import java.sql.Statement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.deusto.ingenieria.prog3.UDExplore.io.BaseDeDatos;
import es.deusto.ingenieria.prog3.UDExplore.domain.*;
import es.deusto.ingenieria.prog3.UDExplore.gui.*;

public class TestBaseDeDatos {

	private static String testBaseDatos = "test.db";
//	private static final Logger logger = Logger.getLogger("TestBaseDatos");
	private static BaseDeDatos baseDatos;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		baseDatos.abrirConexion(testBaseDatos, true);
		baseDatos.cargarHoteles();
		baseDatos.cargarUsuarios();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		baseDatos.cerrarConexion();
	}

	@Before
	public void setUp() {
		assertTrue(baseDatos.abrirConexion(testBaseDatos, true));
	}

	@After
	public void tearDown() {
		baseDatos.cerrarConexion();
	}
	
	public void testAnyadirCliente() {
		//cliente de pruba y añadirlo a la base de datos
		Cliente cliente = new Cliente("Cliene1", "ClienteApellido", "correo@electronico.com", "contrasenya1");
		int clienteId = baseDatos.anyadirCliente(cliente);
		//hay al menos un cliente
		assertTrue(clienteId > 0);
	}
	
	public void testAnyadirReserva() {
		//reserva de prueba
		Cliente cliente = new Cliente("Cliene1", "ClienteApellido", "correo@electronico.com", "contrasenya1");
		int clienteId = baseDatos.anyadirCliente(cliente);
		ReservaHotel reservaHotel = new ReservaHotel(new java.util.Date(), new java.util.Date(), cliente);
		int idReservaHotel = baseDatos.anyadirHabitacion(reservaHotel);
		assertTrue(idReservaHotel>0);
		
	}
	
	public void testCargarReservasPorUsuario() {
		Cliente cliente = new Cliente("Nombre", "Apellido", "email@email.com", "Password");
		int idCliente = baseDatos.anyadirCliente(cliente);
		Date fechaInicio = new Date();
	    Date fechaFin = new Date(fechaInicio.getTime() + 86400000);
	        
		ReservaHotel reserva1 = new ReservaHotel(new java.util.Date(), new java.util.Date(), cliente);
		ReservaHotel reserva2 = new ReservaHotel(new java.util.Date(), new java.util.Date(), cliente);
		
		int idReserva1 = BaseDeDatos.anyadirHabitacion(reserva1);
        assertTrue(idReserva1 > 0);
        int idReserva2 = BaseDeDatos.anyadirHabitacion(reserva2);
        assertTrue(idReserva2 > 0);
	
        List<ReservaConEstancia> reservasPorUsuario = BaseDeDatos.cargarReservasPorUsuario(idCliente);
        assertNotNull(reservasPorUsuario);
        assertEquals(2, reservasPorUsuario);
        
        boolean reserva1Encon = false;
        boolean reserva2Encon = false;
        
        for(ReservaConEstancia reserva : reservasPorUsuario) {
        	if(reserva.getReserva().getNumeroReserva() == idReserva1) {
        		reserva1Encon = true;
        	} else if(reserva.getReserva().getNumeroReserva() == idReserva2) {
        		reserva2Encon = true;
        	}
        }
        assertTrue(reserva1Encon && reserva2Encon);
	}
	
	//cargar ficheros csv
//	private static List<Reserva> importarReservas(){
//		List<Reserva> reservas = new ArrayList<>();
//		
//		try(BufferedReader bfr = new BufferedReader(new FileReader("Resources/data/reservas.csv"))){
//			String line;
//			bfr.readLine();
//			while((line = bfr.readLine()) != null) {
//				reservas.add(Reserva.parseCSV(line));
//			}
//			
//		}catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return reservas;
//	}
	

//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}

}
