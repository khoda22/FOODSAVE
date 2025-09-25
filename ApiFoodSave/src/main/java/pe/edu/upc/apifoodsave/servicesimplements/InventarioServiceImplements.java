package pe.edu.upc.apifoodsave.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.apifoodsave.entities.Inventario;
import pe.edu.upc.apifoodsave.repositories.IInventarioRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IInventarioService;


import java.util.List;

@Service
public class InventarioServiceImplements implements IInventarioService {
    @Autowired
    private IInventarioRepository repository;

    @Override
    public List<Inventario> list() {
        return repository.findAll();
    }

    @Override
    public void insert(Inventario p) {
        repository.save(p);
    }

    @Override
    public Inventario listId(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public void edit(Inventario p) {
        repository.save(p);
    }
}
