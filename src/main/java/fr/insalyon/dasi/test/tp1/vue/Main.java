/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.vue;

import fr.insalyon.dasi.test.tp1.dao.JpaUtil;
import fr.insalyon.dasi.test.tp1.metier.model.Contact;
import fr.insalyon.dasi.test.tp1.metier.model.Etablissement;
import fr.insalyon.dasi.test.tp1.metier.model.Login;
import fr.insalyon.dasi.test.tp1.metier.model.personne.Eleve;
import fr.insalyon.dasi.test.tp1.metier.service.EtablissementService;
import fr.insalyon.dasi.test.tp1.metier.service.PersonneService;

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
        
        // Insertion des établissements
        
        EtablissementService etablissementService = new EtablissementService();
        
        etablissementService.enregistrer("0692155T");
        
        
        // Insertion des utilisateurs
        PersonneService personneService = new PersonneService();
        
        Login login = new Login("email", "password");
        Contact contact = new Contact("email", "phone");
        
        Eleve eleve = new Eleve("nom", "prenom", login, contact, 0, etablissement);
        
        personneService.inscrire(eleve);
    }
}
