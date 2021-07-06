package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.CitaDomicilio;

public interface RepositorioCitaDomicilio {
    CitaDomicilio getCitaById(Long id);
    void registrarCitaDomicilio(CitaDomicilio citaDomicilio);
}
