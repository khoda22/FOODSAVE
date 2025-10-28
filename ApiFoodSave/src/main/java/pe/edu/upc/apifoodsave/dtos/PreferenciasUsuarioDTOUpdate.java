package pe.edu.upc.apifoodsave.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public class PreferenciasUsuarioDTOUpdate {
    private Integer idPreferencias;
    private Boolean notificacionesActivas;
    private String temaVisual;
    private String idioma;
    private String canal;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Integer diasAnticipacion;
    private Boolean modoVacaciones;
    private LocalDate fechaInicioVacaciones;
    private LocalDate fechaFinVacaciones;
    private Integer idUsuario;

    public Integer getIdPreferencias() {
        return idPreferencias;
    }

    public void setIdPreferencias(Integer idPreferencias) {
        this.idPreferencias = idPreferencias;
    }

    public Boolean getNotificacionesActivas() {
        return notificacionesActivas;
    }

    public void setNotificacionesActivas(Boolean notificacionesActivas) {
        this.notificacionesActivas = notificacionesActivas;
    }

    public String getTemaVisual() {
        return temaVisual;
    }

    public void setTemaVisual(String temaVisual) {
        this.temaVisual = temaVisual;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public Integer getDiasAnticipacion() {
        return diasAnticipacion;
    }

    public void setDiasAnticipacion(Integer diasAnticipacion) {
        this.diasAnticipacion = diasAnticipacion;
    }

    public Boolean getModoVacaciones() {
        return modoVacaciones;
    }

    public void setModoVacaciones(Boolean modoVacaciones) {
        this.modoVacaciones = modoVacaciones;
    }

    public LocalDate getFechaInicioVacaciones() {
        return fechaInicioVacaciones;
    }

    public void setFechaInicioVacaciones(LocalDate fechaInicioVacaciones) {
        this.fechaInicioVacaciones = fechaInicioVacaciones;
    }

    public LocalDate getFechaFinVacaciones() {
        return fechaFinVacaciones;
    }

    public void setFechaFinVacaciones(LocalDate fechaFinVacaciones) {
        this.fechaFinVacaciones = fechaFinVacaciones;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
