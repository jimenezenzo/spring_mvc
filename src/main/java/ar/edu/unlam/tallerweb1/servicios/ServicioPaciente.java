package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.CitaConsultorio;

import java.util.List;

public interface ServicioPaciente {
    List<CitaConsultorio> getCitaConsultorio(String email);
}
