package pe.edu.upc.apifoodsave.dtos;

public class PublicacionUpdateDTO {
    private int idPublicacion;
    private String contenidoPublicacion;
    private String fotoUrlPublicacion;

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

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
}
