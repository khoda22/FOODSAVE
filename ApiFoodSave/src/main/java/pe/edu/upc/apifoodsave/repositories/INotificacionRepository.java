package pe.edu.upc.apifoodsave.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.apifoodsave.entities.Notificacion;

import java.util.List;

@Repository
public interface INotificacionRepository extends JpaRepository<Notificacion, Integer> {
    @Query(value = "SELECT \n" +
            "  g.nombre_grupo AS nombre_grupo,\n" +
            "  COUNT(n.id_notificacion) AS total_notificaciones,\n" +
            "  COUNT(DISTINCT ug.id_usuario) AS total_usuarios,\n" +
            "  ROUND(1.0 * COUNT(n.id_notificacion) / NULLIF(COUNT(DISTINCT ug.id_usuario), 0), 2) AS promedio_por_usuario\n" +
            "FROM grupo g\n" +
            "JOIN usuario_grupo ug ON g.id_grupo = ug.id_grupo\n" +
            "JOIN inventario i ON ug.id_usuario = i.id_usuario\n" +
            "JOIN notificacion n ON i.id_inventario = n.id_inventario\n" +
            "GROUP BY g.id_grupo, g.nombre_grupo\n" +
            "ORDER BY promedio_por_usuario DESC", nativeQuery = true)
    public List<Object[]> comparacionNotificacionesPorGrupo();
}
