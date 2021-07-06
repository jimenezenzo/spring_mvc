package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.datos.DatosCitaConsultio;

import java.util.List;

public interface ServicioCita {
    void registrarCitaConsultorio(DatosCitaConsultio datosCitaConsultio);

    List<Especialidad> allEspecialidades();

    List medicosByEspecialidad(Long idEspecialidad);
}
