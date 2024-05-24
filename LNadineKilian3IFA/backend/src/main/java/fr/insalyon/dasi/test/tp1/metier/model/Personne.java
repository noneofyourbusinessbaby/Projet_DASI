/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.metier.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author khoupeurt
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Personne implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Embedded
    Login login;

    @Embedded
    Contact contact;

    public Personne() {
    }

    public Personne(String nom, String prenom, Login login, Contact contact) {
        this.nom = nom;
        this.prenom = prenom;

        this.login = login;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Personne{" + "nom=" + getNom() + ", prenom=" + getPrenom() + '}';
    }

    public long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Login getLogin() {
        return login;
    }

    public Contact getContact() {
        return contact;
    }
}
