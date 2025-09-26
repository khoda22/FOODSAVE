package pe.edu.upc.apifoodsave.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "seguidor",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_seguidor", "id_seguido"})
)
public class Seguidor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSeguidorRel; // id de la relación (no confundir con usuario)

    @ManyToOne
    @JoinColumn(name = "id_seguidor", nullable = false) // quien sigue
    private Usuario seguidor;

    @ManyToOne
    @JoinColumn(name = "id_seguido", nullable = false)  // a quien sigue
    private Usuario seguido;

    @Column(name = "fecha_union", nullable = false)
    private LocalDate fechaUnion;

    @PrePersist
    public void prePersist() {
        if (this.fechaUnion == null) this.fechaUnion = LocalDate.now();
    }

    public Seguidor() {}

    public Seguidor(Integer idSeguidorRel, Usuario seguidor, Usuario seguido, LocalDate fechaUnion) {
        this.idSeguidorRel = idSeguidorRel;
        this.seguidor = seguidor;
        this.seguido = seguido;
        this.fechaUnion = fechaUnion;
    }

    public Integer getIdSeguidorRel() {
        return idSeguidorRel;
    }

    public void setIdSeguidorRel(Integer idSeguidorRel) {
        this.idSeguidorRel = idSeguidorRel;
    }

    public Usuario getSeguidor() {
        return seguidor;
    }

    public void setSeguidor(Usuario seguidor) {
        this.seguidor = seguidor;
    }

    public Usuario getSeguido() {
        return seguido;
    }

    public void setSeguido(Usuario seguido) {
        this.seguido = seguido;
    }

    public LocalDate getFechaUnion() {
        return fechaUnion;
    }

    public void setFechaUnion(LocalDate fechaUnion) {
        this.fechaUnion = fechaUnion;
    }
}
