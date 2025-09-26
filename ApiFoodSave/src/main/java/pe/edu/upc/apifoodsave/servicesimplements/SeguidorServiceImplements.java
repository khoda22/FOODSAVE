package pe.edu.upc.apifoodsave.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.apifoodsave.entities.Seguidor;
import pe.edu.upc.apifoodsave.repositories.ISeguidorRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.ISeguidorService;

import java.util.List;

@Service
public class SeguidorServiceImplements implements ISeguidorService {
    @Autowired
    private ISeguidorRepository repository;

    @Override
    public void insert(Seguidor s) {
        repository.save(s);
    }

    @Override
    public List<Seguidor> list() {
        return repository.findAll();
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
