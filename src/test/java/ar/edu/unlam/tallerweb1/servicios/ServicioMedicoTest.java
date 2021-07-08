package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.CitaConsultorio;
import ar.edu.unlam.tallerweb1.modelo.Medico;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioMedico;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class ServicioMedicoTest {

    private RepositorioMedico repositorioMedico;
    private ServicioMedico servicioMedico;

    @Before
    public void init() {
        repositorioMedico = mock(RepositorioMedico.class);
        servicioMedico = new ServicioMedicoImpl(repositorioMedico);
    }

    @Test
    public void testQueUnMedicoPuedaVerSuAgenda(){
        Medico medico = givenUsuarioMedico();
        List<Agenda> agenda = whenBuscoLaAgenda(medico.getEmail());
        thenLaAgendaDaExisto(agenda);
    }

    @Test
    public void testQueUnMedicoPuedaModificarSuAgenda(){
        Medico medico = givenUsuarioMedico();
        List<Agenda> agenda = whenBuscoLaAgenda(medico.getEmail());
        Agenda agendaModificada = whenModificoUnDiaEnLaAgenda(agenda, medico.getEmail());
        thenElDiaSeModificaConExito(agendaModificada);
    }

    @Test
    public void testQueSePuedaVerLosHorariosDisponiblesParaUnaCitaProgramada(){
        Medico medico = givenUsuarioMedico();
        List<Agenda> agenda = whenBuscoLaAgenda(medico.getEmail());
        List<String> horariosDisponibles = whenBuscoLosHorariosDisponibles(medico, agenda);
        thenHayHorariosDisponibles(horariosDisponibles);
    }

    @Test
    public void testQueElHorarioDeUnaCitaProgramadaNoEsteDisponible(){
        Medico medico = givenUsuarioMedico();
        List<Agenda> agenda = whenBuscoLaAgenda(medico.getEmail());
        List<String> horariosDisponibles = whenBuscoLosHorariosDisponibles(medico, agenda);
        thenElHorarioNoEstaDisponible(horariosDisponibles);
    }

    @Test
    public void testQueNoHayHorariosDisponibles(){
        Medico medico = givenUsuarioMedico();
        List<Agenda> agenda = whenBuscoLaAgenda(medico.getEmail());
        List<String> horariosDisponibles = whenBuscoLosHorariosDisponiblesLleno(medico, agenda);
        thenNoHayHorariosDisponibles(horariosDisponibles);
    }

    @Test
    public void testQueNoHayHorariosDisponiblesPorQueElMedicoEstaDeGuardia(){
        Medico medico = givenUsuarioMedico();
        List<Agenda> agenda = whenBuscoLaAgenda(medico.getEmail());
        List<String> horariosDisponibles =  whenBuscoLosHorariosDisponiblesLlenoPorGuardia(medico, agenda);
        thenNoHayHorariosDisponibles(horariosDisponibles);
    }

    private Medico givenUsuarioMedico() {
        Medico medico = new Medico();
        medico.setId(1L);
        medico.setEmail("pepe@pepe.com");
        medico.setRol("Medico");

        return  medico;
    }

    private List<Agenda> whenBuscoLaAgenda(String email) {
        Agenda agenda = new Agenda();
        agenda.setActivo(true);
        agenda.setDia("lunes");
        agenda.setGuardia(false);
        agenda.setHoraDesde(LocalTime.parse("08:00"));
        agenda.setHoraHasta(LocalTime.parse("16:00"));

        Agenda agenda1 = new Agenda();
        agenda1.setActivo(true);
        agenda1.setDia("martes");
        agenda1.setGuardia(false);
        agenda1.setHoraDesde(LocalTime.parse("10:00"));
        agenda1.setHoraHasta(LocalTime.parse("12:00"));

        Agenda agenda2 = new Agenda();
        agenda2.setActivo(true);
        agenda2.setDia("miércoles");
        agenda2.setGuardia(true);
        agenda2.setHoraDesde(LocalTime.parse("08:00"));
        agenda2.setHoraHasta(LocalTime.parse("16:00"));

        List<Agenda> agendaList = Arrays.asList(agenda, agenda1, agenda2);
        when(repositorioMedico.obtenerAgenda(email)).thenReturn(agendaList);

        return servicioMedico.getAgenda(email);
    }

    private Agenda whenModificoUnDiaEnLaAgenda(List<Agenda> agenda, String email) {
        Agenda agendaModificada = agenda.get(0);
        agendaModificada.setHoraDesde(LocalTime.parse("07:00"));

        servicioMedico.actualizarAgenda(agendaModificada, email);

        return agendaModificada;
    }

    private List<String> whenBuscoLosHorariosDisponibles(Medico medico, List<Agenda> agenda) {
        CitaConsultorio citaConsultorio = new CitaConsultorio();
        citaConsultorio.setMedico(medico);
        citaConsultorio.setHora(LocalTime.parse("08:40"));

        when(repositorioMedico.getDiaAgenda(medico.getId(), "lunes")).thenReturn(agenda.get(0));
        when(repositorioMedico.obtenerCitasPorFechaMedicoId(medico.getId(), LocalDate.parse("2021-07-12"))).thenReturn(Arrays.asList(citaConsultorio));

        return servicioMedico.getHorariosDia(medico.getId(), "2021-07-12");
    }

    private List<String> whenBuscoLosHorariosDisponiblesLleno(Medico medico, List<Agenda> agenda) {
        CitaConsultorio citaConsultorio = new CitaConsultorio();
        citaConsultorio.setMedico(medico);
        citaConsultorio.setHora(LocalTime.parse("10:00"));

        CitaConsultorio citaConsultorio1 = new CitaConsultorio();
        citaConsultorio1.setMedico(medico);
        citaConsultorio1.setHora(LocalTime.parse("10:40"));

        CitaConsultorio citaConsultorio2 = new CitaConsultorio();
        citaConsultorio2.setMedico(medico);
        citaConsultorio2.setHora(LocalTime.parse("11:20"));

        CitaConsultorio citaConsultorio3 = new CitaConsultorio();
        citaConsultorio3.setMedico(medico);
        citaConsultorio3.setHora(LocalTime.parse("12:00"));

        when(repositorioMedico.getDiaAgenda(medico.getId(), "martes")).thenReturn(agenda.get(1));
        when(repositorioMedico.obtenerCitasPorFechaMedicoId(medico.getId(), LocalDate.parse("2021-07-13"))).thenReturn(Arrays.asList(citaConsultorio, citaConsultorio1, citaConsultorio2, citaConsultorio3));

        return servicioMedico.getHorariosDia(medico.getId(), "2021-07-13");
    }

    private List<String> whenBuscoLosHorariosDisponiblesLlenoPorGuardia(Medico medico, List<Agenda> agenda) {
        when(repositorioMedico.getDiaAgenda(medico.getId(), "miércoles")).thenReturn(agenda.get(2));
        verify(repositorioMedico, never()).obtenerCitasPorFechaMedicoId(medico.getId(), LocalDate.parse("2021-07-14"));

        return servicioMedico.getHorariosDia(medico.getId(), "2021-07-14");
    }

    private void thenLaAgendaDaExisto(List<Agenda> agenda) {
        assertThat(agenda.size()).isEqualTo(3);
    }

    private void thenElDiaSeModificaConExito(Agenda agenda) {
        verify(repositorioMedico, times(1)).actualizarAgenda(agenda);
    }

    private void thenHayHorariosDisponibles(List<String> horariosDisponibles) {
        assertThat(horariosDisponibles).isNotEmpty();
    }

    private void thenElHorarioNoEstaDisponible(List<String> horariosDisponibles) {
        assertThat(horariosDisponibles.contains("08:40")).isFalse();
    }

    private void thenNoHayHorariosDisponibles(List<String> horariosDisponibles) {
        assertThat(horariosDisponibles).isEmpty();
    }
}
