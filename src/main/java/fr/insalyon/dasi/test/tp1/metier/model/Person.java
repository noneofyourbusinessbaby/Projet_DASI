/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.metier.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author khoupeurt
 */

@Entity
@Table(name = "T_PERSONNE")
public class Person implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false)
    private String nom;
    
    @Column(nullable = false)
    private String prenom;
    
    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String mail;
    
    @Column(nullable = false)
    private String address;
    
    @Column(nullable = true)
    private Double latitude;
    @Column(nullable = true)
    private Double longitude;
    
    

    public Person() {
    }

    public Person(String nom, String prenom, String password, String mail, String address) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.mail = mail;
        this.address = address;
    }   

    @Override
    public String toString() {
        return this.nom;
    }

    // GET
    
    public long getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
    
    public String getAddress() {
        return address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
    
    // SET

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Boolean checkPassword(String password) {
        return (this.password == null ? password == null : this.password.equals(password));
    }
        
    
    public void setLongitudeLatitude(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
