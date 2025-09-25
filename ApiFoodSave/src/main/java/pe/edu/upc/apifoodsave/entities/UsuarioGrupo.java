package pe.edu.upc.apifoodsave.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "UsuariosGrupos")
public class UsuarioGrupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuarioGrupo;

    @Id
    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @Id
    @ManyToOne
    @JoinColumn(name = "idGrupo", nullable = false)
    private Grupo grupo;

    @Column(name = "fechaUnion", nullable = false)
    private LocalDateTime fechaUnion;

    @Column(name = "rolGrupo", length = 30)
    private String rolGrupo;

    public UsuarioGrupo() {
    }

    public UsuarioGrupo(int idUsuarioGrupo, Usuario usuario, Grupo grupo, LocalDateTime fechaUnion, String rolGrupo) {
        this.idUsuarioGrupo = idUsuarioGrupo;
        this.usuario = usuario;
        this.grupo = grupo;
        this.fechaUnion = fechaUnion;
        this.rolGrupo = rolGrupo;
    }

    public int getIdUsuarioGrupo() {
        return idUsuarioGrupo;
    }

    public void setIdUsuarioGrupo(int idUsuarioGrupo) {
        this.idUsuarioGrupo = idUsuarioGrupo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public LocalDateTime getFechaUnion() {
        return fechaUnion;
    }

    public void setFechaUnion(LocalDateTime fechaUnion) {
        this.fechaUnion = fechaUnion;
    }

    public String getRolGrupo() {
        return rolGrupo;
    }

    public void setRolGrupo(String rolGrupo) {
        this.rolGrupo = rolGrupo;
    }
}
