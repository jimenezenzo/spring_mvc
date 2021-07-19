package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.CitaDomicilio;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RepositorioCitaDomicilioImpl implements RepositorioCitaDomicilio {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioCitaDomicilioImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public CitaDomicilio getCitaById(Long id) {
         return sessionFactory.getCurrentSession().get(CitaDomicilio.class, id);
    }

    @Override
    public void registrarCitaDomicilio(CitaDomicilio citaDomicilio){
        sessionFactory.getCurrentSession().save(citaDomicilio);
    }

    @Override
    public void actualizarCitaDomicilio(CitaDomicilio citaDomicilio){
        sessionFactory.getCurrentSession().update(citaDomicilio);
    }

}
