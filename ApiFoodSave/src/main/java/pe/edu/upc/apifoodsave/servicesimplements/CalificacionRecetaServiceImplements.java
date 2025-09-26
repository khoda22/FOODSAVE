package pe.edu.upc.apifoodsave.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.apifoodsave.entities.CalificacionReceta;
import pe.edu.upc.apifoodsave.repositories.ICalificacionRecetaRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.ICalificacionRecetaService;

import java.util.List;

@Service
public class CalificacionRecetaServiceImplements implements ICalificacionRecetaService {
    @Autowired
    private ICalificacionRecetaRepository repository;

    @Override
    public void insert(CalificacionReceta cr) {
        repository.save(cr);
    }

    @Override
    public List<CalificacionReceta> list() {
        return repository.findAll();
    }
}
