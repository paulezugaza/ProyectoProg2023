package es.deusto.ingenieria.prog3.UDExplore;

import static org.junit.Assert.*;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.deusto.ingenieria.prog3.UDExplore.io.BaseDeDatos;
import es.deusto.ingenieria.prog3.UDExplore.domain.Cliente;

public class TestBaseDeDatos {

	private static final String testBaseDatos = "test.db";
	private static final Logger logger = Logger.getLogger("TestBaseDatos");
	private static BaseDeDatos baseDatos = null;
	
//	@BeforeClass
//	public static void setUpBeforeClass() {
//		
//	}

//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//	}

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
		int clienteId = baseDatos.anyadirCliente(cliente);
		//hay al menos un cliente
		assertTrue(clienteId > 0);
	}
	
	public void testAnyadirReserva() {
		//reserva de prueba
		Date fechaInicio = new Date();
		Date fechaFin = new Date(fechaInicio.getTime() + (3*86400000));
		int usuarioId = 1;
		
		int reserva = baseDatos.anyadirReserva(fechaInicio, fechaFin, usuarioId);
		assertTrue(reserva > 0);
	}
	
//	public static void testCargarHoteles() {
//		//añadir hotel y cadena de hoteles a la base de datos para recuperarlo
//		try (Statement stmnt = baseDatos.conexion.createStatement()){
//			String cadenaPrueba = "INSERT INTRO CadenaHotelera(id, nombre) VALUE (1, 'CadenaPrueba');";
//			stmnt.executeQuery(cadenaPrueba);
//			
//			String hotelPrueba = "INSERT INTO Hotel (nombre, ciudad, foto, categoria, idCadenaHotelera) VALUES ('HotelPrueba', 'CiudadPrueba', 'fotoPrueba.jpg', 3, 1);";
//			stmnt.executeQuery(hotelPrueba);
//		} catch (SQLException e) {
//			logger.log(Level.WARNING, "Error durante la insercion de datos de prueba");
//		}
//		
//		assertNotNull(baseDatos.cargarHoteles());
//		
//	}

//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}

}
