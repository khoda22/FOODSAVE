package pe.edu.upc.apifoodsave.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.apifoodsave.entities.Producto;

import java.util.List;

@Repository
public interface IProductoRepository extends JpaRepository<Producto,Integer>{
    @Query(value = "SELECT * FROM producto WHERE LOWER(nombre) LIKE LOWER('%' || ?1 || '%')", nativeQuery = true)
    List<Producto> buscarPorNombre(String nombre);

    @Query(value = "SELECT * FROM producto WHERE codigo_barra = ?1", nativeQuery = true)
    List<Producto> buscarPorCodigoBarra(Integer codigo);

    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :termino, '%')) OR LOWER(p.categoria) LIKE LOWER(CONCAT('%', :termino, '%'))")
    List<Producto> buscarSimilares(@Param("termino") String termino);
}
