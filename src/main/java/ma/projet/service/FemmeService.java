package ma.projet.service;

import ma.projet.classes.Femme;
import ma.projet.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class FemmeService extends AbstractService<Femme> {
    public FemmeService() {
        super(Femme.class);
    }

    public Long countEnfantsBetweenDates(Femme f, Date d1, Date d2) {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        try {
            Long count = em.createQuery("SELECT SUM(m.nbrEnfants) FROM Mariage m WHERE m.femme = :f AND m.dateDebut BETWEEN :d1 AND :d2", Long.class)
                    .setParameter("f", f)
                    .setParameter("d1", d1)
                    .setParameter("d2", d2)
                    .getSingleResult();
            return count != null ? count : 0L;
        } finally {
            em.close();
        }
    }

    public List<Femme> findFemmesMarieesDeuxFoisOuPlus() {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT m.femme FROM Mariage m GROUP BY m.femme HAVING COUNT(m) >= 2", Femme.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
