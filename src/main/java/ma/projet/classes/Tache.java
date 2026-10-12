package ma.projet.classes;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "taches")
@NamedQueries({
    @NamedQuery(name = "Tache.findByPrixSuperieur", query = "SELECT t FROM Tache t WHERE t.prix > :prix")
})
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 150)
    private String nom;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    private Date dateFin;

    @Column(nullable = false)
    private double prix;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "projet_id", nullable = false)
    private Projet projet;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employe_id")
    private Employe employe;

    public Tache() {}

    public Tache(String nom, Date dateDebut, Date dateFin, double prix, Projet projet, Employe employe) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prix = prix;
        this.projet = projet;
        this.employe = employe;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }

    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public Projet getProjet() { return projet; }
    public void setProjet(Projet projet) { this.projet = projet; }

    public Employe getEmploye() { return employe; }
    public void setEmploye(Employe employe) { this.employe = employe; }

    @Override
    public String toString() {
        return "Tache{" + "id=" + id + ", nom='" + nom + '\'' + ", prix=" + prix + '}';
    }
}
