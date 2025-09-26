package pe.edu.upc.apifoodsave.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.apifoodsave.entities.Receta;

@Repository
public interface IRecetaRepository extends JpaRepository<Receta, Integer> {
}
