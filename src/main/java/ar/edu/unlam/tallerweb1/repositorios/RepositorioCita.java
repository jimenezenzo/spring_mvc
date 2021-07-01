package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.CitaConsultorio;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Paciente;

import java.util.List;

public interface RepositorioCita {

    Especialidad especialidadById(Long id);

    List<Especialidad> allEspecialidad();

    void registrarCitaConsultorio(CitaConsultorio citaConsultorio);

    List<CitaConsultorio> obtenerCitasConsultorioPaciente(Paciente paciente);

    List medicoByEspecialidad(Long idEspecialidad);
}
