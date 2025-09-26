package pe.edu.upc.apifoodsave.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.apifoodsave.entities.Donacion;
import pe.edu.upc.apifoodsave.repositories.IDonacionRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IDonacionService;

import java.util.List;

@Service
public class DonacionServiceImplements implements IDonacionService {
    @Autowired
    private IDonacionRepository repository;

    @Override
    public void insert(Donacion d) {
        repository.save(d);
    }

    @Override
    public List<Donacion> list() {
        return repository.findAll();
    }

    @Override
    public Donacion listId(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void edit(Donacion d) {
        repository.save(d);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
