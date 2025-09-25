package pe.edu.upc.apifoodsave.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "UsuariosLogros")
public class UsuarioLogro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuarioLogro;


    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;


    @ManyToOne
    @JoinColumn(name = "idLogro")
    private Logro logro;

    @Column(name = "fechaLogro")
    private LocalDateTime fechaLogro;

    public UsuarioLogro() {
    }

    public UsuarioLogro(int idUsuarioLogro, Usuario usuario, Logro logro, LocalDateTime fechaLogro) {
        this.idUsuarioLogro = idUsuarioLogro;
        this.usuario = usuario;
        this.logro = logro;
        this.fechaLogro = fechaLogro;
    }

    public int getIdUsuarioLogro() {
        return idUsuarioLogro;
    }

    public void setIdUsuarioLogro(int idUsuarioLogro) {
        this.idUsuarioLogro = idUsuarioLogro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Logro getLogro() {
        return logro;
    }

    public void setLogro(Logro logro) {
        this.logro = logro;
    }

    public LocalDateTime getFechaLogro() {
        return fechaLogro;
    }

    public void setFechaLogro(LocalDateTime fechaLogro) {
        this.fechaLogro = fechaLogro;
    }
}
