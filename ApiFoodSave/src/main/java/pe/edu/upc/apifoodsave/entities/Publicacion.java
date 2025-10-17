package pe.edu.upc.apifoodsave.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Publicacion")
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPublicacion;

    @Column(name = "contenidoPublicacion", nullable = false, length = 4000)
    private String contenidoPublicacion;

    @Column(name = "fotoUrlPublicacion", length = 1000)
    private String fotoUrlPublicacion;

    @Column(name = "fechaCreacionPublicacion", nullable = false)
    private LocalDateTime fechaCreacionPublicacion;

    /* FK: Usuario */
    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    /* FK: Receta */
    @ManyToOne
    @JoinColumn(name = "idReceta", nullable = false)
    private Receta receta;


    @PrePersist
    public void prePersist() {
        if (this.fechaCreacionPublicacion == null) this.fechaCreacionPublicacion = LocalDateTime.now();
    }

    public Publicacion() {
    }

    public Publicacion(int idPublicacion, String contenidoPublicacion, String fotoUrlPublicacion, LocalDateTime fechaCreacionPublicacion, Usuario usuario/*, Receta receta*/) {
        this.idPublicacion = idPublicacion;
        this.contenidoPublicacion = contenidoPublicacion;
        this.fotoUrlPublicacion = fotoUrlPublicacion;
        this.fechaCreacionPublicacion = fechaCreacionPublicacion;
        this.usuario = usuario;
        this.receta = receta;
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getContenidoPublicacion() {
        return contenidoPublicacion;
    }

    public void setContenidoPublicacion(String contenidoPublicacion) {
        this.contenidoPublicacion = contenidoPublicacion;
    }

    public String getFotoUrlPublicacion() {
        return fotoUrlPublicacion;
    }

    public void setFotoUrlPublicacion(String fotoUrlPublicacion) {
        this.fotoUrlPublicacion = fotoUrlPublicacion;
    }

    public LocalDateTime getFechaCreacionPublicacion() {
        return fechaCreacionPublicacion;
    }

    public void setFechaCreacionPublicacion(LocalDateTime fechaCreacionPublicacion) {
        this.fechaCreacionPublicacion = fechaCreacionPublicacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }
}
