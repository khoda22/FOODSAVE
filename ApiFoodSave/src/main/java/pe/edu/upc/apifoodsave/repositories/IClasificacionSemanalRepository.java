package pe.edu.upc.apifoodsave.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.apifoodsave.entities.ClasificacionSemanal;

import java.util.List;

@Repository
public interface IClasificacionSemanalRepository extends JpaRepository<ClasificacionSemanal, Integer> {
    @Query(value = "SELECT \n" +
            "    u.id_usuario    AS userId,\n" +
            "    u.nombre        AS username,\n" +
            "    SUM(c.puntaje_clasificacion + c.kg_salvados_clasificacion) AS totalPuntos,\n" +
            "    SUM(c.kg_salvados_clasificacion)                           AS totalKg,\n" +
            "    RANK() OVER (ORDER BY SUM(c.puntaje_clasificacion + c.kg_salvados_clasificacion) DESC) AS rank\n" +
            " FROM clasificacion_semanal c\n" +
            " JOIN usuario u ON u.id_usuario = c.id_usuario\n" +
            " WHERE c.periodo_clasificacion = c.periodo_clasificacion\n" +
            " GROUP BY u.id_usuario, u.nombre\n" +
            " ORDER BY totalPuntos DESC;", nativeQuery = true)
    public List<String[]> RankClasificacionSemanal();
}
