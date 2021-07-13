package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.CitaHistoria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RepositorioCitaHistoriaImpl implements RepositorioCitaHistoria{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioCitaHistoriaImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public CitaHistoria citaHistoriaById(Long idCita) {
        return sessionFactory.getCurrentSession().get(CitaHistoria.class, idCita);
    }

    @Override
    public void updateCitaHistoria(CitaHistoria citaHistoria) {
        sessionFactory.getCurrentSession().update(citaHistoria);
    }
}
