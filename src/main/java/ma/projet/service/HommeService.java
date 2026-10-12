package ma.projet.service;

import ma.projet.classes.Femme;
import ma.projet.classes.Homme;
import ma.projet.classes.Mariage;
import ma.projet.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class HommeService extends AbstractService<Homme> {
    public HommeService() {
        super(Homme.class);
    }

    public List<Femme> findEpousesBetweenDates(Homme h, Date d1, Date d2) {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT m.femme FROM Mariage m WHERE m.homme = :h AND m.dateDebut BETWEEN :d1 AND :d2", Femme.class)
                    .setParameter("h", h)
                    .setParameter("d1", d1)
                    .setParameter("d2", d2)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Mariage> findMariages(Homme h) {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT m FROM Mariage m WHERE m.homme = :h", Mariage.class)
                    .setParameter("h", h)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
