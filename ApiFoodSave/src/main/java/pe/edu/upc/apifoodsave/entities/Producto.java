package pe.edu.upc.apifoodsave.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "categoria", nullable = false, length = 100)
    private String categoria;

    @Column(name = "vida_util_dias", nullable = false)
    private int vidaUtilDias;

    @Column(name = "estado", nullable = false, length = 40)
    private String estado;

    @Column(name = "codigo_barra", nullable = false)
    private int codigoBarra;

    @Column(name = "peso_unitario", nullable = false)
    private double pesoUnitario;

    public Producto() {}

    public Producto(int idProducto, String nombre, String categoria, int vidaUtilDias, String estado, int codigoBarra, double pesoUnitario) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.vidaUtilDias = vidaUtilDias;
        this.estado = estado;
        this.codigoBarra = codigoBarra;
        this.pesoUnitario = pesoUnitario;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getVidaUtilDias() {
        return vidaUtilDias;
    }

    public void setVidaUtilDias(int vidaUtilDias) {
        this.vidaUtilDias = vidaUtilDias;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(int codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public double getPesoUnitario() {
        return pesoUnitario;
    }

    public void setPesoUnitario(double pesoUnitario) {
        this.pesoUnitario = pesoUnitario;
    }
}
