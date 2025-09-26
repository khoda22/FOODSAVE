package pe.edu.upc.apifoodsave.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.apifoodsave.entities.Intercambio;

@Repository
public interface IIntercambioRepository extends JpaRepository<Intercambio, Integer> {
}
