package pe.edu.upc.apifoodsave.entities;

import jakarta.persistence.*;

@Entity
@Table(
        name = "participanteIntercambio",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_usuario", "id_intercambio"})
)
public class ParticipanteIntercambio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idParticipanteIntercambio;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_intercambio", nullable = false)
    private Intercambio intercambio;

    public ParticipanteIntercambio() {}

    public ParticipanteIntercambio(Integer idParticipanteIntercambio, Usuario usuario, Intercambio intercambio) {
        this.idParticipanteIntercambio = idParticipanteIntercambio;
        this.usuario = usuario;
        this.intercambio = intercambio;
    }

    public Integer getIdParticipanteIntercambio() {
        return idParticipanteIntercambio;
    }

    public void setIdParticipanteIntercambio(Integer idParticipanteIntercambio) {
        this.idParticipanteIntercambio = idParticipanteIntercambio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Intercambio getIntercambio() {
        return intercambio;
    }

    public void setIntercambio(Intercambio intercambio) {
        this.intercambio = intercambio;
    }
}
