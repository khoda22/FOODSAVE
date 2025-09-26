package pe.edu.upc.apifoodsave.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.apifoodsave.entities.ParticipanteIntercambio;
import pe.edu.upc.apifoodsave.repositories.IParticipanteIntercambioRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IParticipanteIntercambioService;

@Service
public class ParticipanteIntercambioServiceImplements implements IParticipanteIntercambioService {
    @Autowired
    private IParticipanteIntercambioRepository repository;

    @Override
    public void insert(ParticipanteIntercambio pi) {
        repository.save(pi);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
