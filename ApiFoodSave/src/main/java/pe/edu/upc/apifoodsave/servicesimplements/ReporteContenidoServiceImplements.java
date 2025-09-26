package pe.edu.upc.apifoodsave.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.apifoodsave.entities.ReporteContenido;
import pe.edu.upc.apifoodsave.repositories.IReporteContenidoRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IReporteContenidoService;

import java.util.List;

@Service
public class ReporteContenidoServiceImplements implements IReporteContenidoService {
    @Autowired
    private IReporteContenidoRepository repository;

    @Override
    public void insert(ReporteContenido rc) {
        repository.save(rc);
    }

    @Override
    public List<ReporteContenido> list() {
        return repository.findAll();
    }

    @Override
    public ReporteContenido listId(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void edit(ReporteContenido rc) {
        repository.save(rc);
    }
}
