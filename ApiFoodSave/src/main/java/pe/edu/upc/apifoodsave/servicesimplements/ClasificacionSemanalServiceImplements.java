package pe.edu.upc.apifoodsave.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.apifoodsave.entities.ClasificacionSemanal;
import pe.edu.upc.apifoodsave.repositories.IClasificacionSemanalRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IClasificacionSemanalService;

import java.util.List;

@Service
public class ClasificacionSemanalServiceImplements implements IClasificacionSemanalService{
    @Autowired
    private IClasificacionSemanalRepository repository;

    @Override
    public void insert(ClasificacionSemanal cs) {
        repository.save(cs);
    }

    @Override
    public void edit(ClasificacionSemanal cs) {
        repository.save(cs);
    }

    @Override
    public List<ClasificacionSemanal> list() {
        return repository.findAll();
    }

    @Override
    public ClasificacionSemanal listId(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
