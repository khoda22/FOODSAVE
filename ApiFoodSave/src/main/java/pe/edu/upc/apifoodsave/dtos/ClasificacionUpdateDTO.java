package pe.edu.upc.apifoodsave.dtos;

public class ClasificacionUpdateDTO {
    private int idClasificacion;
    private String periodoClasificacion;
    private double kgSalvadosClasificacion;
    private int idUsuario;

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

    public double getKgSalvadosClasificacion() {
        return kgSalvadosClasificacion;
    }

    public void setKgSalvadosClasificacion(double kgSalvadosClasificacion) {
        this.kgSalvadosClasificacion = kgSalvadosClasificacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
