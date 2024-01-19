package es.deusto.ingenieria.prog3.UDExplore.domain;



public class ReservaConEstancia {
    private Reserva reserva;
    private Estancia estancia;

    public ReservaConEstancia(Reserva reserva, Estancia estancia) {
        this.reserva = reserva;
        this.estancia = estancia;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public Estancia getEstancia() {
        return estancia;
    }
}
