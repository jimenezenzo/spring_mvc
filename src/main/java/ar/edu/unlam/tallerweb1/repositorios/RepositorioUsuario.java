package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Paciente;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.Optional;

// Interface que define los metodos del Repositorio de Usuarios.
public interface RepositorioUsuario {

	Usuario userByEmail (String email);

	Paciente obtenerPacientePorNumeroAfiliado(String numero);

	void registrarPaciente(Paciente paciente);
}
