package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.CitaHistoria;
import ar.edu.unlam.tallerweb1.modelo.datos.DatosCitaHistoria;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ServicioCitaHistoria {
    CitaHistoria citaHistoriaById(Long idCita);

    void updateCitaHistoria(CitaHistoria citaHistoria);

    void observarCitaConsultorio(DatosCitaHistoria datosCitaHistoria) throws IOException;

    void cancelarCitaPaciente(Long idCita);

    void descargarArchivo(String file, HttpServletResponse response) throws IOException;
}
