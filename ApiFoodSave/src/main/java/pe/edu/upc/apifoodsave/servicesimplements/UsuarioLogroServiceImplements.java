package pe.edu.upc.apifoodsave.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.apifoodsave.entities.UsuarioLogro;
import pe.edu.upc.apifoodsave.repositories.IUsuarioLogroRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IUsuarioLogroService;

@Service
public class UsuarioLogroServiceImplements implements IUsuarioLogroService {
    @Autowired
    private IUsuarioLogroRepository repository;

    @Override
    public void insert(UsuarioLogro ul) {
        repository.save(ul);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
