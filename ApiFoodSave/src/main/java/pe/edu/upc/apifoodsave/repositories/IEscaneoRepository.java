package pe.edu.upc.apifoodsave.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.apifoodsave.entities.Escaneo;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IEscaneoRepository extends JpaRepository<Escaneo, Integer> {
    @Query("SELECT e FROM Escaneo e WHERE e.fechaEscaneo >= :fechaInicio ORDER BY e.fechaEscaneo DESC")
    List<Escaneo> findEscaneosUltimosDias(LocalDate fechaInicio);
}
