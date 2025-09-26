package pe.edu.upc.apifoodsave.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.apifoodsave.entities.Donacion;

@Repository
public interface IDonacionRepository extends JpaRepository<Donacion, Integer> {
}
