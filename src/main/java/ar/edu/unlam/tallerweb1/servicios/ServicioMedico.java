package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Medico;

import java.util.List;

public interface ServicioMedico {

    List<String> getHorariosDia(Long medico, String fecha);

    List<Medico> obtenerMedicosTodos();
}
