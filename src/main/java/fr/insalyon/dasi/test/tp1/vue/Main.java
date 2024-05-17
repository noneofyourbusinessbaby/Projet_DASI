/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.vue;

import fr.insalyon.dasi.test.tp1.dao.JpaUtil;
import fr.insalyon.dasi.test.tp1.metier.model.Contact;
import fr.insalyon.dasi.test.tp1.metier.model.Login;
import fr.insalyon.dasi.test.tp1.metier.model.personne.Eleve;
import fr.insalyon.dasi.test.tp1.metier.model.personne.Intervenant;
import fr.insalyon.dasi.test.tp1.metier.model.personne.intervenant.IntervenantAutre;
import fr.insalyon.dasi.test.tp1.metier.model.personne.intervenant.IntervenantEnseignant;
import fr.insalyon.dasi.test.tp1.metier.model.personne.intervenant.IntervenantEtudiant;
import fr.insalyon.dasi.test.tp1.metier.service.PersonneService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author khoupeurt
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        JpaUtil.creerFabriquePersistance();
        
        PersonneService personneService = new PersonneService();
        
        Eleve eleve = new Eleve("nom", "prenom", new Login("email", "password"), new Contact("email", "phone"));
        
        List<Intervenant> intervenants = new ArrayList<>();
        
        for (Integer i = 0; i<15 ; i++){
            
            Login login = new Login("email"+i, "password"+i);
            Contact contact = new Contact("email"+i, "phone"+i);
            
            Intervenant intervenant;
            
            if(i < 5) {  
                intervenant = new IntervenantAutre(
                        "nom"+i, "prenom"+i,
                        login, contact,
                        0,6, 
                        "activitée"
                );           
            }
            else if (i < 10){
                intervenant = new IntervenantEtudiant(
                        "nom"+i, "prenom"+i,
                        login, contact,
                        0,6, 
                        "université", "spécialité"
                );  
            }
            else {
                intervenant = new IntervenantEnseignant(
                        "nom"+i, "prenom"+i,
                        login, contact,
                        0,6, 
                        "typeEtablissement"
                );  
            }
            
            intervenants.add(intervenant);
        }
        
         intervenants.forEach((intervenant) -> {
            try {
                personneService.inscrire(intervenant);
            } catch (Exception e) {
                e.printStackTrace();
            }
         });
        
        
        try {
            personneService.inscrire(eleve, "0692155T", 1);
            
            personneService.login(eleve.getLogin().getEmail(), eleve.getLogin().getPassword());
            
            personneService.creerSeance(eleve.getId(), seance);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
