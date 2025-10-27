package pe.edu.upc.apifoodsave.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(
        name = "calificacion_receta",
        uniqueConstraints = @UniqueConstraint(name = "uk_usuario_receta", columnNames = {"id_usuario","id_receta"})
)
public class CalificacionReceta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCalificacionReceta;

    @Column(name = "calificacion", nullable = false)
    @jakarta.validation.constraints.Min(1)
    @jakarta.validation.constraints.Max(5)
    private Integer calificacion;

    @ManyToOne
    @JoinColumn(name = "id_receta", nullable = false)
    private Receta receta;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    public CalificacionReceta() {
    }

    public CalificacionReceta(Integer idCalificacionReceta, Integer calificacion, Receta receta, Usuario usuario) {
        this.idCalificacionReceta = idCalificacionReceta;
        this.calificacion = calificacion;
        this.receta = receta;
        this.usuario = usuario;
    }

    public Integer getIdCalificacionReceta() {
        return idCalificacionReceta;
    }

    public void setIdCalificacionReceta(Integer idCalificacionReceta) {
        this.idCalificacionReceta = idCalificacionReceta;
    }

    public @Min(1) @Max(5) Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(@Min(1) @Max(5) Integer calificacion) {
        this.calificacion = calificacion;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
