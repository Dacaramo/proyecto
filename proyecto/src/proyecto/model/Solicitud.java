package proyecto.model;

import java.util.UUID;

public class Solicitud {
    String id;
    Aspirante aspirante;
    String nombreOferta;

    public Solicitud(Aspirante aspirante, String nombreOferta) {
        this.id = UUID.randomUUID().toString();
        this.aspirante = aspirante;
        this.nombreOferta = nombreOferta;
    }
}
