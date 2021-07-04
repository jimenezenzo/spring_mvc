package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.CitaDomicilio;
import ar.edu.unlam.tallerweb1.modelo.Medico;
import java.util.List;

public interface ServicioMedico {

    List<String> getHorariosDia(Long medico, String fecha);

    List<Medico> obtenerMedicosTodos();

    List<CitaDomicilio> obtenerCitasDomicilio(String username);

    List getAgenda(String username);

    void actualizarAgenda(Agenda agenda, String username);

    Medico consultarMedicoPorEmail(String username);

    List obtenerCitasConsultorio(String username);
}
