package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.excepciones.EmailPacienteException;
import ar.edu.unlam.tallerweb1.excepciones.PacienteNoEncontradoException;
import ar.edu.unlam.tallerweb1.excepciones.PacienteRegistradoException;
import ar.edu.unlam.tallerweb1.modelo.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

// Implelemtacion del Servicio de usuarios, la anotacion @Service indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.servicios
// para encontrar esta clase.
// La anotacion @Transactional indica que se debe iniciar una transaccion de base de datos ante la invocacion de cada metodo del servicio,
// dicha transaccion esta asociada al transaction manager definido en el archivo spring-servlet.xml y el mismo asociado al session factory definido
// en hibernateCOntext.xml. De esta manera todos los metodos de cualquier dao invocados dentro de un servicio se ejecutan en la misma transaccion
@Service("servicioLogin")
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {

	@Autowired
	private RepositorioUsuario repositorioUsuario;

	@Override
	public Usuario consultarUsuarioEmail(String email) {
		return repositorioUsuario.userByEmail(email);
	}

	@Override
	public void registrarPaciente(Paciente paciente){
		Paciente pacienteEncontrado = this.repositorioUsuario.obtenerPacientePorNumeroAfiliado(paciente.getNumeroAfiliado());
		if (pacienteEncontrado == null)
			throw new PacienteNoEncontradoException();

		if (!pacienteEncontrado.getPassword().isEmpty())
			throw new PacienteRegistradoException();

		if (!pacienteEncontrado.getEmail().equals(paciente.getEmail()))
			throw new EmailPacienteException();

		pacienteEncontrado.setPassword(new BCryptPasswordEncoder().encode(paciente.getPassword()));
		this.repositorioUsuario.registrarPaciente(pacienteEncontrado);
	}

}
