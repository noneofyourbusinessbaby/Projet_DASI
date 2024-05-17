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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author khoupeurt
 */

@Entity
public class Eleve extends Personne {
    
    @Column(nullable = false)
    private Integer classe;
    
    @OneToOne
    private Etablissement etablissement;
    
    @OneToMany(mappedBy = "eleve")
    private List<Seance> seances;
        
    public Eleve() {
    }

    public Eleve(String nom, String prenom, Login login, Contact contact) {
        super(nom, prenom, login, contact);
    }   

    @Override
    public String toString() {
        return this.getNom();
    }

    public Integer getClasse() {
        return classe;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement, Integer classe) throws Exception {
        
        if (classe < 0 || classe > 6) {
            throw new Exception();
        }
        
        this.classe = classe;
        this.etablissement = etablissement;
    }

    @Override
    public List<Seance> getSeances() {
        return this.seances;
    }
}
