package pe.edu.upc.apifoodsave.entities;

import jakarta.persistence.*;

@Entity
@Table(
        name = "favoritoReceta",
        //protegen de duplicados
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_usuario", "id_receta"})
)
public class FavoritoReceta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFavorito;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_receta", nullable = false)
    private Receta receta;

    public FavoritoReceta() {}

    public FavoritoReceta(Integer idFavorito, Usuario usuario, Receta receta) {
        this.idFavorito = idFavorito;
        this.usuario = usuario;
        this.receta = receta;
    }

    public Integer getIdFavorito() {
        return idFavorito;
    }

    public void setIdFavorito(Integer idFavorito) {
        this.idFavorito = idFavorito;
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
