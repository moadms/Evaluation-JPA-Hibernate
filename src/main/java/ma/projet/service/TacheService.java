package ma.projet.service;

import ma.projet.classes.Tache;
import ma.projet.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class TacheService extends AbstractService<Tache> {
    public TacheService() {
        super(Tache.class);
    }

    public List<Tache> findByPrixSuperieur(double prix) {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createNamedQuery("Tache.findByPrixSuperieur", Tache.class)
                    .setParameter("prix", prix)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
