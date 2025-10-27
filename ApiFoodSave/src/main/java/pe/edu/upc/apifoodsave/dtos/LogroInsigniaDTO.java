package pe.edu.upc.apifoodsave.dtos;

import java.time.LocalDateTime;

public class LogroInsigniaDTO {
    private String username;
    private String nombreLogro;
    private Integer puntosTotales;
    private Double kgTotales;
    private LocalDateTime fechaLogro;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombreLogro() {
        return nombreLogro;
    }

    public void setNombreLogro(String nombreLogro) {
        this.nombreLogro = nombreLogro;
    }

    public Integer getPuntosTotales() {
        return puntosTotales;
    }

    public void setPuntosTotales(Integer puntosTotales) {
        this.puntosTotales = puntosTotales;
    }

    public Double getKgTotales() {
        return kgTotales;
    }

    public void setKgTotales(Double kgTotales) {
        this.kgTotales = kgTotales;
    }

    public LocalDateTime getFechaLogro() {
        return fechaLogro;
    }

    public void setFechaLogro(LocalDateTime fechaLogro) {
        this.fechaLogro = fechaLogro;
    }
}
