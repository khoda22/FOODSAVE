package pe.edu.upc.apifoodsave.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.apifoodsave.dtos.NotificacionPorGrupoDTO;
import pe.edu.upc.apifoodsave.entities.Notificacion;
import pe.edu.upc.apifoodsave.repositories.INotificacionRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.INotificacionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<Notificacion> findByInventarioIdAndTipo(Integer inventarioId, boolean tipo) {
        // delega al método correcto del repository
        return repository.findByInventario_IdInventarioAndTipo(inventarioId, tipo);
    }
}
