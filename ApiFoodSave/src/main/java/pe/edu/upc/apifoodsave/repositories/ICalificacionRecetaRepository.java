package pe.edu.upc.apifoodsave.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.apifoodsave.entities.CalificacionReceta;

import java.util.Optional;

@Repository
public interface ICalificacionRecetaRepository extends JpaRepository<CalificacionReceta, Integer> {
    Optional<CalificacionReceta> findByUsuario_IdUsuarioAndReceta_IdReceta(Integer idUsuario, Integer idReceta);

    @Query("select avg(c.calificacion) from CalificacionReceta c where c.receta.idReceta = :idReceta")
    Double promedioPorReceta(@Param("idReceta") Integer idReceta);

    @Query("select count(c) from CalificacionReceta c where c.receta.idReceta = :idReceta")
    Long cantidadPorReceta(@Param("idReceta") Integer idReceta);
}
