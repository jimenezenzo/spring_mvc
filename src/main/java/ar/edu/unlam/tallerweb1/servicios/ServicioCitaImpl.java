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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

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
    public void registrarCitaConsultorio(DatosCitaConsultio datosCitaConsultio) {
        CitaConsultorio citaConsultorio = new CitaConsultorio();
        citaConsultorio.setFecha(LocalDate.parse(datosCitaConsultio.getFecha()));
        citaConsultorio.setHora(LocalTime.parse(datosCitaConsultio.getHora()));
        citaConsultorio.setEspecialidad(this.repositorioCita.especialidadById((long) datosCitaConsultio.getEspecialidad()));
        citaConsultorio.setMedico(this.repositorioMedico.consultarMedicoPorId((long) datosCitaConsultio.getMedico()));
        citaConsultorio.setPaciente(this.repositorioPaciente.obtenerPacientePorEmail(datosCitaConsultio.getPaciente()));
        citaConsultorio.setFechaRegistro(LocalDateTime.now());

        this.repositorioPaciente.registrarCita(citaConsultorio);
    }

    @Override
    public List<String> getHorariosDia(Long medico, String fecha) {
        DateTimeFormatter formatoDia = DateTimeFormatter
                .ofPattern("EEEE")
                .withLocale(new Locale("es", "AR"));
        LocalDate fechaLocal = LocalDate.parse(fecha);
        String dia = fechaLocal.format(formatoDia);

        Agenda agenda = this.repositorioMedico.getDiaAgenda(medico, dia);
        List<String> intervalos = new ArrayList<>();
        if (!agenda.getActivo()){
            return intervalos;
        }

        LocalTime interIni = agenda.getHoraDesde();
        LocalTime interFin = agenda.getHoraHasta();
        intervalos.add(interIni.toString());

        List<CitaConsultorio> citasDeLaFecha = this.repositorioMedico.obtenerCitasPorFechaMedicoId(medico, fechaLocal);
        List<String> horariosNoDisponibles = new ArrayList<>();
        for (CitaConsultorio c:citasDeLaFecha){
            horariosNoDisponibles.add(c.getHora().toString());
        }

        while (interIni.isBefore(interFin)){
            interIni = interIni.plusMinutes(40);
            if (!horariosNoDisponibles.contains(interIni.toString()))
                intervalos.add(interIni.toString());
        }

        return intervalos;
    }

    @Override
    public List<Especialidad> allEspecialidades() {
        return repositorioCita.allEspecialidad();
    }

    @Override
    public void create(DatosCitaConsultio datosCita) {
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
    public List medicosByEspecialidad(Long idEspecialidad) {
        return repositorioCita.medicoByEspecialidad(idEspecialidad);
    }
}
