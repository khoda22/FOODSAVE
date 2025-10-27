package pe.edu.upc.apifoodsave.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.apifoodsave.entities.Notificacion;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface INotificacionRepository extends JpaRepository<Notificacion, Integer> {
    // Usa el nombre del campo real de Inventario: idInventario
    Optional<Notificacion> findByInventario_IdInventarioAndTipo(Integer inventarioId, boolean tipo);
}
