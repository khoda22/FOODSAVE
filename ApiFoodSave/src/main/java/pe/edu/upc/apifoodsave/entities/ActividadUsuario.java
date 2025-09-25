package pe.edu.upc.apifoodsave.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="ActividadUsuario")
public class ActividadUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idActividad;

    @Column(name = "preferenciasjson", nullable = false, length = 100)
    private String preferenciasjson;

    @Column(name = "tipoAccion", nullable = false, length = 255)
    private String tipoAccion;

    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDateTime Creacion;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public ActividadUsuario() {

    }

    public ActividadUsuario(int idActividad, String preferenciasjson, String tipoAccion, String descripcion, Usuario usuario, LocalDateTime creacion) {
        this.idActividad = idActividad;
        this.preferenciasjson = preferenciasjson;
        this.tipoAccion = tipoAccion;
        this.descripcion = descripcion;
        this.usuario = usuario;
        Creacion = creacion;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getPreferenciasjson() {
        return preferenciasjson;
    }

    public void setPreferenciasjson(String preferenciasjson) {
        this.preferenciasjson = preferenciasjson;
    }

    public String getTipoAccion() {
        return tipoAccion;
    }

    public void setTipoAccion(String tipoAccion) {
        this.tipoAccion = tipoAccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getCreacion() {
        return Creacion;
    }

    public void setCreacion(LocalDateTime creacion) {
        Creacion = creacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
