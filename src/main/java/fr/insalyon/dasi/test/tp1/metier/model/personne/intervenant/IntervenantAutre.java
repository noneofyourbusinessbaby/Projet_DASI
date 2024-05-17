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
public class IntervenantAutre extends Intervenant {
   
    @Column(nullable = false)    
    private String activite;
    
    public IntervenantAutre() {
    }

    public IntervenantAutre(String nom, String prenom, Login login, Contact contact,
            Integer niveauDeCompetenceMin, Integer niveauDeCompetenceMax,
            String activite
    ) {
        super(nom, prenom, login, contact, niveauDeCompetenceMin, niveauDeCompetenceMax);
        
        this.activite = activite;
    }   
    
    @Override
    public String toString() {
        return this.getNom();
    }

    public String getActivite() {
        return activite;
    }
}
