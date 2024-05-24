/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.metier.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

/**
 *
 * @author khoupeurt
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Matiere implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nom;
    
    public Matiere() {
    }

    public Matiere(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Matiere{" + "nom=" + getNom() + '}';
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
    
}
