package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.CitaDomicilio;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCitaDomicilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioCitaDomicilioImpl implements ServicioCitaDomicilio{

    RepositorioCitaDomicilio repositorioCitaDomicilio;

    @Autowired
    public ServicioCitaDomicilioImpl(RepositorioCitaDomicilio repositorioCitaDomicilio) {
        this.repositorioCitaDomicilio = repositorioCitaDomicilio;
    }

    @Override
    public CitaDomicilio getCitaById(Long id) {
        return repositorioCitaDomicilio.getCitaById(id);
    }
}
