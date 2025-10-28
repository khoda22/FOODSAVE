package pe.edu.upc.apifoodsave.dtos;

import pe.edu.upc.apifoodsave.entities.Usuario;

import java.time.LocalDateTime;
public class ActividadUsuarioDTOUpdate {
    private int idActividad;
    private String preferenciasjson;
    private String tipoAccion;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private Integer idUsuario;

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

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
