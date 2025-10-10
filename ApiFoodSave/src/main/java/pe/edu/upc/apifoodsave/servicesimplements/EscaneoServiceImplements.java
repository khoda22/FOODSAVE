package pe.edu.upc.apifoodsave.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.apifoodsave.entities.Escaneo;
import pe.edu.upc.apifoodsave.repositories.IEscaneoRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IEscaneoService;

import java.time.LocalDate;
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

    @Override
    public List<Escaneo> obtenerHistorialUltimos7DiasService() {
        LocalDate hace7Dias = LocalDate.now().minusDays(7);
        return repository.findEscaneosUltimosDias(hace7Dias);
    }
}
