package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Persona;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioPersonaImpl implements RepositorioPersona{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioPersonaImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Persona consultarPersonaPorTipoYNumero(String tipo, String numero) {
        final Session session = sessionFactory.getCurrentSession();
        return (Persona) session.createCriteria(Persona.class)
                .add(Restrictions.eq("tipoDocumento", tipo))
                .add(Restrictions.eq("numeroDocumento", numero))
                .uniqueResult();
    }
}
