package pe.edu.upc.apifoodsave.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ComentarioInsertDTO {
    @NotBlank
    @Size(max = 1000)
    private String contenido;

    @Min(1)
    private int idUsuario;

    @Min(1)
    private int idPublicacion;

    public @NotBlank @Size(max = 1000) String getContenido() {
        return contenido;
    }

    public void setContenido(@NotBlank @Size(max = 1000) String contenido) {
        this.contenido = contenido;
    }

    @Min(1)
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(@Min(1) int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Min(1)
    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(@Min(1) int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }
}
