package pe.edu.upc.apifoodsave.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventario")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInventario;

    @Column(name = "cantidadInventario", nullable = false)
    private int cantidadInventario;

    @Column(name = "diasduracionInventario", nullable = false)
    private int diasduracionInventario;

    @Column(name = "estadoInventario",length = 50, nullable = false)
    private String estadoInventario;

    @Column(name = "fechavencimientoInventario",nullable = false)
    private LocalDate fechavencimientoInventario;

    @Column(name = "fechacreacionInventario",nullable = false)
    private LocalDate fechacreacionInventario;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;

    @PrePersist
    public void prePersist() {
        if (this.fechacreacionInventario == null) this.fechacreacionInventario = LocalDate.now();
    }

    public Inventario() {}

    public Inventario(int idInventario, int cantidadInventario, String estadoInventario, LocalDate fechavencimientoInventario, LocalDate fechacreacionInventario, Usuario usuario, Producto producto) {
        this.idInventario = idInventario;
        this.cantidadInventario = cantidadInventario;
        this.estadoInventario = estadoInventario;
        this.fechavencimientoInventario = fechavencimientoInventario;
        this.fechacreacionInventario = fechacreacionInventario;
        this.usuario = usuario;
        this.producto = producto;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public int getCantidadInventario() {
        return cantidadInventario;
    }

    public void setCantidadInventario(int cantidadInventario) {
        this.cantidadInventario = cantidadInventario;
    }

    public String getEstadoInventario() {
        return estadoInventario;
    }

    public void setEstadoInventario(String estadoInventario) {
        this.estadoInventario = estadoInventario;
    }

    public LocalDate getFechavencimientoInventario() {
        return fechavencimientoInventario;
    }

    public void setFechavencimientoInventario(LocalDate fechavencimientoInventario) {
        this.fechavencimientoInventario = fechavencimientoInventario;
    }

    public LocalDate getFechacreacionInventario() {
        return fechacreacionInventario;
    }

    public void setFechacreacionInventario(LocalDate fechacreacionInventario) {
        this.fechacreacionInventario = fechacreacionInventario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
