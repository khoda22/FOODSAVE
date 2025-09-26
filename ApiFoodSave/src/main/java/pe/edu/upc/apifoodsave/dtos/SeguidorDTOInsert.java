package pe.edu.upc.apifoodsave.dtos;

import java.time.LocalDate;

public class SeguidorDTOInsert {
    private Integer idSeguidor;   // quien sigue
    private Integer idSeguido;    // a quién sigue
    private LocalDate fechaUnion;

    public Integer getIdSeguidor() {
        return idSeguidor;
    }

    public void setIdSeguidor(Integer idSeguidor) {
        this.idSeguidor = idSeguidor;
    }

    public Integer getIdSeguido() {
        return idSeguido;
    }

    public void setIdSeguido(Integer idSeguido) {
        this.idSeguido = idSeguido;
    }

    public LocalDate getFechaUnion() {
        return fechaUnion;
    }

    public void setFechaUnion(LocalDate fechaUnion) {
        this.fechaUnion = fechaUnion;
    }
}
