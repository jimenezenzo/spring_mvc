package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Persona;

public interface RepositorioPersona {

    Persona consultarPersonaPorTipoYNumero(String tipo, String numero);
}
