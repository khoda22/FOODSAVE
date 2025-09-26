package pe.edu.upc.apifoodsave.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "intercambio")
public class Intercambio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIntercambio;

    @Column(name = "titulo", length = 120, nullable = false)
    private String titulo;

    @Column(name = "descripcion", columnDefinition = "text", nullable = false)
    private String descripcion;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "ubicacion", length = 120, nullable = false)
    private String ubicacion;

    public Intercambio() {}

    public Intercambio(Integer idIntercambio, String titulo, String descripcion, LocalDate fecha, String ubicacion) {
        this.idIntercambio = idIntercambio;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
    }

    public Integer getIdIntercambio() {
        return idIntercambio;
    }

    public void setIdIntercambio(Integer idIntercambio) {
        this.idIntercambio = idIntercambio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
