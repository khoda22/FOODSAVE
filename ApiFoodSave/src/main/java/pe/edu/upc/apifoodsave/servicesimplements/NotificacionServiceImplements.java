package pe.edu.upc.apifoodsave.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.apifoodsave.dtos.NotificacionPorGrupoDTO;
import pe.edu.upc.apifoodsave.entities.Notificacion;
import pe.edu.upc.apifoodsave.repositories.INotificacionRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.INotificacionService;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificacionServiceImplements implements INotificacionService {
    @Autowired
    private INotificacionRepository repository;

    @Override
    public void insert(Notificacion n) {
        repository.save(n);
    }

    @Override
    public List<Notificacion> list() {
        return repository.findAll();
    }

    @Override
    public List<NotificacionPorGrupoDTO> comparacionNotificacionesPorGrupo() {
        List<Object[]> results = repository.comparacionNotificacionesPorGrupo();
        List<NotificacionPorGrupoDTO> dtos = new ArrayList<>();

        for (Object[] row : results) {
            NotificacionPorGrupoDTO dto = new NotificacionPorGrupoDTO(
                    (String) row[0],                      // nombreGrupo
                    ((Number) row[1]).intValue(),         // totalNotificaciones
                    ((Number) row[2]).intValue(),         // totalUsuarios
                    ((Number) row[3]).doubleValue()       // promedioNotificacionesPorUsuario
            );
            dtos.add(dto);
        }

        return dtos;
    }
}
