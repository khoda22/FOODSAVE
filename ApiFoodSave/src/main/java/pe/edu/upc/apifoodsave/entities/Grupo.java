package pe.edu.upc.apifoodsave.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Grupo")
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGrupo;

    @Column(name = "nombreGrupo", length = 100, nullable = false)
    private String nombreGrupo;

    @Column(name = "descripcionGrupo", length = 2000)
    private String descripcionGrupo;

    public Grupo() {
    }

    public Grupo(int idGrupo, String nombreGrupo, String descripcionGrupo) {
        this.idGrupo = idGrupo;
        this.nombreGrupo = nombreGrupo;
        this.descripcionGrupo = descripcionGrupo;
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

    public String getDescripcionGrupo() {
        return descripcionGrupo;
    }

    public void setDescripcionGrupo(String descripcionGrupo) {
        this.descripcionGrupo = descripcionGrupo;
    }
}
