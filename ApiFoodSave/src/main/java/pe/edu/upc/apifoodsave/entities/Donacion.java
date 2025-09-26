package pe.edu.upc.apifoodsave.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "donacion")
public class Donacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDonacion;

    @Column(name = "ubicacion", length = 120, nullable = false)
    private String ubicacion;

    @Column(name = "fecha_programada", nullable = false)
    private LocalDate fechaProgramada;

    @ManyToOne
    @JoinColumn(name = "id_inventario", nullable = false)
    private Inventario inventario;

    public Donacion() {
    }

    public Donacion(Integer idDonacion, String ubicacion, LocalDate fechaProgramada, Inventario inventario) {
        this.idDonacion = idDonacion;
        this.ubicacion = ubicacion;
        this.fechaProgramada = fechaProgramada;
        this.inventario = inventario;
    }

    public Integer getIdDonacion() {
        return idDonacion;
    }

    public void setIdDonacion(Integer idDonacion) {
        this.idDonacion = idDonacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public LocalDate getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(LocalDate fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }
}
