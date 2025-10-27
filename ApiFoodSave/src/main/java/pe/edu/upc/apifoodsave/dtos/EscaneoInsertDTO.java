package pe.edu.upc.apifoodsave.dtos;

import java.time.LocalDate;

public class EscaneoInsertDTO {
    private LocalDate fechaEscaneo;   // opcional: si no viene, se usa hoy
    private String origen;            // "QR", "BARRAS", "MANUAL"
    private Integer idUsuario;        // requerido
    private Integer idProducto;       // opcional si mandas codigoBarra
    private String codigoBarra;

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

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }
}
