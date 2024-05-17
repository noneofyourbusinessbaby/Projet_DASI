/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.metier.service;

import fr.insalyon.dasi.test.tp1.dao.EleveDao;
import fr.insalyon.dasi.test.tp1.dao.EtablissementDao;
import fr.insalyon.dasi.test.tp1.dao.IntervenantDao;
import fr.insalyon.dasi.test.tp1.dao.PersonneDao;
import fr.insalyon.dasi.test.tp1.dao.JpaUtil;
import fr.insalyon.dasi.test.tp1.metier.model.Etablissement;
import fr.insalyon.dasi.test.tp1.metier.model.Personne;
import fr.insalyon.dasi.test.tp1.metier.model.Seance;
import fr.insalyon.dasi.test.tp1.metier.model.personne.Eleve;
import fr.insalyon.dasi.test.tp1.metier.model.personne.Intervenant;
import fr.insalyon.dasi.test.tp1.metier.utils.EducNetApi;
import fr.insalyon.dasi.test.tp1.metier.utils.Messaging;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author khoupeurt
 */
public class PersonneService {
    
    public void inscrire(Intervenant intervenant) throws Exception {
        
        JpaUtil.creerContextePersistance();
                 
        try {
        
            JpaUtil.ouvrirTransaction();
                        
            PersonneDao.create(intervenant);
                                
            Messaging.envoyerMail("noreply@insa-lyon.fr", intervenant.getContact().getEmail(), "Inscription", "Vous avez été inscrit avec succès !! OUII !");
        
            JpaUtil.validerTransaction();
        }
        catch(Exception e) {  
            JpaUtil.annulerTransaction();
            throw e;
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
    }
    
    public void inscrire(Eleve eleve, String codeEtablissement, Integer classe) throws Exception {
        
        JpaUtil.creerContextePersistance();
                 
        try {
        
            JpaUtil.ouvrirTransaction();
            
            Etablissement etablissement;
        
            try {
                etablissement = EtablissementDao.findByCode(codeEtablissement);
            }
            catch(NoResultException e){

                EducNetApi api = new EducNetApi();

                List<String> result = api.getInformationCollege(codeEtablissement);

                if(result == null) {
                    throw new Exception();
                }

                String ips = result.get(8);
                String nom = result.get(1);
                String nomCommune = result.get(4);

                String adresseEtablissement = nom + ", " + nomCommune;

                etablissement = new Etablissement(nom, ips, codeEtablissement, adresseEtablissement);

                EtablissementDao.create(etablissement);
            }

            eleve.setEtablissement(etablissement, classe);
            
            PersonneDao.create(eleve);
                                
            Messaging.envoyerMail("noreply@insa-lyon.fr", eleve.getContact().getEmail(), "Inscription", "Vous avez été inscrit avec succès !! OUII !");
        
            JpaUtil.validerTransaction();
        }
        catch(Exception e) {  
            JpaUtil.annulerTransaction();
            throw e;
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
    }
    
    public Personne login(String email, String password) throws Exception {
        JpaUtil.creerContextePersistance();

        try {
            Personne personne = PersonneDao.findByEmail(email);
            
            if (personne.getLogin().getPassword().equals(password)) return personne;
            
            throw new Exception();
        }
        catch (NoResultException e) {
            throw e;
        }
    }
    
    public Seance creerSeance(Long idEleve, String matiere, String description) throws Exception{
         JpaUtil.creerContextePersistance();
                 
        try {
        
            JpaUtil.ouvrirTransaction();
            // on cherche l'eleve
            
            String lien = ""; // todo generer
            Eleve eleve = EleveDao.findById(idEleve);
            
            Seance seance = new Seance(eleve, lien, matiere, description);
            
            // trouver un intervenant disponible 
            Integer classe = eleve.getClasse();
            
            Intervenant intervenant = IntervenantDao.findByNiveauEtDisponible(classe);
            
            seance.setIntervenant(intervenant);
            
            seance.start();
               
            // Seance seance = new Seance ()
            JpaUtil.validerTransaction();
        }
        catch(Exception e) {  
            JpaUtil.annulerTransaction();
            throw e;
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return null;
    }
}
