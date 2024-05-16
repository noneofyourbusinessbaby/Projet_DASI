/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.metier.model.personne;

import fr.insalyon.dasi.test.tp1.metier.model.Contact;
import fr.insalyon.dasi.test.tp1.metier.model.Login;
import fr.insalyon.dasi.test.tp1.metier.model.Personne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

    public Intervenant(String nom, String prenom, Login login, Contact contact, Integer niveauDeCompetenceMin, Integer niveauDeCompetenceMax) {
        super(nom, prenom, login, contact);
        
        this.niveauCompetenceMin = niveauDeCompetenceMin;
        this.niveauCompetenceMax = niveauDeCompetenceMax;
    }   

    @Override
    public String toString() {
        return this.getNom();
    }

    public Integer getNiveauCompetenceMin() {
        return niveauCompetenceMin;
    }
    
    public Integer getNiveauCompetenceMax() {
        return niveauCompetenceMax;
    }
}
