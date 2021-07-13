package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.CitaHistoria;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCitaHistoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioCitaHistoriaImpl implements ServicioCitaHistoria{

    RepositorioCitaHistoria repositorioCitaHistoria;

    @Autowired
    public ServicioCitaHistoriaImpl(RepositorioCitaHistoria repositorioCitaHistoria) {
        this.repositorioCitaHistoria = repositorioCitaHistoria;
    }

    @Override
    public CitaHistoria citaHistoriaById(Long idCita) {
        return repositorioCitaHistoria.citaHistoriaById(idCita);
    }

    @Override
    public void updateCitaHistoria(CitaHistoria citaHistoria) {
        repositorioCitaHistoria.updateCitaHistoria(citaHistoria);
    }
}
