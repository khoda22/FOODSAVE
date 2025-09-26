package pe.edu.upc.apifoodsave.dtos;

public class ReporteContenidoDTOUpdate {
    private Integer idReporte;
    private String estado;

    public Integer getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
