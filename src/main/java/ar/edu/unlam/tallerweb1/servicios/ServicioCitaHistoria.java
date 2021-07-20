package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.CitaHistoria;

public interface ServicioCitaHistoria {
    CitaHistoria citaHistoriaById(Long idCita);

    void updateCitaHistoria(CitaHistoria citaHistoria);

    void observarCitaConsultorio(Long idCita, String observacion, String estado);
}
