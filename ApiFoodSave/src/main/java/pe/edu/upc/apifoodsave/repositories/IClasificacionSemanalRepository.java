package pe.edu.upc.apifoodsave.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.apifoodsave.entities.ClasificacionSemanal;

import java.util.List;

@Repository
public interface IClasificacionSemanalRepository extends JpaRepository<ClasificacionSemanal, Integer> {
    /**
     * Recalcula los puntos de TODAS las filas: puntos = floor(kg * 10)
     * (function('floor', ...) es JPQL portable; Hibernate la traduce a PostgreSQL).
     */
    @Modifying
    @Transactional
    @Query("UPDATE ClasificacionSemanal cs " +
            "SET cs.puntajeClasificacion = function('floor', cs.kgSalvadosClasificacion * 10)")
    int recalcularPuntosParaTodos();

    // Ranking de una semana exacta (ej: "2025-W44") SIN sumar: usa el último registro por usuario/semana
    @Query(value =
            "WITH latest AS ( " +
                    "  SELECT * FROM ( " +
                    "    SELECT cs.*, " +
                    "           ROW_NUMBER() OVER (PARTITION BY cs.id_usuario, cs.periodo_clasificacion " +
                    "                              ORDER BY cs.id_clasificacion DESC) AS rn " +
                    "    FROM clasificacion_semanal cs " +
                    "    WHERE cs.periodo_clasificacion = :periodo " +
                    "  ) x WHERE x.rn = 1 " +
                    ") " +
                    "SELECT RANK() OVER (ORDER BY l.puntaje_clasificacion DESC, l.kg_salvados_clasificacion DESC, l.id_usuario ASC) AS posicion, " +
                    "       l.id_usuario, u.username, " +
                    "       l.kg_salvados_clasificacion AS kg_totales, " +
                    "       l.puntaje_clasificacion     AS puntos_totales, " +
                    "       l.periodo_clasificacion     AS periodo " +
                    "FROM latest l " +
                    "JOIN usuario u ON u.id_usuario = l.id_usuario " +
                    "ORDER BY posicion",
            nativeQuery = true)
    List<Object[]> rankingSemanal(@Param("periodo") String periodo);

    // Ranking de TODAS las semanas, cada semana con su propio ranking
    @Query(value =
            "WITH latest AS ( " +
                    "  SELECT * FROM ( " +
                    "    SELECT cs.*, " +
                    "           ROW_NUMBER() OVER (PARTITION BY cs.id_usuario, cs.periodo_clasificacion " +
                    "                              ORDER BY cs.id_clasificacion DESC) AS rn " +
                    "    FROM clasificacion_semanal cs " +
                    "  ) x WHERE x.rn = 1 " +
                    ") " +
                    "SELECT RANK() OVER (PARTITION BY l.periodo_clasificacion " +
                    "                    ORDER BY l.puntaje_clasificacion DESC, l.kg_salvados_clasificacion DESC, l.id_usuario ASC) AS posicion, " +
                    "       l.periodo_clasificacion AS periodo, " +
                    "       l.id_usuario, u.username, " +
                    "       l.kg_salvados_clasificacion AS kg_totales, " +
                    "       l.puntaje_clasificacion     AS puntos_totales " +
                    "FROM latest l " +
                    "JOIN usuario u ON u.id_usuario = l.id_usuario " +
                    "ORDER BY l.periodo_clasificacion, posicion",
            nativeQuery = true)
    List<Object[]> rankingTodasSemanas();

    // Ranking de la semana ISO actual (ej: "2025-W44") SIN sumar
    @Query(value =
            "WITH per AS (SELECT to_char(now(), 'IYYY') || '-W' || lpad(to_char(now(), 'IW'), 2, '0') AS p), " +
                    "latest AS ( " +
                    "  SELECT * FROM ( " +
                    "    SELECT cs.*, " +
                    "           ROW_NUMBER() OVER (PARTITION BY cs.id_usuario, cs.periodo_clasificacion " +
                    "                              ORDER BY cs.id_clasificacion DESC) AS rn " +
                    "    FROM clasificacion_semanal cs " +
                    "    JOIN per ON cs.periodo_clasificacion = per.p " +
                    "  ) x WHERE x.rn = 1 " +
                    ") " +
                    "SELECT RANK() OVER (ORDER BY l.puntaje_clasificacion DESC, l.kg_salvados_clasificacion DESC, l.id_usuario ASC) AS posicion, " +
                    "       l.id_usuario, u.username, " +
                    "       l.kg_salvados_clasificacion AS kg_totales, " +
                    "       l.puntaje_clasificacion     AS puntos_totales, " +
                    "       l.periodo_clasificacion     AS periodo " +
                    "FROM latest l " +
                    "JOIN usuario u ON u.id_usuario = l.id_usuario " +
                    "ORDER BY posicion",
            nativeQuery = true)
    List<Object[]> rankingSemanalActual();
}
