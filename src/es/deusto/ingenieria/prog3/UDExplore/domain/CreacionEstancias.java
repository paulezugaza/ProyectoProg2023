package es.deusto.ingenieria.prog3.UDExplore.domain;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreacionEstancias {

    public static void main(String[] args) {
        List<Estancia> estancias = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            Estancia estancia;
            if (i % 2 == 0) {
                estancia = crearHotelConHabitaciones(i); // Crear hotel con habitaciones
            } else {
                estancia = crearApartamento(i);
            }
            estancias.add(estancia);
        }

        for (Estancia estancia : estancias) {
            System.out.println(estancia);
        }
    }

    public static Estancia crearHotelConHabitaciones(int id) {
        String nombre = generarNombreHotel();
        Ciudad ciudad = generarCiudadAleatoria();
        int categoria = new Random().nextInt(5) + 1; 
        int numeroHabitaciones = new Random().nextInt(100) + 1; 
        double tarifaNoche = (double) Math.round((new Random().nextDouble() * 150) * 100) / 100; 
        String imagen = "Resources/images/" + ciudad.toString().toLowerCase() + ".jpg";
        CadenaHotelera cadenaHotelera = generarCadenaHoteleraAleatoria();

        Hotel hotel = new Hotel( nombre, ciudad, categoria, numeroHabitaciones, tarifaNoche, imagen, cadenaHotelera, new ArrayList<Habitacion>());

     
        int numHabitaciones = new Random().nextInt(20) + 1; 
        for (int i = 1; i <= numHabitaciones; i++) {
            int capacidadMaxima = new Random().nextInt(3) + 2; 
            double precioPorNoche = 50.0 + new Random().nextDouble() * 150.0; 
            precioPorNoche = Math.round(precioPorNoche * 100.0) / 100.0; 
            Habitacion habitacion = new Habitacion(i, capacidadMaxima, precioPorNoche);
            hotel.addHabitacion(habitacion);
        }

        return hotel;
    }

    public static Estancia crearApartamento(int id) {
        String nombre = generarNombreApartamento();
        Ciudad ciudad = generarCiudadAleatoria();
        int categoria = new Random().nextInt(5) + 1;
        int numeroHabitaciones = new Random().nextInt(4) + 1;
        double tarifaNoche = (double) Math.round((new Random().nextDouble() * 150) * 100) / 100; // Tarifa entre 0.01 y 150.00
        String imagen = "Resources/images/" + ciudad.toString().toLowerCase() + ".jpg";
        return new Apartamento( nombre, ciudad, categoria,  numeroHabitaciones, tarifaNoche, imagen);
    }

    public static String generarNombreHotel() {
        String[] nombres = {
            "Hotel Majestic",
            "Ritz-Carlton Hotel",
            "Hilton Garden Inn",
            "Marriott Hotel",
            "InterContinental Hotel",
            "Sheraton Hotel",
            "Westin Hotel",
            "Fairmont Hotel",
            "Hyatt Regency",
            "Waldorf Astoria Hotel"
        };
        return nombres[new Random().nextInt(nombres.length)];
    }

    public static String generarNombreApartamento() {
        String[] nombres = {
            "Beachfront Apartment",
            "Downtown Loft",
            "City View Studio",
            "Penthouse Suite",
            "Cozy Retreat",
            "Luxury Duplex",
            "Seaside Villa",
            "Mountain View Cabin",
            "Lakefront Cottage",
            "Charming Bungalow"
        };
        return nombres[new Random().nextInt(nombres.length)];
    }

    public static Ciudad generarCiudadAleatoria() {
        Ciudad[] ciudades = Ciudad.values();
        return ciudades[new Random().nextInt(ciudades.length)];
    }

    public static CadenaHotelera generarCadenaHoteleraAleatoria() {
        CadenaHotelera[] cadenas = CadenaHotelera.values();
        return cadenas[new Random().nextInt(cadenas.length)];
    }
    
}

