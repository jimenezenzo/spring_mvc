package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
public class ServicioMedicoImpl implements ServicioMedico{

    private RepositorioMedico repositorioMedico;

    @Autowired
    public ServicioMedicoImpl(RepositorioMedico repositorioMedico){
        this.repositorioMedico = repositorioMedico;
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
    public List<Medico> obtenerMedicosTodos() {
        return this.repositorioMedico.obtenerTodosLosMedicos();
    }

    @Override
    public List<CitaDomicilio> obtenerCitasDomicilio(String username) {
       return this.repositorioMedico.obtenerCitasDomicilio(username);
    }

    @Override
    public List obtenerCitasConsultorio(String username) {
        return this.repositorioMedico.obtenerCitasConsultorio(username);
    }

    @Override
    public List<Cita> obtenerCitasDelDia(String username) {
        Medico medico = this.consultarMedicoPorEmail(username);
        List citas;

        String dia = this.formatearFecha(LocalDate.now().toString());

        Agenda agenda = this.repositorioMedico.getDiaAgenda(medico.getId(), dia);
        if (agenda.getGuardia()){
            citas = new ArrayList<CitaDomicilio>();
            citas = this.repositorioMedico.obtenerCitasDomicilioPorFecha(medico, LocalDateTime.now());
        }
        else{
            citas = new ArrayList<CitaConsultorio>();
            citas = this.repositorioMedico.obtenerCitasConsultorioPorFecha(medico, LocalDate.now());
        }

        return citas;
    }

    @Override
    public Boolean getGuardia(String username) {
        Medico medico = this.consultarMedicoPorEmail(username);

        String dia = this.formatearFecha(LocalDate.now().toString());

        Agenda agenda = this.repositorioMedico.getDiaAgenda(medico.getId(), dia);

        return agenda.getGuardia();
    }

    @Override
    public Agenda getAgendaHoy(String username){
        Medico medico = this.consultarMedicoPorEmail(username);

        String dia = this.formatearFecha(LocalDate.now().toString());

        Agenda agenda = this.repositorioMedico.getDiaAgenda(medico.getId(), dia);

        return agenda;
    }

    @Override
    public List getAgenda(String username) {
        return repositorioMedico.obtenerAgenda(username);
    }

    @Override
    public void actualizarAgenda(Agenda agenda, String username) {
        agenda.setMedico(this.consultarMedicoPorEmail(username));
        if (agenda.getActivo() == null) {
            agenda.setActivo(false);
        } else {
            agenda.setActivo(true);
        }

        if (agenda.getGuardia() == null) {
            agenda.setGuardia(false);
        } else {
            agenda.setGuardia(true);
        }

        this.repositorioMedico.actualizarAgenda(agenda);
    }

    private String formatearFecha(String fecha){
        DateTimeFormatter formatoDia = DateTimeFormatter
                .ofPattern("EEEE")
                .withLocale(new Locale("es", "AR"));
        LocalDate fechaLocal = LocalDate.parse(fecha);
        return fechaLocal.format(formatoDia);
    }

    @Override
    public Medico consultarMedicoPorEmail(String username) {
        return repositorioMedico.obtenerMedicoPorEmail(username);
    }

}
