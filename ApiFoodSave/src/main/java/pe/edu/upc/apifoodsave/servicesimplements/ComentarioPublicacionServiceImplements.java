package pe.edu.upc.apifoodsave.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.apifoodsave.entities.ComentarioPublicacion;
import pe.edu.upc.apifoodsave.repositories.IComentarioPublicacionRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IComentarioPublicacionService;

import java.util.List;

@Service
public class ComentarioPublicacionServiceImplements implements IComentarioPublicacionService{
    @Autowired
    private IComentarioPublicacionRepository repository;

    @Override
    public void insert(ComentarioPublicacion c) {
        repository.save(c);
    }

    @Override
    public List<ComentarioPublicacion> list() {
        return repository.findAll();
    }

    @Override
    public ComentarioPublicacion listId(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void edit(ComentarioPublicacion c) {
        repository.save(c);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
