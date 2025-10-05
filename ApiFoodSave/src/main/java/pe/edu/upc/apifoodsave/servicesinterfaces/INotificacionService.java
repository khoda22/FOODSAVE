package pe.edu.upc.apifoodsave.servicesinterfaces;

import pe.edu.upc.apifoodsave.dtos.NotificacionPorGrupoDTO;
import pe.edu.upc.apifoodsave.entities.Notificacion;

import java.util.List;

public interface INotificacionService {
    public void insert(Notificacion n);
    public List<Notificacion> list();
    public List<NotificacionPorGrupoDTO> comparacionNotificacionesPorGrupo();
}
