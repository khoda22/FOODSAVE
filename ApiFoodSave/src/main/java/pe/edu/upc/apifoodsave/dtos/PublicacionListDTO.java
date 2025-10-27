package pe.edu.upc.apifoodsave.dtos;

import java.time.LocalDateTime;

public class PublicacionListDTO {
    private int idPublicacion;
    private String contenidoPublicacion;
    private String fotoUrlPublicacion;
    private LocalDateTime fechaCreacionPublicacion;
    private String username;

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getContenidoPublicacion() {
        return contenidoPublicacion;
    }

    public void setContenidoPublicacion(String contenidoPublicacion) {
        this.contenidoPublicacion = contenidoPublicacion;
    }

    public String getFotoUrlPublicacion() {
        return fotoUrlPublicacion;
    }

    public void setFotoUrlPublicacion(String fotoUrlPublicacion) {
        this.fotoUrlPublicacion = fotoUrlPublicacion;
    }

    public LocalDateTime getFechaCreacionPublicacion() {
        return fechaCreacionPublicacion;
    }

    public void setFechaCreacionPublicacion(LocalDateTime fechaCreacionPublicacion) {
        this.fechaCreacionPublicacion = fechaCreacionPublicacion;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
