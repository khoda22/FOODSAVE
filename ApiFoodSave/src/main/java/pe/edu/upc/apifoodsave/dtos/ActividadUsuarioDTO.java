package pe.edu.upc.apifoodsave.dtos;

import pe.edu.upc.apifoodsave.entities.Usuario;

import java.time.LocalDateTime;
public class ActividadUsuarioDTO {
    private int idActividad;
    private String preferenciasjson;
    private String tipoAccion;
    private String descripcion;
    private LocalDateTime Creacion;
    private Usuario usuario;

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
