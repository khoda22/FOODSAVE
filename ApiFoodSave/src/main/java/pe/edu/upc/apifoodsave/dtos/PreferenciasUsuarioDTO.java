package pe.edu.upc.apifoodsave.dtos;

import pe.edu.upc.apifoodsave.entities.Usuario;

public class PreferenciasUsuarioDTO {
    private int idPreferencias;
    private String preferenciasjson;
    private Usuario usuario;

    public int getIdPreferencias() {
        return idPreferencias;
    }

    public void setIdPreferencias(int idPreferencias) {
        this.idPreferencias = idPreferencias;
    }

    public String getPreferenciasjson() {
        return preferenciasjson;
    }

    public void setPreferenciasjson(String preferenciasjson) {
        this.preferenciasjson = preferenciasjson;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
