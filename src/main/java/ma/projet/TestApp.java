package ma.projet;

import ma.projet.classes.*;
import ma.projet.service.*;
import ma.projet.util.HibernateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestApp {
    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("      DEMARRAGE DU PROGRAMME PRINCIPAL - EVALUATION JPA HIBERNATE               ");
        System.out.println("================================================================================");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // -------------------------------------------------------------
            // EXERCICE 1 : GESTION DE STOCK
            // -------------------------------------------------------------
            System.out.println("\n>>> [EXERCICE 1 : GESTION DE STOCK]");
            CategorieService catService = new CategorieService();
            ProduitService prodService = new ProduitService();
            CommandeService cmdService = new CommandeService();

            Categorie c1 = new Categorie("CAT-INFO", "Informatique");
            Categorie c2 = new Categorie("CAT-BUR", "Bureautique");
            catService.create(c1);
            catService.create(c2);

            Produit p1 = new Produit("Ordinateur Portable Dell", 8500.0f, sdf.parse("2026-01-15"), c1);
            Produit p2 = new Produit("Ecran 27 pouces 4K", 2800.0f, sdf.parse("2026-02-10"), c1);
            Produit p3 = new Produit("Imprimante Multifonction HP", 1450.0f, sdf.parse("2026-03-05"), c2);
            prodService.create(p1);
            prodService.create(p2);
            prodService.create(p3);

            Commande cmd = new Commande(new Date());
            cmdService.create(cmd);

            System.out.println("Categories creees : " + catService.findAll().size());
            System.out.println("Produits crees : " + prodService.findAll().size());

            List<Produit> prodsCat = prodService.findByCategorie(c1);
            System.out.println("Produits dans la categorie " + c1.getLibelle() + " : ");
            for (Produit p : prodsCat) {
                System.out.println("  - " + p.getReference() + " : " + p.getPrix() + " DH");
            }

            List<Produit> prodsPrix = prodService.findByPrixSuperieur(2000.0f);
            System.out.println("Produits avec prix > 2000 DH : " + prodsPrix.size());

            // -------------------------------------------------------------
            // EXERCICE 2 : GESTION DE PROJETS
            // -------------------------------------------------------------
            System.out.println("\n>>> [EXERCICE 2 : GESTION DE PROJETS]");
            EmployeService empService = new EmployeService();
            ProjetService projService = new ProjetService();
            TacheService tacheService = new TacheService();

            Employe e1 = new Employe("Moussaoui", "Moad", "0612345678");
            Employe e2 = new Employe("Alami", "Karim", "0698765432");
            empService.create(e1);
            empService.create(e2);

            Projet proj = new Projet("Plateforme E-Commerce Cloud", sdf.parse("2026-01-01"), sdf.parse("2026-06-30"));
            projService.create(proj);

            Tache t1 = new Tache("Architecture & Modele JPA", sdf.parse("2026-01-05"), sdf.parse("2026-01-25"), 4500.0, proj, e1);
            Tache t2 = new Tache("Integration Services & Tests", sdf.parse("2026-02-01"), sdf.parse("2026-02-28"), 5200.0, proj, e1);
            Tache t3 = new Tache("Securisation OAuth2 & JWT", sdf.parse("2026-03-01"), sdf.parse("2026-03-20"), 3800.0, proj, e2);
            tacheService.create(t1);
            tacheService.create(t2);
            tacheService.create(t3);

            System.out.println("Employes crees : " + empService.findAll().size());
            System.out.println("Projets crees : " + projService.findAll().size());

            List<Tache> tachesMoad = empService.findTachesByEmploye(e1);
            System.out.println("Taches assignees a " + e1.getNom() + " " + e1.getPrenom() + " : ");
            for (Tache t : tachesMoad) {
                System.out.println("  * " + t.getNom() + " (Prix: " + t.getPrix() + " DH)");
            }

            // -------------------------------------------------------------
            // EXERCICE 3 : ETAT CIVIL
            // -------------------------------------------------------------
            System.out.println("\n>>> [EXERCICE 3 : ETAT CIVIL]");
            HommeService hommeService = new HommeService();
            FemmeService femmeService = new FemmeService();
            MariageService mariageService = new MariageService();

            Homme h1 = new Homme("Benani", "Taha", "0611223344", "Casablanca", sdf.parse("1988-04-12"));
            Homme h2 = new Homme("Idrissi", "Omar", "0655667788", "Rabat", sdf.parse("1992-09-20"));
            hommeService.create(h1);
            hommeService.create(h2);

            Femme f1 = new Femme("Chraibi", "Salma", "0622334455", "Casablanca", sdf.parse("1990-06-18"));
            Femme f2 = new Femme("Tazi", "Fatima", "0677889900", "Fes", sdf.parse("1994-11-25"));
            femmeService.create(f1);
            femmeService.create(f2);

            Mariage m1 = new Mariage(sdf.parse("2015-08-10"), null, 2, h1, f1);
            Mariage m2 = new Mariage(sdf.parse("2020-03-15"), null, 1, h2, f2);
            mariageService.create(m1);
            mariageService.create(m2);

            System.out.println("Hommes enregistres : " + hommeService.findAll().size());
            System.out.println("Femmes enregistrees : " + femmeService.findAll().size());
            System.out.println("Mariages enregistres : " + mariageService.findAll().size());

            List<Femme> epouses = hommeService.findEpousesBetweenDates(h1, sdf.parse("2010-01-01"), sdf.parse("2025-01-01"));
            System.out.println("Epouses de " + h1.getNom() + " entre 2010 et 2025 : " + epouses.size());

            System.out.println("\n================================================================================");
            System.out.println("      EXECUTION DU LAB EVALUATION TERMINEE AVEC SUCCES A 100%                   ");
            System.out.println("================================================================================");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
