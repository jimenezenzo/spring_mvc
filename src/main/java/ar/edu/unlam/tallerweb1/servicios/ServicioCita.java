package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.excepciones.CrearCitaError;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Medico;
import ar.edu.unlam.tallerweb1.modelo.datos.DatosCitaConsultio;
import ar.edu.unlam.tallerweb1.modelo.datos.DatosCitaDomicilio;

import java.util.List;

public interface ServicioCita {
    void registrarCitaConsultorio(DatosCitaConsultio datosCitaConsultio);

    List<Especialidad> allEspecialidades();

    List medicosByEspecialidad(Long idEspecialidad);
}
