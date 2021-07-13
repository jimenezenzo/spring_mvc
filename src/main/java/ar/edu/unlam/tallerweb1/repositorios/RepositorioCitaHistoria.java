package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.CitaHistoria;

public interface RepositorioCitaHistoria {

    CitaHistoria citaHistoriaById(Long idCita);

    void updateCitaHistoria(CitaHistoria citaHistoria);
}
