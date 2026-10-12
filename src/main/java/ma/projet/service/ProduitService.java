package ma.projet.service;

import ma.projet.classes.Categorie;
import ma.projet.classes.Produit;
import ma.projet.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class ProduitService extends AbstractService<Produit> {
    public ProduitService() {
        super(Produit.class);
    }

    public List<Produit> findByCategorie(Categorie c) {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createNamedQuery("Produit.findByCategorie", Produit.class)
                    .setParameter("categorie", c)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Produit> findBetweenDates(Date d1, Date d2) {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Produit p WHERE p.dateAchat BETWEEN :d1 AND :d2", Produit.class)
                    .setParameter("d1", d1)
                    .setParameter("d2", d2)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Produit> findByPrixSuperieur(float prix) {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createNamedQuery("Produit.findByPrixSuperieur", Produit.class)
                    .setParameter("prix", prix)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
