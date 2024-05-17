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
    
    @Column(nullable = false)
    private String lien;
    
    @Column(nullable = false)
    private String description;
    
    @Column(nullable = false)
    private String matiere;
    
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
    }

    public Seance(Eleve eleve, String lien, String matiere, String description) throws Exception {
        this.eleve = eleve;
        
        this.lien = lien;
        this.matiere = matiere;
        this.description = description;
    }   

    @Override
    public String toString() {
        return this.lien;
    }
    
    public long getId() {
        return id;
    }

    public String getLien() {
        return lien;
    }

    public String getMatiere() {
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
        }
        else if(this.fin == null) {
            return SeanceStatus.EnCours;
        }
        
        return SeanceStatus.Terminee;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setIntervenant(Intervenant intervenant) {
        this.intervenant = intervenant;
    }

    public Intervenant getIntervenant() {
        return intervenant;
    }
    
    public void start(Date debut, String lien) throws Exception {

        if (this.debut == null){
            this.lien = lien;
            this.debut = debut;
        }
        else {
            throw new Exception();
        }    
    }

    public Comprehension getComprehension() {
        return comprehension;
    }
    
    public void stop(Date fin, Comprehension comprehension) throws Exception {
        
        if (this.getStatus() == SeanceStatus.Terminee) {
            throw new Exception();
        }
        
        if(debut.after(fin)) {
            throw new Exception();
        }
        
        this.fin = fin;
        
        this.comprehension = comprehension;
    }

    public Bilan getBilan() {
        return bilan;
    }

    public void setBilan(Bilan bilan) throws Exception {
        
        if (this.getStatus() == SeanceStatus.Terminee){
            this.bilan = bilan;
        }
        else{
            throw new Exception();
        }        
    }
}
