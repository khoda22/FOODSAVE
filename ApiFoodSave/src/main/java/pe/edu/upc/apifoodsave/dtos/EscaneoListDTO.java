package pe.edu.upc.apifoodsave.dtos;

import java.time.LocalDate;

public class EscaneoListDTO {
    private Integer idEscaneo;
    private LocalDate fechaEscaneo;
    private String origen;
    private Integer idProducto;
    private String nombreProducto;

    public Integer getIdEscaneo() {
        return idEscaneo;
    }

    public void setIdEscaneo(Integer idEscaneo) {
        this.idEscaneo = idEscaneo;
    }

    public LocalDate getFechaEscaneo() {
        return fechaEscaneo;
    }

    public void setFechaEscaneo(LocalDate fechaEscaneo) {
        this.fechaEscaneo = fechaEscaneo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
}
