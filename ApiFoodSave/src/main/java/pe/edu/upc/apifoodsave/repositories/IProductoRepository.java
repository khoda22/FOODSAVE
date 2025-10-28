package pe.edu.upc.apifoodsave.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.apifoodsave.entities.Producto;

import java.util.Optional;

@Repository
public interface IProductoRepository extends JpaRepository<Producto,Integer>{
    Optional<Producto> findByCodigoBarra(String codigoBarra);
}
