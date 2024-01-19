package es.deusto.ingenieria.prog3.UDExplore;

import static org.junit.Assert.*;


import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;


import es.deusto.ingenieria.prog3.UDExplore.io.BaseDeDatos;
import es.deusto.ingenieria.prog3.UDExplore.domain.*;


public class TestBaseDeDatos {

	private static String testBaseDatos = "test.db";
@BeforeClass
	public static void setUpBeforeClass() {
		BaseDeDatos.abrirConexion(testBaseDatos, true);
		BaseDeDatos.cargarHoteles();
		BaseDeDatos.cargarUsuarios();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDeDatos.cerrarConexion();
	}

	@Before
	public void setUp() {
		assertTrue(BaseDeDatos.abrirConexion(testBaseDatos, true));
	}

	@After
	public void tearDown() {
		BaseDeDatos.cerrarConexion();
	}
	
	public void testAnyadirCliente() {
		//cliente de pruba y añadirlo a la base de datos
		Cliente cliente = new Cliente("Cliene1", "ClienteApellido", "correo@electronico.com", "contrasenya1");
		int clienteId = BaseDeDatos.anyadirCliente(cliente);
		//hay al menos un cliente
		assertTrue(clienteId > 0);
	}
	
	public void testAnyadirReserva() {
		//reserva de prueba
		Cliente cliente = new Cliente("Cliene1", "ClienteApellido", "correo@electronico.com", "contrasenya1");
		BaseDeDatos.anyadirCliente(cliente);
		ReservaHotel reservaHotel = new ReservaHotel(new java.util.Date(), new java.util.Date(), cliente);
		int idReservaHotel = BaseDeDatos.anyadirHabitacion(reservaHotel);
		assertTrue(idReservaHotel>0);
		
	}
	
	public void testCargarReservasPorUsuario() {
		Cliente cliente = new Cliente("Nombre", "Apellido", "email@email.com", "Password");
		int idCliente = BaseDeDatos.anyadirCliente(cliente);
		Date fechaInicio = new Date();
	    new Date(fechaInicio.getTime() + 86400000);
	        
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
	
	
}
