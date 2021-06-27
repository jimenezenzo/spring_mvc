package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Paciente;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.datos.DatosAltaUsuario;

// Interface que define los metodos del Servicio de Usuarios.
public interface ServicioUsuario {

	Usuario consultarUsuarioEmail(String email);

	void registrarPaciente(Paciente paciente);

	void altaUsuario(DatosAltaUsuario datosAltaUsuario);
}
