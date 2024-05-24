/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.metier.model;

import com.google.maps.model.LatLng;
import fr.insalyon.dasi.test.tp1.metier.model.personne.Eleve;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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

    @Column(nullable = false)
    private String IPS;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String adresse;
    
    @Column(nullable = false)
    private Double lat;
    
    @Column(nullable = false)
    private Double lng;
    
/*  @OneToMany(mappedBy = "etablissement")
    private List<Eleve> eleves;
    */

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

/*  public List<Eleve> getEleves() {
        return eleves;
    }
    
    public void addEleve(Eleve eleve) {
        this.eleves.add(eleve);
    }
    */
}
