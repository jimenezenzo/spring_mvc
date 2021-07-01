package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Medico;
import ar.edu.unlam.tallerweb1.modelo.Paciente;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.time.LocalTime;
import java.util.Optional;

// implelemtacion del repositorio de usuarios, la anotacion @Repository indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.dao
// para encontrar esta clase.
@Repository("repositorioUsuario")
public class RepositorioUsuarioImpl implements RepositorioUsuario {

	// Como todo repositorio maneja acciones de persistencia, normalmente estará inyectado el session factory de hibernate
	// el mismo está difinido en el archivo hibernateContext.xml
	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioUsuarioImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Usuario userByEmail(String email) {
		final Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.createCriteria(Usuario.class)
				.add(Restrictions.eq("email", email))
				.uniqueResult();
	}

	@Override
	public Paciente obtenerPacientePorNumeroAfiliado(String numero) {
		final Session session = sessionFactory.getCurrentSession();
		return (Paciente) session.createCriteria(Paciente.class)
				.add(Restrictions.eq("numeroAfiliado", numero))
				.uniqueResult();
	}

	@Override
	public void registrarPaciente(Paciente paciente) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(paciente);
	}

	@Override
	public void registrarAltaUsuario(Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(usuario);

		if (usuario instanceof Medico){
			Agenda lunes = new Agenda();
			lunes.setActivo(true);
			lunes.setHoraDesde(LocalTime.parse("08:00"));
			lunes.setHoraHasta(LocalTime.parse("16:00"));
			lunes.setDia("lunes");
			lunes.setMedico((Medico) usuario);
			session.save(lunes);

			Agenda martes = new Agenda();
			martes.setActivo(true);
			martes.setHoraDesde(LocalTime.parse("08:00"));
			martes.setHoraHasta(LocalTime.parse("16:00"));
			martes.setDia("martes");
			martes.setMedico((Medico) usuario);
			session.save(martes);

			Agenda miercoles = new Agenda();
			miercoles.setActivo(true);
			miercoles.setHoraDesde(LocalTime.parse("08:00"));
			miercoles.setHoraHasta(LocalTime.parse("16:00"));
			miercoles.setDia("miércoles");
			miercoles.setMedico((Medico) usuario);
			session.save(miercoles);

			Agenda jueves = new Agenda();
			jueves.setActivo(true);
			jueves.setHoraDesde(LocalTime.parse("08:00"));
			jueves.setHoraHasta(LocalTime.parse("16:00"));
			jueves.setDia("jueves");
			jueves.setMedico((Medico) usuario);
			session.save(jueves);

			Agenda viernes = new Agenda();
			viernes.setActivo(true);
			viernes.setHoraDesde(LocalTime.parse("08:00"));
			viernes.setHoraHasta(LocalTime.parse("16:00"));
			viernes.setDia("viernes");
			viernes.setMedico((Medico) usuario);
			session.save(viernes);

			Agenda sabado = new Agenda();
			sabado.setActivo(false);
			sabado.setHoraDesde(LocalTime.parse("08:00"));
			sabado.setHoraHasta(LocalTime.parse("16:00"));
			sabado.setDia("sábado");
			sabado.setMedico((Medico) usuario);
			session.save(sabado);

			Agenda domingo = new Agenda();
			domingo.setActivo(false);
			domingo.setHoraDesde(LocalTime.parse("08:00"));
			domingo.setHoraHasta(LocalTime.parse("16:00"));
			domingo.setDia("domingo");
			domingo.setMedico((Medico) usuario);
			session.save(domingo);
		}
	}

}
