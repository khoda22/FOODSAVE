package pe.edu.upc.apifoodsave.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "PreferenciasUsuario")
public class PreferenciasUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPreferencias;

    @Column(name = "preferenciasjson", nullable = false, length = 100)
    private String preferenciasjson;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public PreferenciasUsuario() {

    }

    public PreferenciasUsuario(int idPreferencias, String preferenciasjson, Usuario usuario) {
        this.idPreferencias = idPreferencias;
        this.preferenciasjson = preferenciasjson;
        this.usuario = usuario;
    }

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
