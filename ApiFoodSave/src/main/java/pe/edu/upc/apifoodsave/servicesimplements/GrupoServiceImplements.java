package pe.edu.upc.apifoodsave.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.apifoodsave.entities.Grupo;
import pe.edu.upc.apifoodsave.repositories.IGrupoRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IGrupoService;

import java.util.List;
@Service
public class GrupoServiceImplements implements IGrupoService {
    @Autowired
    private IGrupoRepository repository;

    @Override
    public void insert(Grupo g) {
        repository.save(g);
    }

    @Override
    public List<Grupo> list() {
        return repository.findAll();
    }

    @Override
    public Grupo listId(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void edit(Grupo g) {
        repository.save(g);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
