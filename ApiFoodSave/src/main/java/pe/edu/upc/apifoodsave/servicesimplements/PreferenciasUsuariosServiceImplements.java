package pe.edu.upc.apifoodsave.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.apifoodsave.entities.PreferenciasUsuario;
import pe.edu.upc.apifoodsave.repositories.IPreferenciasUsuarioRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IPreferenciasUsuarioService;

import java.util.List;

@Service
public class PreferenciasUsuariosServiceImplements implements IPreferenciasUsuarioService {

    @Autowired
    private IPreferenciasUsuarioRepository puR;

    @Override
    public List<PreferenciasUsuario> listar() {
        return puR.findAll();
    }

    @Override
    public void Registrar(PreferenciasUsuario pu){
        puR.save(pu);
    }

    @Override
    public PreferenciasUsuario listarporid(int id){
        return puR.findById(id).orElse(new PreferenciasUsuario());
    }

    @Override
    public void Modificar(PreferenciasUsuario pu){
        puR.save(pu);
    }

    @Override
    public void Eliminar(int id){
        puR.deleteById(id);
    }
}
