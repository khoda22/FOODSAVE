package pe.edu.upc.apifoodsave.dtos;

import java.time.LocalDate;

public class IntercambioDTOList {
    private Integer idIntercambio;
    private String titulo;
    private LocalDate fecha;
    private String ubicacion;

    public Integer getIdIntercambio() {
        return idIntercambio;
    }

    public void setIdIntercambio(Integer idIntercambio) {
        this.idIntercambio = idIntercambio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
