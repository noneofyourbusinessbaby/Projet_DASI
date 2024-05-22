/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.metier.model.personne.intervenant;

import fr.insalyon.dasi.test.tp1.metier.model.Contact;
import fr.insalyon.dasi.test.tp1.metier.model.Login;
import fr.insalyon.dasi.test.tp1.metier.model.personne.Intervenant;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author khoupeurt
 */

@Entity
public class IntervenantEnseignant extends Intervenant {
   
    @Column(nullable = false)    
    private String typeEtablissement;
    
    public IntervenantEnseignant() {
    }

    public IntervenantEnseignant(String nom, String prenom, Login login, Contact contact,
            Integer niveauDeCompetenceMin, Integer niveauDeCompetenceMax,
            String typeEtablissement
    ) {
        super(nom, prenom, login, contact, niveauDeCompetenceMin, niveauDeCompetenceMax);
        
        this.typeEtablissement = typeEtablissement;
    }   
    
    @Override
    public String toString() {
        return "IntervenantEnseignant: "+super.toString()+" typeEtablissement"+this.getTypeEtablissement();
    }

    public String getTypeEtablissement() {
        return typeEtablissement;
    }
}
