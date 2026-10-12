package ma.projet.service;

import ma.projet.classes.Employe;
import ma.projet.classes.Tache;
import ma.projet.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class EmployeService extends AbstractService<Employe> {
    public EmployeService() {
        super(Employe.class);
    }

    public List<Tache> findTachesByEmploye(Employe e) {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT t FROM Tache t WHERE t.employe = :emp", Tache.class)
                    .setParameter("emp", e)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
