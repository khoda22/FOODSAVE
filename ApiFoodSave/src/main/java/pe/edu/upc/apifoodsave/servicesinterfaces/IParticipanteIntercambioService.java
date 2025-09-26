package pe.edu.upc.apifoodsave.servicesinterfaces;

import pe.edu.upc.apifoodsave.entities.ParticipanteIntercambio;

public interface IParticipanteIntercambioService {
    public void insert(ParticipanteIntercambio pi);
    public void delete(int id);
}
