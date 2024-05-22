/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.metier.model.seance;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author nhajjhassa
 */
@Embeddable
public class Bilan implements Serializable{
    
    @Column(nullable = false)
    private String contenu;

    public Bilan() {
    }

    public Bilan(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return getContenu();
    }
    
    public String getContenu() {
        return contenu;
    }
    
    
}
