/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.metier.model.personne;

import fr.insalyon.dasi.test.tp1.metier.model.Contact;
import fr.insalyon.dasi.test.tp1.metier.model.Login;
import fr.insalyon.dasi.test.tp1.metier.model.Personne;
import fr.insalyon.dasi.test.tp1.metier.model.Seance;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author khoupeurt
 */

@Entity
public abstract class Intervenant extends Personne {

    @Column(nullable = false)
    private Integer niveauCompetenceMin;

    @Column(nullable = false)
    private Integer niveauCompetenceMax;

    public Intervenant() {
    }

    public Intervenant(String nom, String prenom, Login login, Contact contact, Integer niveauDeCompetenceMin,
            Integer niveauDeCompetenceMax) {
        super(nom, prenom, login, contact);

        if (niveauDeCompetenceMin > niveauDeCompetenceMax) {
            throw new IllegalArgumentException(
                    "Le niveau de compétence minimum doit être inférieur au niveau de compétence maximum.");
        }

        if (niveauDeCompetenceMin < 0 || niveauDeCompetenceMax < 0) {
            throw new IllegalArgumentException("Les niveaux de compétence doivent être positifs.");
        }

        if (niveauDeCompetenceMin > 6 || niveauDeCompetenceMax > 6) {
            throw new IllegalArgumentException("Les niveaux de compétence doivent être inférieurs ou égaux à 5.");
        }

        this.niveauCompetenceMin = niveauDeCompetenceMin;
        this.niveauCompetenceMax = niveauDeCompetenceMax;
    }

    @Override
    public String toString() {
        return "Intervenant{" + "nom=" + getNom() + ", prenom=" + getPrenom() + ", niveauCompetenceMin="
                + niveauCompetenceMin
                + ", niveauCompetenceMax=" + niveauCompetenceMax + '}';
    }

    public Integer getNiveauCompetenceMin() {
        return niveauCompetenceMin;
    }

    public Integer getNiveauCompetenceMax() {
        return niveauCompetenceMax;
    }

}
