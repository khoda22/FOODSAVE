package pe.edu.upc.apifoodsave.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "receta")
public class Receta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReceta;

    @Column(name = "titulo", length = 150, nullable = false)
    private String titulo;

    @Column(name = "instrucciones", columnDefinition = "text", nullable = false)
    private String instrucciones;

    @Column(name = "dificultad", nullable = false) // 1..5 por ejemplo
    private Integer dificultad;

    @Column(name = "tiempo_preparacion", nullable = false) // minutos
    private Integer tiempoPreparacion;

    @Column(name = "creado_por", length = 80, nullable = false)
    private String creadoPor;

    public Receta() {
    }

    public Receta(Integer idReceta, String titulo, String instrucciones, Integer dificultad, Integer tiempoPreparacion, String creadoPor) {
        this.idReceta = idReceta;
        this.titulo = titulo;
        this.instrucciones = instrucciones;
        this.dificultad = dificultad;
        this.tiempoPreparacion = tiempoPreparacion;
        this.creadoPor = creadoPor;
    }

    public Integer getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Integer idReceta) {
        this.idReceta = idReceta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public Integer getDificultad() {
        return dificultad;
    }

    public void setDificultad(Integer dificultad) {
        this.dificultad = dificultad;
    }

    public Integer getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(Integer tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }
}
