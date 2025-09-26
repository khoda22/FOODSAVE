package pe.edu.upc.apifoodsave.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "ingredienteReceta")
public class IngredienteReceta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIngredienteReceta;

    @Column(name = "cantidad_productos", nullable = false, precision = 10, scale = 2)
    private BigDecimal cantidadProductos; // ej. 1.50

    @Column(name = "unidad", length = 20, nullable = false)   // "kg", "g", "ml", "unidad"
    private String unidad;

    @Column(name = "nota", length = 150)                      // opcional: "picado", "cocido", etc.
    private String nota;

    @ManyToOne
    @JoinColumn(name = "id_receta", nullable = false)
    private Receta receta;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    public IngredienteReceta() {}

    public IngredienteReceta(Integer idIngredienteReceta, BigDecimal cantidadProductos, String unidad, String nota, Receta receta, Producto producto) {
        this.idIngredienteReceta = idIngredienteReceta;
        this.cantidadProductos = cantidadProductos;
        this.unidad = unidad;
        this.nota = nota;
        this.receta = receta;
        this.producto = producto;
    }

    public Integer getIdIngredienteReceta() {
        return idIngredienteReceta;
    }

    public void setIdIngredienteReceta(Integer idIngredienteReceta) {
        this.idIngredienteReceta = idIngredienteReceta;
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

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
