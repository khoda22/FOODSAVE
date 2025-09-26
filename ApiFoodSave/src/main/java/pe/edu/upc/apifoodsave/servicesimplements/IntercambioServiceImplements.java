package pe.edu.upc.apifoodsave.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.apifoodsave.entities.Intercambio;
import pe.edu.upc.apifoodsave.repositories.IIntercambioRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IIntercambioService;

import java.util.List;

@Service
public class IntercambioServiceImplements implements IIntercambioService {
    @Autowired
    private IIntercambioRepository repository;

    @Override
    public void insert(Intercambio i) {
        repository.save(i);
    }

    @Override
    public List<Intercambio> list() {
        return repository.findAll();
    }

    @Override
    public Intercambio listId(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void edit(Intercambio i) {
        repository.save(i);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
