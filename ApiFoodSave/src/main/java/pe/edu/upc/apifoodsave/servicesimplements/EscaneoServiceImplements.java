package pe.edu.upc.apifoodsave.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.apifoodsave.entities.Escaneo;
import pe.edu.upc.apifoodsave.repositories.IEscaneoRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IEscaneoService;

import java.util.List;

@Service
public class EscaneoServiceImplements implements IEscaneoService {
    @Autowired
    private IEscaneoRepository repository;

    @Override
    public void insert(Escaneo e) {
        repository.save(e);
    }

    @Override
    public List<Escaneo> list() {
        return repository.findAll();
    }
}
