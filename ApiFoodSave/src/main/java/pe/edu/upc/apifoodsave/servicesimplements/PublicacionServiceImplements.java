package pe.edu.upc.apifoodsave.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.apifoodsave.entities.Publicacion;
import pe.edu.upc.apifoodsave.repositories.IPublicacionRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IPublicacionService;

import java.util.List;

@Service
public class PublicacionServiceImplements implements IPublicacionService {
    @Autowired
    private IPublicacionRepository repository;

    @Override
    public void insert(Publicacion p) {
        repository.save(p);
    }

    @Override
    public List<Publicacion> list() {
        return repository.findAll();
    }

    @Override
    public Publicacion listId(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void edit(Publicacion p) {
        repository.save(p);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
