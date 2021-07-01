package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Cita;
import ar.edu.unlam.tallerweb1.modelo.Paciente;

import java.util.List;

public interface RepositorioPaciente {

    void registrarCita(Cita cita);

    List<Cita> obtenerTodasLasCitas(String email);

    Paciente obtenerPacientePorEmail(String email);
}
