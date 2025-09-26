package pe.edu.upc.apifoodsave.dtos;

import java.time.LocalDate;

public class ReporteContenidoDTOList {
    private Integer idReporte;
    private String motivo;
    private LocalDate fechaReporte;
    private String estado;

    public Integer getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDate getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(LocalDate fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
