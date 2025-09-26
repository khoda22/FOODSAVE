package pe.edu.upc.apifoodsave.dtos;

import java.time.LocalDate;

public class SeguidorDTOList {
    private Integer idSeguidorRel; // id de la relación
    private Integer idSeguidor;
    private Integer idSeguido;
    private LocalDate fechaUnion;

    public Integer getIdSeguidorRel() {
        return idSeguidorRel;
    }

    public void setIdSeguidorRel(Integer idSeguidorRel) {
        this.idSeguidorRel = idSeguidorRel;
    }

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
