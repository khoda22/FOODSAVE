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

    @Column(name = "password", nullable = false, length = 255)
    private String password;


    @Column(name = "foto", nullable = false, length = 500)
    private String foto;

    @Column(name = "ubicacion", nullable = false, length = 100)
    private String ubicacion;

    @Column(name = "login", nullable = false)
    private LocalDateTime login;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDateTime Creacion;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "idRol")
    private Rol rol;

    @PrePersist
    public void prePersist() {
        if (this.Creacion == null) this.Creacion = LocalDateTime.now();
    }

    public Usuario() {}

    public Usuario(int idUsuario, Rol rol, String username, String email, String password, String foto, Boolean estado, LocalDateTime creacion, LocalDateTime login, String ubicacion) {
        this.idUsuario = idUsuario;
        this.rol = rol;
        this.username = username;
        this.email = email;
        this.password = password;
        this.foto = foto;
        this.estado = estado;
        Creacion = creacion;
        this.login = login;
        this.ubicacion = ubicacion;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
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

    public LocalDateTime getCreacion() {
        return Creacion;
    }

    public void setCreacion(LocalDateTime creacion) {
        Creacion = creacion;
    }
}
