package pe.edu.upc.apifoodsave.dtos;

import java.math.BigDecimal;

public class IngredienteRecetaDTOList {
    private Integer idIngredienteReceta;
    private Integer idProducto;
    private String nombreProducto;
    private BigDecimal cantidadProductos;
    private String unidad;
    private String nota;

    public Integer getIdIngredienteReceta() {
        return idIngredienteReceta;
    }

    public void setIdIngredienteReceta(Integer idIngredienteReceta) {
        this.idIngredienteReceta = idIngredienteReceta;
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
