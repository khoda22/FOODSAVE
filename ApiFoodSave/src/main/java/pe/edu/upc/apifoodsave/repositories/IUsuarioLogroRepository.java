package pe.edu.upc.apifoodsave.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.apifoodsave.entities.UsuarioLogro;

import java.util.Optional;

@Repository
public interface IUsuarioLogroRepository extends JpaRepository<UsuarioLogro, Integer>{
    boolean existsByUsuario_IdUsuarioAndLogro_IdLogro(int idUsuario, int idLogro);

}
