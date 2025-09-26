package pe.edu.upc.apifoodsave.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.apifoodsave.entities.FavoritoReceta;
import pe.edu.upc.apifoodsave.repositories.IFavoritoRecetaRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IFavoritoRecetaService;

import java.util.List;

@Service
public class FavoritoRecetaServiceImplements implements IFavoritoRecetaService {
    @Autowired
    private IFavoritoRecetaRepository repository;

    @Override
    public void insert(FavoritoReceta fr) {
        repository.save(fr);
    }

    @Override
    public List<FavoritoReceta> list() {
        return repository.findAll();
    }

    @Override
    public FavoritoReceta listId(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
