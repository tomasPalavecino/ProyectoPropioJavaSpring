package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Escuela;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioEscuela")
public class RepositorioEscuelaImpl implements RepositorioEscuela{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioEscuelaImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void guardar(Escuela escuela) {
        sessionFactory.getCurrentSession().save(escuela);
    }

    @Override
    public Escuela buscarEscuelaPorId(Long idEscuela) {
        return (Escuela) sessionFactory.getCurrentSession().createCriteria(Escuela.class)
                .add(Restrictions.eq("id", idEscuela)).uniqueResult();
    }

    @Override
    public List<Escuela> getEscuelas() {
        return sessionFactory.getCurrentSession().createQuery("FROM Escuela", Escuela.class).getResultList();
    }

    @Override
    public Escuela buscarEscuela(String nombre, String direccion, String localidad) {
        final Session session = sessionFactory.getCurrentSession();
        return (Escuela) session.createCriteria(Escuela.class)
                .add(Restrictions.eq("nombre", nombre))
                .add(Restrictions.eq("direccion", direccion))
                .add(Restrictions.eq("localidad", localidad))
                .uniqueResult();
    }
}
