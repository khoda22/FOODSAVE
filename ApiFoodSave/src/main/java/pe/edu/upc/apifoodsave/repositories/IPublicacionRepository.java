package pe.edu.upc.apifoodsave.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.apifoodsave.entities.Publicacion;

@Repository
public interface IPublicacionRepository extends JpaRepository<Publicacion, Integer>{
}
