package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.CitaConsultorio;
import ar.edu.unlam.tallerweb1.modelo.CitaDomicilio;
import ar.edu.unlam.tallerweb1.modelo.datos.DatosCitaDomicilio;

import java.util.List;

public interface ServicioPaciente {
    List<CitaConsultorio> getCitaConsultorio(String email);
    List<DatosCitaDomicilio> getCitasDomicilioPend(String email);
}
