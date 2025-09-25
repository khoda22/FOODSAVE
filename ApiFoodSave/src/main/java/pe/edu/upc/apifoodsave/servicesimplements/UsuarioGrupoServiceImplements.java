package pe.edu.upc.apifoodsave.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.apifoodsave.entities.UsuarioGrupo;
import pe.edu.upc.apifoodsave.repositories.IUsuarioGrupoRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IUsuarioGrupoService;

@Service
public class UsuarioGrupoServiceImplements implements IUsuarioGrupoService {
    @Autowired
    private IUsuarioGrupoRepository repository;

    @Override
    public void insert(UsuarioGrupo ug) {
        repository.save(ug);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
