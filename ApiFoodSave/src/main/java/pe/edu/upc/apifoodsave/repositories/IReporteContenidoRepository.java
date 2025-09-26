package pe.edu.upc.apifoodsave.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.apifoodsave.entities.ReporteContenido;

@Repository
public interface IReporteContenidoRepository extends JpaRepository<ReporteContenido, Integer> {
}
