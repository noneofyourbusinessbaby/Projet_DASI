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
import fr.insalyon.dasi.test.tp1.dao.MatiereDao;
import fr.insalyon.dasi.test.tp1.dao.SeanceDao;
import fr.insalyon.dasi.test.tp1.metier.model.Etablissement;
import fr.insalyon.dasi.test.tp1.metier.model.Matiere;
import fr.insalyon.dasi.test.tp1.metier.model.Personne;
import fr.insalyon.dasi.test.tp1.metier.model.Seance;
import fr.insalyon.dasi.test.tp1.metier.model.personne.Eleve;
import fr.insalyon.dasi.test.tp1.metier.model.personne.Intervenant;
import fr.insalyon.dasi.test.tp1.metier.model.seance.Bilan;
import fr.insalyon.dasi.test.tp1.metier.model.seance.Comprehension;
import fr.insalyon.dasi.test.tp1.metier.utils.EducNetApi;
import fr.insalyon.dasi.test.tp1.metier.utils.Messaging;
import java.sql.Date;
import java.util.List;
import java.time.Instant;
import java.util.ArrayList;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 *
 * @author khoupeurt
 */
public class Service {    
    
    public void inscrire(Intervenant intervenant) throws Exception {
        
        JpaUtil.creerContextePersistance();
                 
        try {
        
            JpaUtil.ouvrirTransaction();
                        
            PersonneDao.create(intervenant);
                                
            Messaging.envoyerMail("noreply@insa-lyon.fr", intervenant.getContact().getEmail(), "Inscription", "Vous avez été inscrit avec succès !! OUII !");
        
            JpaUtil.validerTransaction();
        }
        catch(NonUniqueResultException e){
            JpaUtil.annulerTransaction();
            throw new Exception("Le mail est deja utilisé");
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
            
            if (personne.getLogin().getPassword().equals(password)){
                return personne;
            }
        }
        catch (Exception e) {
            throw new Exception("Mot de passe ou email invalide");
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        
        throw new Exception("Mot de passe ou email invalide");
    }
    
    public Seance creerSeance(Long idEleve, String matiereNom, String description ) throws Exception{
        JpaUtil.creerContextePersistance();
        
        Eleve eleve = null;
        
        try {
            eleve = EleveDao.findById(idEleve);
        }
        catch(NoResultException ex){
            JpaUtil.fermerContextePersistance();
            throw new Exception("Impossible de trouver l'eleve");
        }
        
        Intervenant intervenant = null;
        try {
            Integer classe = eleve.getClasse();
            
            intervenant = IntervenantDao.findByNiveauEtDisponible(classe);
        }
        catch(NoResultException ex){
            JpaUtil.fermerContextePersistance();
            throw new Exception("Aucun intervenant n'est disponible");
        }
        
        Matiere matiere = null;
        try {
            matiere = MatiereDao.findByName(matiereNom);
        }
        catch(NoResultException ex){
            JpaUtil.fermerContextePersistance();
            throw new Exception("La matiere n'existe pas");
        }
                 
        try {
            JpaUtil.ouvrirTransaction();
            
            String lien = "https://insal-lyon.fr";

            Seance seance = new Seance(eleve, matiere, description);
            
            seance.setIntervenant(intervenant);
            
            Date date = new Date(Instant.now().getEpochSecond());

            seance.start(date, lien);
               
            JpaUtil.validerTransaction();
            
            Messaging.envoyerNotification(intervenant.getContact().getTelephone(), "Vous avez une nouvelle séance prévue pour le "+date);

            return seance;
        }
        catch(NoResultException e){
            JpaUtil.annulerTransaction();
            throw new Exception("Impossible de trouver un intervenant disponible ou l'eleve à associer");
        }
        catch(Exception e) {  
            JpaUtil.annulerTransaction();
            throw e;
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
    }
    
    public void terminerSeance(Long seanceId, Integer note ) throws Exception{
        JpaUtil.creerContextePersistance();
        
        try {
            Comprehension comprehension = new Comprehension();
            
            comprehension.setNote(note);

            JpaUtil.ouvrirTransaction();
            
            Seance seance = SeanceDao.findById(seanceId);
            
            Date dateArret = new Date(Instant.now().getEpochSecond());
            
            seance.stop(dateArret, comprehension);
            
            JpaUtil.validerTransaction();
        }
        catch (NoResultException e) {
            JpaUtil.annulerTransaction();
            throw new Exception("Impossible de trouver la séance à stopper");
        }
        catch (Exception e) {
            JpaUtil.annulerTransaction();
            throw e;
        }
        finally {
          JpaUtil.fermerContextePersistance();  
        } 
    }
    
    public void redigerBilan(Long seanceId, String contenu)throws Exception{
        JpaUtil.creerContextePersistance();
        
        try {
            JpaUtil.ouvrirTransaction();
               
            Seance seance = SeanceDao.findById(seanceId);
            
            Bilan bilan = new Bilan(contenu);
            
            seance.setBilan(bilan);
            
            Messaging.envoyerMail("noreply@insa-lyon.fr", seance.getEleve().getContact().getEmail(), "Bilan de votre seance du "+seance.getDebut()+" de "+seance.getMatiere(), contenu);
 
            JpaUtil.validerTransaction();
        }
        catch (NoResultException e) {
            JpaUtil.annulerTransaction();
            throw new Exception("Impossible de trouver la séance pour la rédaction du bilan");
        }
        catch (Exception e) {
            JpaUtil.annulerTransaction();
            throw e;
        }
        finally {
          JpaUtil.fermerContextePersistance();  
        }   
    }
    
    public List<Seance> getHistoriqueSeances(Long personneId)throws Exception{
        JpaUtil.creerContextePersistance();

        try {
            return SeanceDao.findHistoriqueParPersonne(personneId);
        }
        catch(Exception e) {
            throw new Exception("Impossible de lire l'historique des scéances de la personne");
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
    }
    
    public Personne getProfile(Long personneId)throws Exception{
        JpaUtil.creerContextePersistance();

        try {
          return PersonneDao.findById(personneId);
        }
        catch (NoResultException e) {
            throw new Exception("Impossible de trouver le profile de la personne");
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
    }
    
    public Seance getSeanceEnCours(Long personneId) throws Exception{
        JpaUtil.creerContextePersistance();

        try {
          return SeanceDao.findSeanceEnCours(personneId);
        }
        catch (NoResultException ex) {
            throw new Exception("Pas de séance en cours pour la personne");
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
    }
}
