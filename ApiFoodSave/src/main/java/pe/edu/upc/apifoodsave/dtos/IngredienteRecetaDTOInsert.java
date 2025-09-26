package pe.edu.upc.apifoodsave.dtos;

import java.math.BigDecimal;

public class IngredienteRecetaDTOInsert {
    private Integer idReceta;
    private Integer idProducto;
    private BigDecimal cantidadProductos;
    private String unidad;
    private String nota;

    public Integer getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Integer idReceta) {
        this.idReceta = idReceta;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public BigDecimal getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(BigDecimal cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
