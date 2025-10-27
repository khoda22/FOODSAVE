package pe.edu.upc.apifoodsave.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ClasificacionSemanal")
public class ClasificacionSemanal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idClasificacion;

    @Column(name = "periodoClasificacion", length = 20, nullable = false) // ej: 2025-W37
    private String periodoClasificacion;

    @Column(name = "puntajeClasificacion", nullable = false)
    private int puntajeClasificacion;

    @Column(name = "kgSalvadosClasificacion", nullable = false)
    private double kgSalvadosClasificacion;

    /* FK: Usuario */
    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    // ⚙️ Regla automática: 10 puntos por kilo salvado (con redondeo floor)
    @PrePersist
    @PreUpdate
    private void aplicarReglaPuntos() {
        this.puntajeClasificacion = (int) Math.floor(this.kgSalvadosClasificacion * 10);
    }

    public ClasificacionSemanal() {
    }

    public ClasificacionSemanal(int idClasificacion, String periodoClasificacion, int puntajeClasificacion, double kgSalvadosClasificacion, Usuario usuario) {
        this.idClasificacion = idClasificacion;
        this.periodoClasificacion = periodoClasificacion;
        this.puntajeClasificacion = puntajeClasificacion;
        this.kgSalvadosClasificacion = kgSalvadosClasificacion;
        this.usuario = usuario;
    }

    public int getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(int idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    public String getPeriodoClasificacion() {
        return periodoClasificacion;
    }

    public void setPeriodoClasificacion(String periodoClasificacion) {
        this.periodoClasificacion = periodoClasificacion;
    }

    public int getPuntajeClasificacion() {
        return puntajeClasificacion;
    }

    public void setPuntajeClasificacion(int puntajeClasificacion) {
        this.puntajeClasificacion = puntajeClasificacion;
    }

    public double getKgSalvadosClasificacion() {
        return kgSalvadosClasificacion;
    }

    public void setKgSalvadosClasificacion(double kgSalvadosClasificacion) {
        this.kgSalvadosClasificacion = kgSalvadosClasificacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
