package pe.edu.upc.apifoodsave.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.apifoodsave.entities.Seguidor;

@Repository
public interface ISeguidorRepository extends JpaRepository<Seguidor, Integer> {
    boolean existsBySeguidor_IdUsuarioAndSeguido_IdUsuario(int idSeguidor, int idSeguido);
    void deleteBySeguidor_IdUsuarioAndSeguido_IdUsuario(int idSeguidor, int idSeguido);
}
