package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.CitaConsultorio;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Medico;
import ar.edu.unlam.tallerweb1.modelo.Paciente;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioCitaImpl implements RepositorioCita{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioCitaImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Especialidad especialidadById(Long id) {
        return sessionFactory.getCurrentSession().get(Especialidad.class, id);
    }

    @Override
    public List allEspecialidad() {
        return sessionFactory.getCurrentSession().createCriteria(Especialidad.class)
                .list();
    }

    @Override
    public void registrarCitaConsultorio(CitaConsultorio citaConsultorio) {
        sessionFactory.getCurrentSession().save(citaConsultorio);
    }

    @Override
    public List obtenerCitasConsultorioPaciente(Paciente paciente) {
        final Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(CitaConsultorio.class)
                .add(Restrictions.eq("paciente", paciente))
                .addOrder(Order.desc("fechaRegistro"));

        return criteria.list();
    }

    @Override
    public List medicoByEspecialidad(Long idEspecialidad) {
        final Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Medico.class)
                .createCriteria("especialidades")
                .add(Restrictions.eq("id", idEspecialidad))
                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

        return criteria.list();
    }


}
