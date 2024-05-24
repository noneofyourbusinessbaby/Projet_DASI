/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.frontend;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author nhajjhassa
 */
@Entity
public class TestUtilisateur {
    
    @Id
    private Long id;
    
    private String nom;
    
    private String prenom;
    
    private String mail;
    
    private String password;
    
    public TestUtilisateur(){
        
    }
    
    public TestUtilisateur(String prenom, String nom, String mail){
        this.mail = mail;
        this.prenom = prenom;
        this.nom = nom;
        this.id = 1024L;
    }

    public String getNom() {
        return nom;
    }
    
    public String getPrenom(){
        return prenom;
    }
    
    public String getMail() {
        return mail;
    }
    
    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

}
