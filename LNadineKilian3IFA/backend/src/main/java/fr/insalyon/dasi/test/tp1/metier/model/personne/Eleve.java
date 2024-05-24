/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.metier.model.personne;

import fr.insalyon.dasi.test.tp1.metier.model.Contact;
import fr.insalyon.dasi.test.tp1.metier.model.Etablissement;
import fr.insalyon.dasi.test.tp1.metier.model.Login;
import fr.insalyon.dasi.test.tp1.metier.model.Personne;
import fr.insalyon.dasi.test.tp1.metier.model.Seance;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author khoupeurt
 */

@Entity
public class Eleve extends Personne {

    @Column(nullable = false)
    private Integer classe;

    @ManyToOne()
    private Etablissement etablissement;

    public Eleve() {
    }

    public Eleve(String nom, String prenom, Login login, Contact contact) {
        super(nom, prenom, login, contact);
    }

    @Override
    public String toString() {
        return "Eleve{" + "nom=" + getNom() + ", prenom=" + getPrenom() + ", classe=" + classe + ", etablissement="
                + etablissement + '}';
    }

    public Integer getClasse() {
        return classe;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement, Integer classe) throws Exception {

        if (classe < 0 || classe > 6) {
            throw new IllegalArgumentException("La classe doit être comprise entre 0 et 6");
        }

        this.classe = classe;
        this.etablissement = etablissement;
    }

}
