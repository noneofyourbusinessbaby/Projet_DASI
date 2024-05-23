/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.metier.model;

import fr.insalyon.dasi.test.tp1.metier.model.personne.Eleve;
import fr.insalyon.dasi.test.tp1.metier.model.personne.Intervenant;
import fr.insalyon.dasi.test.tp1.metier.model.seance.Bilan;
import fr.insalyon.dasi.test.tp1.metier.model.seance.Comprehension;
import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author khoupeurt
 */

@Entity
public class Seance implements Serializable {

    public enum SeanceStatus {
        EnAttente, EnCours, Terminee
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = true)
    private String lien;

    @Column(nullable = false)
    private String description;

    @ManyToOne(optional = false)
    private Matiere matiere;

    @Column()
    private Date debut;

    @Column()
    private Date fin;

    @Embedded
    private Bilan bilan;

    @Embedded
    private Comprehension comprehension;

    @ManyToOne(optional = false)
    private Eleve eleve;

    @ManyToOne(optional = true)
    private Intervenant intervenant;

    public Seance() {
        this.intervenant = null;

        this.bilan = null;
        this.comprehension = null;
    }

    public Seance(Eleve eleve, Matiere matiere, String description) {
        this.eleve = eleve;

        this.matiere = matiere;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Seance{" + "lien=" + getLien() + ", matiere=" + getMatiere() + ", description=" + getDescription()
                + ", debut=" + getDebut() + ", fin=" + getFin() + ", status=" + getStatus() + ", eleve=" + getEleve()
                + ", intervenant=" + getIntervenant() + ", comprehension=" + getComprehension() + ", bilan="
                + getBilan()
                + '}';
    }

    public long getId() {
        return id;
    }

    public String getLien() {
        return lien;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public String getDescription() {
        return description;
    }

    public Date getDebut() {
        return debut;
    }

    public Date getFin() {
        return fin;
    }

    public SeanceStatus getStatus() {
        if (this.debut == null) {
            return SeanceStatus.EnAttente;
        } else if (this.fin == null) {
            return SeanceStatus.EnCours;
        }

        return SeanceStatus.Terminee;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setIntervenant(Intervenant intervenant) throws Exception {
        if (this.intervenant = null) {
            this.intervenant = intervenant;
        } else {
            throw new Exception("L'intervenant a déjà été renseigné");
        }
    }

    public Intervenant getIntervenant() {
        return intervenant;
    }

    public void start(Date debut, String lien) throws Exception {
        if (this.debut == null) {
            this.lien = lien;
            this.debut = debut;
        } else {
            throw new Exception("La séance a déjà commencé");
        }
    }

    public Comprehension getComprehension() {
        return comprehension;
    }

    public void stop(Date fin) throws Exception {
        if (this.getStatus() == SeanceStatus.Terminee) {
            throw new Exception("La séance est déjà terminée");
        }

        if (debut.after(fin)) {
            throw new IllegalArgumentException("La date de fin est avant la date de début de la séance");
        }

        this.fin = fin;
    }

    public Bilan getBilan() {
        return bilan;
    }

    public void setBilan(Bilan bilan) throws Exception {
        if (this.getStatus() == SeanceStatus.Terminee) {
            if (this.getBilan() == null) {
                this.bilan = bilan;
            } else {
                throw new Exception("Le bilan a déjà été renseigné");
            }

        } else {
            throw new Exception("La séance n'est pas terminée");
        }
    }

    public void setComprehension(Comprehension comprehension) throws Exception {
        if (this.getStatus() == SeanceStatus.Terminee) {
            if (this.getComprehension() == null) {
                this.comprehension = comprehension;
            } else {
                throw new Exception("La note de compréhension a déjà été renseignée");
            }
        } else {
            throw new Exception("La séance n'est pas terminée");
        }
    }
}
