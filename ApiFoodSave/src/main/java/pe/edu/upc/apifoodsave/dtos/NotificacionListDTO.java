package pe.edu.upc.apifoodsave.dtos;

import java.time.LocalDateTime;

public class NotificacionListDTO {
    private Integer idNotificacion;
    private boolean tipo;
    private String mensaje;
    private LocalDateTime fechaProgramada;

    public Integer getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(LocalDateTime fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }
}
