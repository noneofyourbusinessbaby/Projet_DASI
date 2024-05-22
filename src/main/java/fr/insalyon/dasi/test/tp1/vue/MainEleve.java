/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.vue;

import fr.insalyon.dasi.test.tp1.metier.model.Contact;
import fr.insalyon.dasi.test.tp1.metier.model.Login;
import fr.insalyon.dasi.test.tp1.metier.model.Matiere;
import fr.insalyon.dasi.test.tp1.metier.model.Seance;
import fr.insalyon.dasi.test.tp1.metier.model.personne.Eleve;
import fr.insalyon.dasi.test.tp1.metier.model.personne.Intervenant;
import fr.insalyon.dasi.test.tp1.metier.model.personne.intervenant.IntervenantEnseignant;
import fr.insalyon.dasi.test.tp1.metier.service.SeedService;
import fr.insalyon.dasi.test.tp1.metier.service.Service;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nhajjhassa
 */
public class MainEleve {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        // Inscription
        // Authentification
        // Création de demande
        // 
        
        Logger logger = Logger.getLogger(MainEleve.class.getName());
        
        Service service = new Service();
        SeedService seedService = new SeedService();
        
        String ETA = "0692155T";
        
        Eleve eleve1 = new Eleve("nom", "prenom", new Login("email", "password"), new Contact("email", "telephone"));
        Eleve eleve2 = new Eleve("nom", "prenom", new Login(eleve1.getLogin().getEmail(), "password2"), new Contact("email", "telephone"));
        Eleve eleve3 = new Eleve("nom", "prenom", new Login("mail2", "password2"), new Contact("email", "telephone"));

        // OK
        // Inscription d'un eleve
        service.inscrire(eleve1, ETA, 2);
        
        logger.log(Level.ALL, "Inscription d'un eleve");
        logger.log(Level.INFO, eleve1.toString());
        
        // ERR: email pas unique
        // Inscription d'un eleve
        try {
            service.inscrire(eleve2, ETA, 2);
        }
        catch(Exception ex){
            Logger.getLogger(MainEleve.class.getName()).log(Level.INFO, null, ex);
        }
        
        // ERR: classe superieur à 6
        // Inscription d'un eleve
        try {
            service.inscrire(eleve3, ETA, 7);
        }
        catch(Exception ex){
            Logger.getLogger(MainEleve.class.getName()).log(Level.INFO, null, ex);
        }
        
        // ERR:  classe inferieur à 1
        // Inscription d'un eleve
        try {
            service.inscrire(eleve3, ETA, -1);
        }
        catch(Exception ex){
            Logger.getLogger(MainEleve.class.getName()).log(Level.INFO, null, ex);
        }
        
        // ERR: email ou password invalide
        // Login
        try {
            service.login("fake", "fake");
        } catch (Exception ex) {
            Logger.getLogger(MainEleve.class.getName()).log(Level.INFO, null, ex);
        }
        
        // OK: email et password valide
        // Login
        service.login(eleve1.getLogin().getEmail(), eleve1.getLogin().getPassword());
        
        logger.log(Level.ALL, "Login d'un eleve");
        
        // OK: initialisation des matieres
        List<Matiere> matieres = seedService.seedMatieres(10L);
        
        // ERR: Pas d'intervenant trouvé
        // Creation d'une scéance
        try {
            service.creerSeance(eleve1.getId(), matieres.get(0).getNom(), "description");
        }
        catch(Exception ex){
            Logger.getLogger(MainEleve.class.getName()).log(Level.INFO, null, ex);
        }
        
        // Inscription d'un intervenant
        Intervenant intervenant = new IntervenantEnseignant("nom", "prenom", new Login("email3", "password"), new Contact("email", "password"), 1, 6, ETA);
        
        service.inscrire(intervenant);
        
        logger.log(Level.ALL, "Inscription d'un intervenant");
        logger.log(Level.INFO, intervenant.toString());
        
        // OK: Un intervenant est trouvé
        // Création d'une scéance
        Seance seance = service.creerSeance(eleve1.getId(), "matiere", "description");
        
        logger.log(Level.ALL, "Creation d'une seance");
        logger.log(Level.INFO, seance.toString());
        
        // ERR: La scéance n'est pas terminé
        // Rediger un bilan
        try {
            service.redigerBilan(seance.getId(), "bilan");
        }
        catch(Exception ex){
            Logger.getLogger(MainEleve.class.getName()).log(Level.INFO, null, ex);
        }
        
        // ERR: Note inferieur à 0
        // Terminer une seance
        try {
            service.terminerSeance(seance.getId(), -1);
        }
        catch(Exception ex){
            Logger.getLogger(MainEleve.class.getName()).log(Level.INFO, null, ex);
        }
        
        // ERR: Note superieur à 5
        // Terminer une seance
        try {
            service.terminerSeance(seance.getId(), 6);
        }
        catch(Exception ex){
            Logger.getLogger(MainEleve.class.getName()).log(Level.INFO, null, ex);
        }
        
        // OK: Note entre 0 et 5
        // Terminer une seance
        service.terminerSeance(seance.getId(), 2);
        
        logger.log(Level.ALL, "Termination d'une seance");
        
        // OK: Le scéance est terminé
        // Rediger un bilan
        service.redigerBilan(seance.getId(), "contenu");
        
        logger.log(Level.ALL, "Redaction du bilan d'une seance");
    }
}
