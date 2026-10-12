package ma.projet.service;

import ma.projet.classes.Commande;
import ma.projet.classes.LigneCommande;
import ma.projet.classes.Produit;
import ma.projet.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class CommandeService extends AbstractService<Commande> {
    public CommandeService() {
        super(Commande.class);
    }

    public List<Produit> findProduitsByCommande(Commande commande) {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT lc.produit FROM LigneCommande lc WHERE lc.commande = :commande", Produit.class)
                    .setParameter("commande", commande)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
