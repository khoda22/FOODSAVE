package pe.edu.upc.apifoodsave.dtos;

public class CalificacionRecetaDTOInsert {
    private Integer calificacion;   // 1..5
    private Integer idReceta;

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public Integer getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Integer idReceta) {
        this.idReceta = idReceta;
    }
}
