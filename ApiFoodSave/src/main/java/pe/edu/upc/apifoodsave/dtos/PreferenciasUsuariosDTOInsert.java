package pe.edu.upc.apifoodsave.dtos;

public class PreferenciasUsuariosDTOInsert {
    private String preferenciasjson;
    private Integer idUsuario;

    public String getPreferenciasjson() {
        return preferenciasjson;
    }

    public void setPreferenciasjson(String preferenciasjson) {
        this.preferenciasjson = preferenciasjson;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
