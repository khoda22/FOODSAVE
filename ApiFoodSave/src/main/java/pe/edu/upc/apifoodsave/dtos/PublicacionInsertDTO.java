package pe.edu.upc.apifoodsave.dtos;

public class PublicacionInsertDTO {
    private String contenidoPublicacion;
    private String fotoUrlPublicacion;
    private int idUsuario;
    private int idReceta;

    public String getContenidoPublicacion() {
        return contenidoPublicacion;
    }

    public void setContenidoPublicacion(String contenidoPublicacion) {
        this.contenidoPublicacion = contenidoPublicacion;
    }

    public String getFotoUrlPublicacion() {
        return fotoUrlPublicacion;
    }

    public void setFotoUrlPublicacion(String fotoUrlPublicacion) {
        this.fotoUrlPublicacion = fotoUrlPublicacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }
}
