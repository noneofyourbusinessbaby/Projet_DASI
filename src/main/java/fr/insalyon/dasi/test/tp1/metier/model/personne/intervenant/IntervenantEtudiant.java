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
public class IntervenantEtudiant extends Intervenant {
   
        
    @Column(nullable = false)    
    private String specialite;
    
    @Column(nullable = false)    
    private String universite;
    
    public IntervenantEtudiant() {
    }

    public IntervenantEtudiant(String nom, String prenom, Login login, Contact contact,
            Integer niveauDeCompetenceMin, Integer niveauDeCompetenceMax,
            String universite, String specialite
    ) {
        super(nom, prenom, login, contact, niveauDeCompetenceMin, niveauDeCompetenceMax);
        
        this.specialite = specialite;
        this.universite = universite;
    }   
    
    @Override
    public String toString() {
        return "IntervenantEtudiant: "+super.toString()+" Universite "+this.getUniversite()+" Specialité "+getSpecialite();
    }

    public String getSpecialite() {
        return specialite;
    }

    public String getUniversite() {
        return universite;
    }
}
