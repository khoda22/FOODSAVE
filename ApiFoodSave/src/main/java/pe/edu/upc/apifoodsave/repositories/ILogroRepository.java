package pe.edu.upc.apifoodsave.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.apifoodsave.entities.Logro;

import java.util.List;

@Repository
public interface ILogroRepository extends JpaRepository<Logro, Integer>{
    @Query(value = "SELECT \n" +
            "    u.id_usuario,\n" +
            "    u.nombre,\n" +
            "    cs.total_kg,\n" +
            "    CASE \n" +
            "        WHEN l.umbral = 1   THEN 'Primer rescate'\n" +
            "        ELSE CONCAT('Obtuviste ', l.umbral, ' puntos')\n" +
            "    END AS nombre_logro,\n" +
            "    CASE \n" +
            "        WHEN l.umbral = 1   THEN 'Obtuviste tu primer rescate de alimentos'\n" +
            "        ELSE CONCAT('Has alcanzado ', l.umbral, ' puntos por salvar kilos')\n" +
            "    END AS descripcion_logro,\n" +
            "    l.umbral AS puntos_logro\n" +
            " FROM usuario u\n" +
            " JOIN (\n" +
            "    SELECT \n" +
            "        c.id_usuario,\n" +
            "        SUM(c.kg_salvados_clasificacion) AS total_kg\n" +
            "    FROM clasificacion_semanal c\n" +
            "    GROUP BY c.id_usuario\n" +
            " ) cs ON cs.id_usuario = u.id_usuario\n" +
            " JOIN (\n" +
            "    SELECT 1 AS umbral\n" +
            "    UNION ALL SELECT 10\n" +
            "    UNION ALL SELECT 50\n" +
            "    UNION ALL SELECT 100\n" +
            " ) l ON cs.total_kg >= l.umbral\n" +
            " ORDER BY u.id_usuario, l.umbral;", nativeQuery = true)
    public List<String[]> Insignias();
}
