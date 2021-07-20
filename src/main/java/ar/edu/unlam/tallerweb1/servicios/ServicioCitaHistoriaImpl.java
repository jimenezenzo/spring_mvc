package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.CitaHistoria;
import ar.edu.unlam.tallerweb1.modelo.EstadoCita;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCitaHistoria;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class ServicioCitaHistoriaImpl implements ServicioCitaHistoria{

    RepositorioCitaHistoria repositorioCitaHistoria;
    RepositorioMedico repositorioMedico;

    @Autowired
    public ServicioCitaHistoriaImpl(RepositorioCitaHistoria repositorioCitaHistoria, RepositorioMedico repositorioMedico) {
        this.repositorioCitaHistoria = repositorioCitaHistoria;
        this.repositorioMedico = repositorioMedico;
    }

    @Override
    public CitaHistoria citaHistoriaById(Long idCita) {
        return repositorioCitaHistoria.citaHistoriaById(idCita);
    }

    @Override
    public void updateCitaHistoria(CitaHistoria citaHistoria) {
        repositorioCitaHistoria.updateCitaHistoria(citaHistoria);
    }

    @Override
    public void observarCitaConsultorio(Long idCita, String observacion, String estado) {
        CitaHistoria citaHistoria = new CitaHistoria();
        citaHistoria.setObservacion(observacion);
        citaHistoria.setFechaRegistro(LocalDateTime.now());
        citaHistoria.setCita(this.repositorioMedico.obtenerCitaConsultorioId(idCita));

        switch (estado){
            case "observado":
                citaHistoria.setEstado(EstadoCita.OBSERVADO);
                break;
            case "finalizado":
                citaHistoria.setEstado(EstadoCita.FINALIZADO);
                break;
            case "cancelado":
                citaHistoria.setEstado(EstadoCita.CANCELADO);
                break;
        }

        this.repositorioCitaHistoria.guardarHistoria(citaHistoria);
    }
}
