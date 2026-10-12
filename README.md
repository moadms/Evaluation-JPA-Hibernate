# Évaluation — JPA & Hibernate (Cas d'études avancés)

**Auteur :** Moad Moussaoui (`moadms`)  
**Formation :** Ingénierie du Logiciel / Master Big Data & IA  
**Module :** Persistance des données avec JEE, Hibernate & JPA  

---

## 📋 Description du Projet
Ce projet répond aux exigences strictes de l'évaluation finale en mettant en œuvre les trois cas d'études obligatoires selon l'architecture logicielle standardisée en couches (**Classes Métier**, **DAO Générique & Spécifique**, **Couche Service Métier** et **Tests Automatisés JUnit 5**).

---

## 🏛️ Architecture Technique & Packages
Le projet adopte la structure normalisée demandée :
- `ma.projet.classes` : Modélisation des entités JPA avec annotations relationnelles (`@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@ManyToOne`, `@OneToMany`, `@Inheritance`).
  - **Exercice 1 (Gestion de Stock) :** `Categorie`, `Produit`, `Commande`, `LigneCommande`
  - **Exercice 2 (Gestion de Projets) :** `Projet`, `Tache`, `Employe`
  - **Exercice 3 (État Civil) :** `Personne` (abstraite, stratégie `JOINED`), `Homme`, `Femme`, `Mariage`
- `ma.projet.dao` : Interface `IDao<T>` définissant les opérations génériques CRUD.
- `ma.projet.service` : Implémentation générique `AbstractService<T>` avec gestion des transactions (`begin`, `commit`, `rollback`), et services spécialisés (`ProduitService`, `ProjetService`, `HommeService`, `FemmeService`, etc.).
- `ma.projet.util` : Singleton `HibernateUtil` assurant l'initialisation et la fermeture de l'unique `EntityManagerFactory`.
- `src/test/java/ma/projet/test` : Tests d'intégration automatisés avec JUnit 5 (`EvaluationIntegrationTest`).

---

## 💻 Preuve d'Exécution Console (Terminal CMD)

```text
================================================================================
      DEMARRAGE DU PROGRAMME PRINCIPAL - EVALUATION JPA HIBERNATE               
================================================================================

>>> [EXERCICE 1 : GESTION DE STOCK]
Categories creees : 2
Produits crees : 3
Produits dans la categorie Informatique : 
  - Ordinateur Portable Dell : 8500.0 DH
  - Ecran 27 pouces 4K : 2800.0 DH
Produits avec prix > 2000 DH : 2

>>> [EXERCICE 2 : GESTION DE PROJETS]
Employes crees : 2
Projets crees : 1
Taches assignees a Moussaoui Moad : 
  * Architecture & Modele JPA (Prix: 4500.0 DH)
  * Integration Services & Tests (Prix: 5200.0 DH)

>>> [EXERCICE 3 : ETAT CIVIL]
Hommes enregistres : 2
Femmes enregistrees : 2
Mariages enregistres : 2
Epouses de Benani entre 2010 et 2025 : 1

================================================================================
      EXECUTION DU LAB EVALUATION TERMINEE AVEC SUCCES A 100%                   
================================================================================
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

---

## 🧪 Tests Unitaires Automatisés (JUnit 5)

```text
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running ma.projet.test.EvaluationIntegrationTest
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 4.912 s
[INFO] 
[INFO] Results:
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```
