package es.deusto.ingenieria.prog3.UDExplore.domain;


public class Reseña {
    private String textoReseña;
    private Cliente usuario;
    private String estancia;

    public Reseña(String textoReseña, Cliente usuario, String nombreEstancia) {
        this.textoReseña = textoReseña;
        this.usuario = usuario;
        this.estancia = nombreEstancia;
    }

    public String getTextoReseña() {
        return textoReseña;
    }

    public Cliente getUsuario() {
        return usuario;
    }

    public String getEstancia() {
        return estancia;
    }

    @Override
    public String toString() {
        return "Reseña de " + usuario.getNombreUsuario() + " para " + estancia + ":\n" + textoReseña;
    }
}
