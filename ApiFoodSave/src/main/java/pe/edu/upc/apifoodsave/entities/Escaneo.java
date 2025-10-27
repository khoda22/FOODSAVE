package pe.edu.upc.apifoodsave.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "escaneo")
public class Escaneo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEscaneo;

    @Column(name = "fecha_escaneo", nullable = false)
    private LocalDate fechaEscaneo;

    @Column(name = "origen", length = 40, nullable = false) // "QR", "BARRAS", "MANUAL"
    private String origen;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @PrePersist
    public void prePersist() {
        if (this.fechaEscaneo == null) {
            this.fechaEscaneo = LocalDate.now();
        }
    }

    public Escaneo() {
    }

    public Escaneo(Integer idEscaneo, LocalDate fechaEscaneo, String origen, Usuario usuario, Producto producto) {
        this.idEscaneo = idEscaneo;
        this.fechaEscaneo = fechaEscaneo;
        this.origen = origen;
        this.usuario = usuario;
        this.producto = producto;
    }

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
