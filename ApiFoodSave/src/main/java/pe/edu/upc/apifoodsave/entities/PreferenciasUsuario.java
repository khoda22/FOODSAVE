package pe.edu.upc.apifoodsave.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "PreferenciasUsuario")
public class PreferenciasUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPreferencias;

    @Column(name = "notificacionesActivas")
    private Boolean notificacionesActivas;
    @Column(name = "temaVisual")
    private String temaVisual;
    @Column(name = "idioma")
    private String idioma;
    @Column(name = "canal")
    private String canal;
    @Column(name = "horaInicio")
    private LocalTime horaInicio;
    @Column(name = "horaFin")
    private LocalTime horaFin;
    @Column(name = "diasAnticipacion")
    private Integer diasAnticipacion;
    @Column(name = "modoVacaciones")
    private Boolean modoVacaciones;
    @Column(name = "fechaInicioVacaciones")
    private LocalDate fechaInicioVacaciones;
    @Column(name = "fechaFinVacaciones")
    private LocalDate fechaFinVacaciones;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public PreferenciasUsuario() {
    }

    public PreferenciasUsuario(int idPreferencias, Boolean notificacionesActivas, String temaVisual, String idioma, String canal, LocalTime horaInicio, LocalTime horaFin, Integer diasAnticipacion, Boolean modoVacaciones, LocalDate fechaInicioVacaciones, LocalDate fechaFinVacaciones, Usuario usuario) {
        this.idPreferencias = idPreferencias;
        this.notificacionesActivas = notificacionesActivas;
        this.temaVisual = temaVisual;
        this.idioma = idioma;
        this.canal = canal;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.diasAnticipacion = diasAnticipacion;
        this.modoVacaciones = modoVacaciones;
        this.fechaInicioVacaciones = fechaInicioVacaciones;
        this.fechaFinVacaciones = fechaFinVacaciones;
        this.usuario = usuario;
    }

    public int getIdPreferencias() {
        return idPreferencias;
    }

    public void setIdPreferencias(int idPreferencias) {
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
