package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.*;

import java.time.LocalDate;
import java.util.List;

public interface RepositorioMedico {
    void registrarEspecialidad(Especialidad especialidad);

    void registrarConsultorio(Consultorio consultorio);

    Medico consultarMedicoPorId(Long medico);

    Agenda getDiaAgenda(Long medico, String dia);

    List<CitaConsultorio> obtenerCitasPorFechaMedicoId(Long medico, LocalDate fechaLocal);

    List<Medico> obtenerTodosLosMedicos();
}
