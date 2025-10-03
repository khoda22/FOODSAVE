package pe.edu.upc.apifoodsave.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.apifoodsave.entities.Logro;
import pe.edu.upc.apifoodsave.repositories.ILogroRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.ILogroService;

import java.util.List;

@Service
public class LogroServiceImplements implements ILogroService {
    @Autowired
    private ILogroRepository repository;

    @Override
    public void insert(Logro l) {
        repository.save(l);
    }

    @Override
    public List<Logro> list() {
        return repository.findAll();
    }

    @Override
    public Logro listId(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void edit(Logro l) {
        repository.save(l);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public List<String[]> InsigniasService() {
        return repository.Insignias();
    }
}
