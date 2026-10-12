package ma.projet.test;

import ma.projet.classes.*;
import ma.projet.service.*;
import ma.projet.util.HibernateUtil;
import org.junit.jupiter.api.*;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EvaluationIntegrationTest {

    private static CategorieService categorieService;
    private static ProduitService produitService;
    private static EmployeService employeService;
    private static ProjetService projetService;
    private static TacheService tacheService;
    private static HommeService hommeService;
    private static FemmeService femmeService;
    private static MariageService mariageService;

    @BeforeAll
    public static void setUp() {
        categorieService = new CategorieService();
        produitService = new ProduitService();
        employeService = new EmployeService();
        projetService = new ProjetService();
        tacheService = new TacheService();
        hommeService = new HommeService();
        femmeService = new FemmeService();
        mariageService = new MariageService();
    }

    @AfterAll
    public static void tearDown() {
        HibernateUtil.shutdown();
    }

    @Test
    @Order(1)
    public void testExercice1Stock() {
        Categorie cat = new Categorie("TEST-CAT", "Hardware");
        assertTrue(categorieService.create(cat));
        assertNotNull(cat.getId());

        Produit p = new Produit("SSD NVMe 1TB", 950.0f, new Date(), cat);
        assertTrue(produitService.create(p));
        assertNotNull(p.getId());

        List<Produit> list = produitService.findByCategorie(cat);
        assertFalse(list.isEmpty());
        assertEquals("SSD NVMe 1TB", list.get(0).getReference());
    }

    @Test
    @Order(2)
    public void testExercice2Projets() {
        Employe emp = new Employe("Moussaoui", "Moad", "0600000000");
        assertTrue(employeService.create(emp));

        Projet proj = new Projet("Architecture Microservices", new Date(), new Date());
        assertTrue(projetService.create(proj));

        Tache t = new Tache("Configuration JPA", new Date(), new Date(), 3000.0, proj, emp);
        assertTrue(tacheService.create(t));

        List<Tache> taches = employeService.findTachesByEmploye(emp);
        assertEquals(1, taches.size());
        assertEquals("Configuration JPA", taches.get(0).getNom());
    }

    @Test
    @Order(3)
    public void testExercice3EtatCivil() {
        Homme h = new Homme("Mansouri", "Ali", "0612345678", "Rabat", new Date());
        Femme f = new Femme("Bennani", "Kenza", "0687654321", "Rabat", new Date());
        assertTrue(hommeService.create(h));
        assertTrue(femmeService.create(f));

        Mariage m = new Mariage(new Date(), null, 2, h, f);
        assertTrue(mariageService.create(m));

        List<Mariage> mariages = hommeService.findMariages(h);
        assertEquals(1, mariages.size());
    }
}
