package pe.edu.upc.apifoodsave.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "calificacionReceta")
public class CalificacionReceta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCalificacionReceta;

    @Column(name = "calificacion", nullable = false) // 1..5
    private Integer calificacion;

    @ManyToOne
    @JoinColumn(name = "id_receta", nullable = false)
    private Receta receta;

    public CalificacionReceta() {
    }

    public CalificacionReceta(Integer idCalificacionReceta, Integer calificacion, Receta receta) {
        this.idCalificacionReceta = idCalificacionReceta;
        this.calificacion = calificacion;
        this.receta = receta;
    }

    public Integer getIdCalificacionReceta() {
        return idCalificacionReceta;
    }

    public void setIdCalificacionReceta(Integer idCalificacionReceta) {
        this.idCalificacionReceta = idCalificacionReceta;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }
}
