package pe.edu.upc.apifoodsave.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.apifoodsave.entities.Logro;

import java.util.List;

@Repository
public interface ILogroRepository extends JpaRepository<Logro, Integer>{
    @Modifying
    @Transactional
    @Query(value =
            "WITH totales AS ( " +
                    "  SELECT cs.id_usuario, " +
                    "         COALESCE(SUM(cs.kg_salvados_clasificacion),0) AS kg_total, " +
                    "         COALESCE(SUM(cs.puntaje_clasificacion),0)     AS puntos_total " +
            "  FROM clasificacion_semanal cs GROUP BY cs.id_usuario " +
            "), tiers AS ( " +
            "  SELECT * FROM (VALUES " +
            "   ('Novato',10.0,100), " +
            "   ('Bronce',50.0,500), " +
            "   ('Plata',100.0,1000), " +
            "   ('Oro',250.0,2500), " +
            "   ('Diamante',500.0,5000) " +
            "  ) AS t(nombre, min_kg, min_pts) " +
            "), logros_mapeados AS ( " +
            "  SELECT l.id_logro, t.nombre, t.min_kg, t.min_pts " +
            "  FROM logro l JOIN tiers t ON t.nombre = l.nombre_logro " +
            ") " +
            "INSERT INTO usuario_logro (id_usuario, id_logro, fecha_logro) " +
            "SELECT tot.id_usuario, lm.id_logro, NOW() " +
            "FROM totales tot " +
            "JOIN logros_mapeados lm " +
            "  ON tot.kg_total >= lm.min_kg OR tot.puntos_total >= lm.min_pts " +
            "LEFT JOIN usuario_logro ul " +
            "  ON ul.id_usuario = tot.id_usuario AND ul.id_logro = lm.id_logro " +
            "WHERE ul.id_usuario_logro IS NULL",
            nativeQuery = true)
    int asignarLogrosAutomaticamente();

    @Query(value =
            "WITH totales AS ( " +
                    "  SELECT cs.id_usuario, " +
                    "         COALESCE(SUM(cs.kg_salvados_clasificacion),0) AS kg_total, " +
                    "         COALESCE(SUM(cs.puntaje_clasificacion),0)     AS puntos_total " +
            "  FROM clasificacion_semanal cs GROUP BY cs.id_usuario " +
            ") " +
            "SELECT u.username AS username, l.nombre_logro AS nombre_logro, " +
            "       t.puntos_total AS puntos_totales, t.kg_total AS kg_totales, ul.fecha_logro AS fecha_logro " +
            "FROM usuario u " +
            "JOIN usuario_logro ul ON ul.id_usuario = u.id_usuario " +
            "JOIN logro l         ON l.id_logro    = ul.id_logro " +
            "LEFT JOIN totales t  ON t.id_usuario  = u.id_usuario " +
            "WHERE u.id_usuario = :idUsuario " +
            "ORDER BY ul.fecha_logro DESC, l.puntos_logro ASC",
            nativeQuery = true)
    List<Object[]> verLogrosDeUsuario(@Param("idUsuario") int idUsuario);
}
