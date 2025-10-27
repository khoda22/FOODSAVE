package pe.edu.upc.apifoodsave.dtos;

public class CalificacionRecetaDTOList {
    private Integer idCalificacionReceta;
    private Integer calificacion;
    private Integer idUsuario;
    private Integer idReceta;
    private String tituloReceta;

    public Integer getIdCalificacionReceta() {
        return idCalificacionReceta;
    }

    public void setIdCalificacionReceta(Integer idCalificacionReceta) {
        this.idCalificacionReceta = idCalificacionReceta;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Integer idReceta) {
        this.idReceta = idReceta;
    }

    public String getTituloReceta() {
        return tituloReceta;
    }

    public void setTituloReceta(String tituloReceta) {
        this.tituloReceta = tituloReceta;
    }
}
