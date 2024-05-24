/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.metier.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author nhajjhassa
 */

@Embeddable
public class Contact implements Serializable {

    @Column(name = "contact_mail", nullable = false)
    private String email;

    @Column(name = "contact_telephone", nullable = false)
    private String telephone;

    public Contact() {
    }

    public Contact(String email, String telephone) {
        this.email = email;
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Contact{" + "email=" + getEmail() + ", telephone=" + getTelephone() + '}';
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }
}