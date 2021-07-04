package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public class RepositorioMedicoImpl implements RepositorioMedico{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioMedicoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void registrarEspecialidad(Especialidad especialidad) {
        final Session session = sessionFactory.getCurrentSession();
        session.save(especialidad);
    }

    @Override
    public void registrarConsultorio(Consultorio consultorio) {
        final Session session = sessionFactory.getCurrentSession();
        session.save(consultorio);
    }

    @Override
    public Medico consultarMedicoPorId(Long medico) {
        return sessionFactory.getCurrentSession().get(Medico.class, medico);
    }

    @Override
    public Agenda getDiaAgenda(Long medico, String dia) {
        final Session session = sessionFactory.getCurrentSession();

        Medico userMedico = this.consultarMedicoPorId(medico);

        Criteria criteria = session.createCriteria(Agenda.class)
                .add(Restrictions.eq("dia", dia))
                .add(Restrictions.eq("medico", userMedico));

        return (Agenda) criteria.uniqueResult();
    }

    @Override
    public List<CitaConsultorio> obtenerCitasPorFechaMedicoId(Long medico, LocalDate fechaLocal) {
        final Session session = sessionFactory.getCurrentSession();

        Medico userMedico = session.get(Medico.class, medico);

        if (medico == null)
            return null;

        Criteria criteria = session.createCriteria(CitaConsultorio.class)
                .add(Restrictions.eq("medico", userMedico))
                .add(Restrictions.eq("fecha", fechaLocal))
                .addOrder(Order.asc("hora"));

        return criteria.list();
    }

    @Override
    public List<Medico> obtenerTodosLosMedicos() {
        final Session session = sessionFactory.getCurrentSession();
        return  session.createCriteria(Medico.class)
                .list();
    }

    @Override
    public List<CitaDomicilio> obtenerCitasDomicilioPorFechaMedicoId(Long idMedico, LocalDate fechaLocal) {
        return null;
    }

    @Override
    public List obtenerAgenda(String username) {
        final Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Agenda.class).
                createCriteria("medico")
                .add(Restrictions.eq("email", username));

        return criteria.list();
    }

    @Override
    public void actualizarAgenda(Agenda agenda) {
        final Session session = sessionFactory.getCurrentSession();
        session.update(agenda);
    }

    @Override
    public Medico obtenerMedicoPorEmail(String username) {
        final Session session = sessionFactory.getCurrentSession();

        return (Medico) session.createCriteria(Medico.class)
                .add(Restrictions.eq("email", username))
                .uniqueResult();
    }

    @Override
    public List obtenerCitasDomicilio(String username) {
        final Session session = sessionFactory.getCurrentSession();

        return session.createCriteria(CitaDomicilio.class)
                .createCriteria("medico")
                .add(Restrictions.eq("email", username))
                .list();
    }

    @Override
    public List obtenerCitasConsultorio(String username) {
        final Session session = sessionFactory.getCurrentSession();

        return session.createCriteria(CitaConsultorio.class)
                .createCriteria("medico")
                .add(Restrictions.eq("email", username))
                .list();
    }

}
