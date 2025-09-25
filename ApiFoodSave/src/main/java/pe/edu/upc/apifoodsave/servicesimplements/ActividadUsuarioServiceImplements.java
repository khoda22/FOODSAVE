package pe.edu.upc.apifoodsave.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.apifoodsave.entities.ActividadUsuario;
import pe.edu.upc.apifoodsave.repositories.IActividadUsuariosRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IActividadUsuarioService;

import java.util.List;

@Service
public class ActividadUsuarioServiceImplements implements IActividadUsuarioService{
    @Autowired
    private IActividadUsuariosRepository auR;

    @Override
    public List<ActividadUsuario> listar() {
        return auR.findAll();
    }

    @Override
    public void Registrar(ActividadUsuario au){
        auR.save(au);
    }

    @Override
    public ActividadUsuario listarporid(int id){
        return auR.findById(id).orElse(new ActividadUsuario());
    }

    @Override
    public void Modificar(ActividadUsuario au){
        auR.save(au);
    }

    @Override
    public void Eliminar(int id){
        auR.deleteById(id);
    }
}
