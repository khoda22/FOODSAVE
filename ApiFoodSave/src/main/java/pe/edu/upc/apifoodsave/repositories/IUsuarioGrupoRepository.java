package pe.edu.upc.apifoodsave.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.apifoodsave.entities.UsuarioGrupo;

@Repository
public interface IUsuarioGrupoRepository extends JpaRepository<UsuarioGrupo, Integer>{
    boolean existsByUsuario_IdUsuarioAndGrupo_IdGrupo(int idUsuario, int idGrupo);
}
