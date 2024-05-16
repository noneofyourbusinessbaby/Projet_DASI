/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.metier.service;

import fr.insalyon.dasi.test.tp1.dao.PersonneDao;
import fr.insalyon.dasi.test.tp1.dao.JpaUtil;
import fr.insalyon.dasi.test.tp1.metier.model.Personne;
import fr.insalyon.dasi.test.tp1.metier.utils.Messaging;

/**
 *
 * @author khoupeurt
 */
public class PersonneService {
    
    public void inscrire(Personne personne) {
                
        JpaUtil.creerContextePersistance();
        
        try {
            JpaUtil.ouvrirTransaction();

            PersonneDao personneDao = new PersonneDao();

            personneDao.create(personne);
                        
            JpaUtil.validerTransaction();
        
            Messaging.envoyerMail("noreply@insa-lyon.fr", personne.getContact().getEmail(), "Inscription", "Vous avez été inscrit avec succès !! OUII !");
        }
        catch(Exception e) {  
            JpaUtil.annulerTransaction();
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
    }
}
