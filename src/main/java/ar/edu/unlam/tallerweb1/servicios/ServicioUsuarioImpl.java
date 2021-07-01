package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.configuraciones.SendEmail;
import ar.edu.unlam.tallerweb1.excepciones.*;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.modelo.datos.DatosAltaUsuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

import java.time.LocalDate;

// Implelemtacion del Servicio de usuarios, la anotacion @Service indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.servicios
// para encontrar esta clase.
// La anotacion @Transactional indica que se debe iniciar una transaccion de base de datos ante la invocacion de cada metodo del servicio,
// dicha transaccion esta asociada al transaction manager definido en el archivo spring-servlet.xml y el mismo asociado al session factory definido
// en hibernateCOntext.xml. De esta manera todos los metodos de cualquier dao invocados dentro de un servicio se ejecutan en la misma transaccion
@Service("servicioLogin")
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {

	private RepositorioUsuario repositorioUsuario;
	private RepositorioPersona repositorioPersona;

	private final String urlRegistroPaciente = "http://localhost:8080/proyecto_limpio_spring_war_exploded/registro";

	@Autowired
	public ServicioUsuarioImpl(RepositorioUsuario repositorioUsuario, RepositorioPersona repositorioPersona){
		this.repositorioUsuario = repositorioUsuario;
		this.repositorioPersona = repositorioPersona;
	}

	public ServicioUsuarioImpl(RepositorioUsuario repositorioUsuario) {
		this.repositorioUsuario = repositorioUsuario;
	}

	@Override
	public Usuario consultarUsuarioEmail(String email) {
		return repositorioUsuario.userByEmail(email);
	}

	@Override
	public void registrarPaciente(Paciente paciente){
		Paciente pacienteEncontrado = this.repositorioUsuario.obtenerPacientePorNumeroAfiliado(paciente.getNumeroAfiliado());
		if (pacienteEncontrado == null)
			throw new PacienteNoEncontradoException("El numero de afiliado no es correcto");

		if (pacienteEncontrado.getPassword() != null)
			throw new PacienteRegistradoException("El afiliado ya se encuentra registrado");

		if (!pacienteEncontrado.getEmail().equals(paciente.getEmail()))
			throw new EmailPacienteException("El email ingresado no se encuentra en el sistema");

		pacienteEncontrado.setPassword(new BCryptPasswordEncoder().encode(paciente.getPassword()));
		this.repositorioUsuario.registrarPaciente(pacienteEncontrado);


	}

	public void enviarEmailDeRegistroExitoso(Paciente paciente) {
		String subject = "Registro exitoso La clinica";

		String cuerpoDelEmail = "Le informamos que su numero de afiliado es: " + paciente.getNumeroAfiliado() +
				" Por favor utilicelo para darse de alta como usuario en el siguiente link: " + urlRegistroPaciente;

		String email = paciente.getEmail();
		SendEmail sendEmail = new SendEmail();
		sendEmail.SendSimpleEmail(subject, cuerpoDelEmail, email);
	}

	@Override
	public void altaUsuario(DatosAltaUsuario datosAltaUsuario) {
		if (this.repositorioPersona.consultarPersonaPorTipoYNumero(datosAltaUsuario.getTipoDocumento(), datosAltaUsuario.getNumeroDocumento()) != null)
			throw new UsuarioRegistradoException("El usuario ya se encuentra registrado");

		if (this.repositorioUsuario.userByEmail(datosAltaUsuario.getEmail()) != null)
			throw new EmailExistenteException("El email ya se encuentra en uso");

		Persona persona = new Persona();
		persona.setTipoDocumento(datosAltaUsuario.getTipoDocumento());
		persona.setNumeroDocumento(datosAltaUsuario.getNumeroDocumento());
		persona.setNombre(datosAltaUsuario.getNombre());
		persona.setApellido(datosAltaUsuario.getApellido());
		persona.setSexo(datosAltaUsuario.getSexo());
		persona.setFechaNacimiento(LocalDate.parse(datosAltaUsuario.getFechaNacimiento()));

		if (datosAltaUsuario.getRol().equals("Paciente")){
			Paciente paciente = new Paciente();
			paciente.setPersona(persona);
			paciente.setEmail(datosAltaUsuario.getEmail());
			paciente.setRol(datosAltaUsuario.getRol());
			paciente.setNumeroAfiliado(datosAltaUsuario.getNumeroAfiliado());
			this.repositorioUsuario.registrarAltaUsuario(paciente);
			this.enviarEmailDeRegistroExitoso(paciente);
		}
		else{
			Medico medico = new Medico();
			medico.setPersona(persona);
			medico.setEmail(datosAltaUsuario.getEmail());
			medico.setRol(datosAltaUsuario.getRol());
			medico.setMatricula(datosAltaUsuario.getMatricula());
			medico.setGuardia(false);
			this.repositorioUsuario.registrarAltaUsuario(medico);
		}
	}

}
