package pe.edu.upc.apifoodsave.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ComentarioPublicacion")
public class ComentarioPublicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComentario;

    @Column(name = "contenidoComentario", length = 1000, nullable = false)
    private String contenidoComentario;

    @Column(name = "fechaCreacionComentario", nullable = false)
    private LocalDateTime fechaCreacionComentario;

    /* FK: Usuario */
    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    /* FK: Publicacion */
    @ManyToOne
    @JoinColumn(name = "idPublicacion", nullable = false)
    private Publicacion publicacion;

    @PrePersist
    public void prePersist() {
        if (this.fechaCreacionComentario == null) this.fechaCreacionComentario = LocalDateTime.now();
    }

    public ComentarioPublicacion() {
    }

    public ComentarioPublicacion(int idComentario, String contenidoComentario, LocalDateTime fechaCreacionComentario, Usuario usuario, Publicacion publicacion) {
        this.idComentario = idComentario;
        this.contenidoComentario = contenidoComentario;
        this.fechaCreacionComentario = fechaCreacionComentario;
        this.usuario = usuario;
        this.publicacion = publicacion;
    }

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public String getContenidoComentario() {
        return contenidoComentario;
    }

    public void setContenidoComentario(String contenidoComentario) {
        this.contenidoComentario = contenidoComentario;
    }

    public LocalDateTime getFechaCreacionComentario() {
        return fechaCreacionComentario;
    }

    public void setFechaCreacionComentario(LocalDateTime fechaCreacionComentario) {
        this.fechaCreacionComentario = fechaCreacionComentario;
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
