package pe.edu.upc.apifoodsave.entities;

import jakarta.persistence.*;

@Entity
@Table(
        name = "MeGustaPublicacion",
        uniqueConstraints = @UniqueConstraint(columnNames = {"idUsuario", "idPublicacion"})
)
public class MeGustaPublicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMeGusta;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idPublicacion", nullable = false)
    private Publicacion publicacion;

    public MeGustaPublicacion() {
    }

    public MeGustaPublicacion(int idMeGusta, Usuario usuario, Publicacion publicacion) {
        this.idMeGusta = idMeGusta;
        this.usuario = usuario;
        this.publicacion = publicacion;
    }

    public int getIdMeGusta() {
        return idMeGusta;
    }

    public void setIdMeGusta(int idMeGusta) {
        this.idMeGusta = idMeGusta;
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
