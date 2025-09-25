package pe.edu.upc.apifoodsave.dtos;

public class UsuarioGrupoDTO {
    private int idUsuarioGrupo;
    private int idUsuario;
    private int idGrupo;
    private String nombreGrupo;

    public int getIdUsuarioGrupo() {
        return idUsuarioGrupo;
    }

    public void setIdUsuarioGrupo(int idUsuarioGrupo) {
        this.idUsuarioGrupo = idUsuarioGrupo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }
}
