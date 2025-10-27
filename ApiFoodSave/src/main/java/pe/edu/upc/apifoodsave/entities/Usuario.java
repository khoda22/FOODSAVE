package pe.edu.upc.apifoodsave.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 250)
    private String password;

    @Column(name = "foto", nullable = false, length = 500)
    private String foto;

    @Column(name = "ubicacion", nullable = false, length = 100)
    private String ubicacion;

    @Column(name = "login", nullable = false)
    private LocalDateTime login;

    @Column(name = "fechaCreacion", nullable = false, updatable = false)
    private LocalDateTime creacion;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "idRol")
    private Rol rol;

    @PrePersist
    public void prePersist() {
        if (this.creacion == null) this.creacion = LocalDateTime.now();
    }

    public Usuario() {}

    public Usuario(int idUsuario, String username, String email, String password, String foto, String ubicacion, LocalDateTime login, LocalDateTime creacion, Boolean estado, Rol rol) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.email = email;
        this.password = password;
        this.foto = foto;
        this.ubicacion = ubicacion;
        this.login = login;
        this.creacion = creacion;
        this.estado = estado;
        this.rol = rol;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getCreacion() {
        return creacion;
    }

    public void setCreacion(LocalDateTime creacion) {
        this.creacion = creacion;
    }
}
