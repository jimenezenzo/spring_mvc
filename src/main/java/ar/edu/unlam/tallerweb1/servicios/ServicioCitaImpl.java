package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.modelo.datos.DatosCitaConsultio;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCita;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioMedico;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
public class ServicioCitaImpl implements ServicioCita {



    private RepositorioPaciente repositorioPaciente;
    private RepositorioCita repositorioCita;
    private RepositorioMedico repositorioMedico;

    @Autowired
    public ServicioCitaImpl(RepositorioPaciente repositorioPaciente, RepositorioCita repositorioCita, RepositorioMedico repositorioMedico){
        this.repositorioPaciente = repositorioPaciente;
        this.repositorioCita = repositorioCita;
        this.repositorioMedico = repositorioMedico;
    }

    @Override
    public void registrarCitaConsultorio(DatosCitaConsultio datosCita) {
        CitaHistoria citaHistoria = new CitaHistoria();
        citaHistoria.setEstado(EstadoCita.CREADO);
        citaHistoria.setObservacion("Creado");
        citaHistoria.setFechaRegistro(LocalDateTime.now());

        CitaConsultorio citaConsultorio =  new CitaConsultorio();
        citaConsultorio.setFechaRegistro(LocalDateTime.now());
        citaConsultorio.setFecha(LocalDate.parse(datosCita.getFecha()));
        citaConsultorio.setHora(LocalTime.parse(datosCita.getHora()));
        citaConsultorio.setPaciente(this.repositorioPaciente.obtenerPacientePorEmail(datosCita.getPaciente()));
        citaConsultorio.setMedico(this.repositorioMedico.consultarMedicoPorId((long) datosCita.getMedico()));
        citaConsultorio.setEspecialidad(this.repositorioCita.especialidadById((long) datosCita.getEspecialidad()));
        citaConsultorio.agregarHistoria(citaHistoria);

        this.repositorioCita.registrarCitaConsultorio(citaConsultorio);
    }


    @Override
    public List<Especialidad> allEspecialidades() {
        return repositorioCita.allEspecialidad();
    }

    @Override
    public List medicosByEspecialidad(Long idEspecialidad) {
        return repositorioCita.medicoByEspecialidad(idEspecialidad);
    }



}
