package es.deusto.ingenieria.prog3.UDExplore.io;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.deusto.ingenieria.prog3.UDExplore.domain.Administrador;
import es.deusto.ingenieria.prog3.UDExplore.domain.Cliente;
import es.deusto.ingenieria.prog3.UDExplore.domain.Usuario;



public class BaseDeDatos {
	
	private static Connection conexion;
	private static Logger logger = Logger.getLogger( "BaseDeDatos" );
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static HashMap<String,Usuario> users = new HashMap<String, Usuario>();
	
	public static boolean abrirConexion( String nombreBD, boolean reiniciaBD ) {
		try {
			logger.log( Level.INFO, "Carga de librería org.sqlite.JDBC" );
			Class.forName("org.sqlite.JDBC");  // Carga la clase de BD para sqlite
			logger.log( Level.INFO, "Abriendo conexión con " + nombreBD );
			conexion = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
			if (reiniciaBD) {
				Statement statement = conexion.createStatement();
				
				String sent = "CREATE TABLE IF NOT EXISTS usuario(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre varchar(10), email varchar(25), contrasenya varchar(25), admin int);";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				
				sent = "CREATE TABLE IF NOT EXISTS reserva(numeroReserva INTEGER PRIMARY KEY AUTOINCREMENT, fechaInicio bigint, fechaFin bigint, idUsuario INTEGER REFERENCES usuario (id));";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				
				sent = "CREATE TABLE IF NOT EXISTS Habitacion(numeroHabitacion INTEGER PRIMARY KEY AUTOINCREMENT, capacidadMax integer, precioPorNoche integer, iDhotel INTEGER REFERENCES hotel(id));";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );

				sent = "CREATE TABLE IF NOT EXISTS Hotel(id INTEGER PRIMARY KEY AUTOINCREMENT, cadenaHotelera varchar(10));";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "CREATE TABLE IF NOT EXISTS ReservasHotel(id INTEGER PRIMARY KEY AUTOINCREMENT, iDHabitacion INTEGER REFERENCES Habitacion(id),iDhotel INTEGER REFERENCES hotel(id));";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				
			}
			
			return true;
		} catch(Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}	
	
	public static void cerrarConexion() {
		try {
			logger.log( Level.INFO, "Cerrando conexión" );
			conexion.close();
		} catch (SQLException e) {
			logger.log( Level.SEVERE, "Excepción", e );
		}
	}
	
	public static HashMap<String,Usuario> getUsuarios(){
		try (Statement statement = conexion.createStatement()){
			String sent = "select * from usuario;";
			logger.log( Level.INFO, "Statement: " + sent );
			ResultSet rs = statement.executeQuery( sent );
			while( rs.next() ) { // Leer el resultset
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String email = rs.getString("email");
				String constrasenya = rs.getString("contrasenya");
				if(rs.getInt("admin")==1) {
					Administrador a =new Administrador(nombre, email, constrasenya );
					a.setCodigoUsuario(id);
					users.put(email,  a);		
				}
				else {
					Cliente c = new Cliente(nombre, email, constrasenya, rs.getInt("admin")) ;
					c.setCodigoUsuario(id);
					users.put(email,c);
				}
			}
			return users;
		 } catch (SQLException e) {
		        logger.log(Level.SEVERE, "Excepción SQL", e);
		        return null; 
		    }
	}
	
	
	
	public static void añadirUsuario(String nombre, String email, String contrasenya) {
		String sent="";
		try(Statement statement = conexion.createStatement()) {
			sent = "insert into usuario (nombre, email, contrasenya, admin) values ('" + nombre +"', '" + email + "', '" + contrasenya + "', 0);";
			logger.log( Level.INFO, "Lanzada actualización a base de datos: " + sent );
			int val = statement.executeUpdate( sent );
			logger.log( Level.INFO, "Añadida " + val + " fila a base de datos\t" + sent );
			
		} catch (SQLException e) {
			logger.log( Level.SEVERE, "Error en inserción de base de datos\t" + e );
		}
	}
	
}
