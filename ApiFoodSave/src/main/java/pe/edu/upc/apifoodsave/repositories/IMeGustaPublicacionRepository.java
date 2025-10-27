package pe.edu.upc.apifoodsave.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.apifoodsave.entities.MeGustaPublicacion;

import java.util.Optional;

@Repository
public interface IMeGustaPublicacionRepository extends JpaRepository<MeGustaPublicacion, Integer>{
    boolean existsByUsuario_IdUsuarioAndPublicacion_IdPublicacion(int idUsuario, int idPublicacion);
    void deleteByUsuario_IdUsuarioAndPublicacion_IdPublicacion(int idUsuario, int idPublicacion);
    Optional<MeGustaPublicacion> findByUsuario_IdUsuarioAndPublicacion_IdPublicacion(int idUsuario, int idPublicacion);
}