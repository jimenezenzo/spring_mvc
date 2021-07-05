package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.CitaConsultorio;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCitaConsultorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioCitaConsultorioImpl implements ServicioCitaConsultorio{

    RepositorioCitaConsultorio repositorioCitaConsultorio;

    @Autowired
    public ServicioCitaConsultorioImpl(RepositorioCitaConsultorio repositorioCitaConsultorio) {
        this.repositorioCitaConsultorio = repositorioCitaConsultorio;
    }

    @Override
    public CitaConsultorio getCitaById(Long id) {
        return repositorioCitaConsultorio.getCitaById(id);
    }
}
