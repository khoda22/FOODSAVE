package pe.edu.upc.apifoodsave.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.apifoodsave.entities.Producto;
import pe.edu.upc.apifoodsave.repositories.IProductoRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IProductoService;

import java.util.List;

@Service
public class ProductoServiceImplements implements IProductoService {
    @Autowired
    private IProductoRepository repository;

    @Override
    public List<Producto> list() {
        return repository.findAll();
    }

    @Override
    public void insert(Producto p) {
        repository.save(p);
    }

    @Override
    public Producto listId(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public void edit(Producto p) {
        repository.save(p);
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        return repository.buscarPorNombre(nombre);
    }

    @Override
    public List<Producto> buscarPorCodigoBarra(Integer codigo) {
        return repository.buscarPorCodigoBarra(codigo);
    }

    @Override
    public List<Producto> sugerirProductosSimilares(String termino) {
        return repository.buscarSimilares(termino);
    }

}
