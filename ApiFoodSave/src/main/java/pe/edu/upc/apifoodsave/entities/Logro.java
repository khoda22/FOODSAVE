package pe.edu.upc.apifoodsave.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Logro")
public class Logro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLogro;

    @Column(name = "nombreLogro", length = 100)
    private String nombreLogro;

    @Column(name = "descripcionLogro", length = 2000)
    private String descripcionLogro;

    @Column(name = "puntosLogro")
    private int puntosLogro;

    public Logro() {
    }

    public Logro(int idLogro, String nombreLogro, String descripcionLogro, int puntosLogro) {
        this.idLogro = idLogro;
        this.nombreLogro = nombreLogro;
        this.descripcionLogro = descripcionLogro;
        this.puntosLogro = puntosLogro;
    }

    public int getIdLogro() {
        return idLogro;
    }

    public void setIdLogro(int idLogro) {
        this.idLogro = idLogro;
    }

    public String getNombreLogro() {
        return nombreLogro;
    }

    public void setNombreLogro(String nombreLogro) {
        this.nombreLogro = nombreLogro;
    }

    public String getDescripcionLogro() {
        return descripcionLogro;
    }

    public void setDescripcionLogro(String descripcionLogro) {
        this.descripcionLogro = descripcionLogro;
    }

    public int getPuntosLogro() {
        return puntosLogro;
    }

    public void setPuntosLogro(int puntosLogro) {
        this.puntosLogro = puntosLogro;
    }
}
