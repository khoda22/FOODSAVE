package pe.edu.upc.apifoodsave.dtos;

import java.time.LocalDateTime;

public class UsuarioLogroDTO {
    private int idUsuarioLogro;
    private int idUsuario;
    private int idLogro;
    private LocalDateTime fechaLogro;

    public int getIdUsuarioLogro() {
        return idUsuarioLogro;
    }

    public void setIdUsuarioLogro(int idUsuarioLogro) {
        this.idUsuarioLogro = idUsuarioLogro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdLogro() {
        return idLogro;
    }

    public void setIdLogro(int idLogro) {
        this.idLogro = idLogro;
    }

    public LocalDateTime getFechaLogro() {
        return fechaLogro;
    }

    public void setFechaLogro(LocalDateTime fechaLogro) {
        this.fechaLogro = fechaLogro;
    }
}
