package pe.edu.upc.apifoodsave.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.apifoodsave.entities.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto,Integer>{
}
