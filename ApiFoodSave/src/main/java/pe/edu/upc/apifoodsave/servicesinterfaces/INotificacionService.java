package pe.edu.upc.apifoodsave.servicesinterfaces;

import pe.edu.upc.apifoodsave.dtos.NotificacionPorGrupoDTO;
import pe.edu.upc.apifoodsave.entities.Notificacion;

import java.util.List;
import java.util.Optional;

public interface INotificacionService {
    public void insert(Notificacion n);
    public List<Notificacion> list();
    Optional<Notificacion> findByInventarioIdAndTipo(Integer inventarioId, boolean tipo);
}
