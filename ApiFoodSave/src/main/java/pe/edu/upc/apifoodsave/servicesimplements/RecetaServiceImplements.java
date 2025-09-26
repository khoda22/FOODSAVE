package pe.edu.upc.apifoodsave.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.apifoodsave.entities.Receta;
import pe.edu.upc.apifoodsave.repositories.IRecetaRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IRecetaService;

import java.util.List;

@Service
public class RecetaServiceImplements implements IRecetaService {
    @Autowired
    private IRecetaRepository repository;

    @Override
    public void insert(Receta r) {
        repository.save(r);
    }

    @Override
    public List<Receta> list() {
        return repository.findAll();
    }

    @Override
    public Receta listId(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void edit(Receta r) {
        repository.save(r);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
