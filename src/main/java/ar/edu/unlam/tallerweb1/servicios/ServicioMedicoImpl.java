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
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServicioMedicoImpl implements ServicioMedico{

    private RepositorioMedico repositorioMedico;


    @Autowired
    public ServicioMedicoImpl(RepositorioMedico repositorioMedico){
        this.repositorioMedico = repositorioMedico;

    }

    @Override
    public List getHorariosDia(Long medico, String fecha) {
        String dia = this.formatearFecha(fecha);

        Agenda agenda = this.repositorioMedico.getDiaAgenda(medico, dia);
        if (agenda == null || !agenda.getActivo() || agenda.getGuardia()){
            return Collections.emptyList();
        }

        List<CitaConsultorio> citasDeLaFecha = this.repositorioMedico.obtenerCitasPorFechaMedicoId(medico, LocalDate.parse(fecha));
        List<String> horariosNoDisponibles = citasDeLaFecha.stream()
                .map(cita -> cita.getHora().toString())
                .collect(Collectors.toList());

        return this.crearIntervalos(agenda.getHoraDesde(), agenda.getHoraHasta(), horariosNoDisponibles);
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
        List<Cita> citas;

        String dia = this.formatearFecha(LocalDate.now().toString());

        Agenda agenda = this.repositorioMedico.getDiaAgenda(medico.getId(), dia);
        if (agenda.getGuardia()){
            citas = this.repositorioMedico.obtenerCitasDomicilioPorFecha(medico, LocalDateTime.now());
            return citas.stream()
                    .filter(cita -> cita.getFechaRegistro().toLocalDate().toString().equals(LocalDate.now().toString()))
                    .collect(Collectors.toList());
        }

        return this.repositorioMedico.obtenerCitasConsultorioPorFecha(medico, LocalDate.now());
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

        agenda.setActivo(agenda.getActivo() != null);
        agenda.setGuardia(agenda.getGuardia() != null);

        this.repositorioMedico.actualizarAgenda(agenda);
    }

    @Override
    public Medico consultarMedicoPorEmail(String username) {
        return repositorioMedico.obtenerMedicoPorEmail(username);
    }

    private String formatearFecha(String fecha){
        DateTimeFormatter formatoDia = DateTimeFormatter
                .ofPattern("EEEE")
                .withLocale(new Locale("es", "AR"));

        return LocalDate.parse(fecha).format(formatoDia);
    }

    private List crearIntervalos(LocalTime intervaloInicial, LocalTime intervaloFinal, List horariosNoDisponibles){
        List<String> intervaloAgenda = new ArrayList<>();

        while (intervaloInicial.isBefore(intervaloFinal)){
            if (!horariosNoDisponibles.contains(intervaloInicial.toString()))
                intervaloAgenda.add(intervaloInicial.toString());

            intervaloInicial = intervaloInicial.plusMinutes(40);
        }

        return intervaloAgenda;
    }
}
