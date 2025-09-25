package pe.edu.upc.apifoodsave.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.apifoodsave.entities.MeGustaPublicacion;
import pe.edu.upc.apifoodsave.repositories.IMeGustaPublicacionRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IMeGustaPublicacionService;

@Service
public class MeGustaPublicacionServiceImplements implements IMeGustaPublicacionService {
    @Autowired
    private IMeGustaPublicacionRepository repository;

    @Override
    public void insert(MeGustaPublicacion mg) {
        repository.save(mg);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
