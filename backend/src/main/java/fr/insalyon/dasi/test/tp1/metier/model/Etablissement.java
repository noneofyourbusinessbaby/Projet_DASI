/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.metier.model;

import com.google.maps.model.LatLng;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author khoupeurt
 */

@Entity
public class Etablissement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nom;

    @Column(nullable = false, unique = true)
    private String IPS;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String adresse;
    
    private Double lat;
    
    private Double lng;

    public Etablissement() {
    }

    public Etablissement(String nom, String IPS, String code, String adresse, LatLng coord) {
        this.nom = nom;
        this.IPS = IPS;
        this.code = code;
        this.adresse = adresse;
        
        this.lat = coord.lat;
        this.lng = coord.lng;
    }

    @Override
    public String toString() {
        return "Etablissement{" + "nom=" + getNom() + ", IPS=" + getIPS() + ", code=" + getCode() + ", adresse="
                + getAdresse() + '}';
    }

    public long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getCode() {
        return code;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getIPS() {
        return IPS;
    }
    public Double getLat(){
        return lat;
    }
    public Double getLng(){
        return lng;
    }
}
