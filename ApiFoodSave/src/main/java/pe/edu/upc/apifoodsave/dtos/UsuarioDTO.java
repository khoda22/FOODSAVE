package pe.edu.upc.apifoodsave.dtos;

import java.time.LocalDateTime;

public class UsuarioDTO {
    private int idUsuario;
    private String email;
    private String password;
    private String foto;
    private String ubicacion;
    private LocalDateTime login;
    private LocalDateTime Creacion;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public LocalDateTime getLogin() {
        return login;
    }

    public void setLogin(LocalDateTime login) {
        this.login = login;
    }

    public LocalDateTime getCreacion() {
        return Creacion;
    }

    public void setCreacion(LocalDateTime creacion) {
        Creacion = creacion;
    }
}
