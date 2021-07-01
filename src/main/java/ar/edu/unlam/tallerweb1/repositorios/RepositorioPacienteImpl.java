package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Cita;
import ar.edu.unlam.tallerweb1.modelo.Paciente;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class RepositorioPacienteImpl implements RepositorioPaciente{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioPacienteImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void registrarCita(Cita cita) {
        final Session session = sessionFactory.getCurrentSession();
        session.save(cita);
    }

    @Override
    public List obtenerTodasLasCitas(String email) {
        final Session session = sessionFactory.getCurrentSession();
        Paciente paciente = this.obtenerPacientePorEmail(email);

        Criteria criteria = session.createCriteria(Cita.class)
                .add(Restrictions.eq("paciente", paciente));

        return criteria.list();
    }

    @Override
    public Paciente obtenerPacientePorEmail(String email) {
        final Session session = sessionFactory.getCurrentSession();
        return (Paciente) session.createCriteria(Paciente.class)
                .add(Restrictions.eq("email", email))
                .uniqueResult();
    }
}
