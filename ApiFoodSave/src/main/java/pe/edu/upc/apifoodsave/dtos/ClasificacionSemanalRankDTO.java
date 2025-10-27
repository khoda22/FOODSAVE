package pe.edu.upc.apifoodsave.dtos;

public class ClasificacionSemanalRankDTO {
    private Integer posicion;
    private Integer idUsuario;
    private String username;
    private Double kgTotales;
    private Integer puntosTotales;
    private String periodo; // para el endpoint "actual"

    public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getKgTotales() {
        return kgTotales;
    }

    public void setKgTotales(Double kgTotales) {
        this.kgTotales = kgTotales;
    }

    public Integer getPuntosTotales() {
        return puntosTotales;
    }

    public void setPuntosTotales(Integer puntosTotales) {
        this.puntosTotales = puntosTotales;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
