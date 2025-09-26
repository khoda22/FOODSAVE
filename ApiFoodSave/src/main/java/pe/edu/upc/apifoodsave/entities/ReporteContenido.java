package pe.edu.upc.apifoodsave.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reporteContenido")
public class ReporteContenido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReporte;

    @Column(name = "motivo", length = 150, nullable = false)
    private String motivo;

    @Column(name = "fecha_reporte", nullable = false)
    private LocalDate fechaReporte;

    @Column(name = "estado", length = 30, nullable = false) // "PENDIENTE", "REVISADO", "DESCARTADO"
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_publicacion", nullable = false)
    private Publicacion publicacion;

    @PrePersist
    public void prePersist() {
        if (this.fechaReporte == null) this.fechaReporte = LocalDate.now();
    }

    public ReporteContenido() {
    }

    public ReporteContenido(Integer idReporte, String motivo, LocalDate fechaReporte, String estado, Usuario usuario, Publicacion publicacion) {
        this.idReporte = idReporte;
        this.motivo = motivo;
        this.fechaReporte = fechaReporte;
        this.estado = estado;
        this.usuario = usuario;
        this.publicacion = publicacion;
    }

    public Integer getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDate getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(LocalDate fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }
}
