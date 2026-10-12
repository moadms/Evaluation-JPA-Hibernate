package ma.projet.service;

import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class ProjetService extends AbstractService<Projet> {
    public ProjetService() {
        super(Projet.class);
    }

    public List<Tache> findTachesPlanifieesByProjet(Projet p) {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT t FROM Tache t WHERE t.projet = :proj", Tache.class)
                    .setParameter("proj", p)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
