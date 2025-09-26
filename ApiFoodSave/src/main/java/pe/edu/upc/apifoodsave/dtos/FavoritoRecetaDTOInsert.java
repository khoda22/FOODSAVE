package pe.edu.upc.apifoodsave.dtos;

public class FavoritoRecetaDTOInsert {
    private Integer idUsuario;
    private Integer idReceta;

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
}
