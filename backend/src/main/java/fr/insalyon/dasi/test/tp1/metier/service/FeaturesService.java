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
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 *
 * @author khoupeurt
 * 
 *         Service métier
 * 
 *         Permet de gérer les actions métier
 * 
 *         Les méthodes de ce service sont transactionnelles et gèrent les
 *         transactions elles-mêmes.
 *         En cas d'exception, la transaction est annulée.
 * 
 */
public class FeaturesService {

    /**
     * Permet d'inscrire un intervenant
     * 
     * @param intervenant Intervenant
     * @throws Exception Si l'email est déjà utilisé
     */
    public static void inscrireIntervenant(Intervenant intervenant) throws Exception {

        JpaUtil.creerContextePersistance();

        try {

            JpaUtil.ouvrirTransaction();

            PersonneDao.create(intervenant);

            JpaUtil.validerTransaction();

            Messaging.envoyerMail("noreply@insa-lyon.fr", intervenant.getContact().getEmail(), "Inscription",
                    "Vous avez été inscrit avec succès !! OUII !");

        } catch (NonUniqueResultException e) {
            JpaUtil.annulerTransaction();
            throw new Exception("Le mail est deja utilisé");
        } catch (Exception e) {
            JpaUtil.annulerTransaction();
            throw e;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    /**
     * Permet d'inscrire un élève
     * 
     * @param eleve
     * @param codeEtablissement
     * @param classe
     * @throws Exception Si l'établissement n'existe pas, si l'email est déjà
     *                   utilisé
     */
    public static void inscrireEleve(Eleve eleve, String codeEtablissement, Integer classe) throws Exception {
        JpaUtil.creerContextePersistance();

        try {

            JpaUtil.ouvrirTransaction();

            Etablissement etablissement;

            try {
                etablissement = EtablissementDao.findByCode(codeEtablissement);
            } catch (NoResultException e) {

                EducNetApi api = new EducNetApi();

                List<String> result = api.getInformationCollege(codeEtablissement);

                if (result == null) {
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

            JpaUtil.validerTransaction();

            Messaging.envoyerMail("noreply@insa-lyon.fr", eleve.getContact().getEmail(), "Inscription",
                    "Vous avez été inscrit avec succès !! OUII !");
        } catch (Exception e) {
            JpaUtil.annulerTransaction();
            throw e;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    /**
     * Permet de connecter une personne
     * 
     * @param email    Email
     * @param password Mot de passe
     * @return La personne connectée
     * @throws Exception Si le mot de passe ou l'email est invalide
     */
    public static Personne login(String email, String password) throws Exception {
        JpaUtil.creerContextePersistance();

        try {
            Personne personne = PersonneDao.findByEmail(email);

            if (personne.getLogin().getPassword().equals(password)) {
                return personne;
            }
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        throw new Exception("Mot de passe ou email invalide");
    }

    /**
     * Crée une séance pour un élève
     * 
     * @param idEleve     Id de l'élève
     * @param matiereNom  Nom de la matière
     * @param description Description de la séance
     * @return La séance créée
     * @throws Exception Si l'élève a déjà une séance en cours, si l'élève ou la
     *                   matière n'existe pas, ou si aucun intervenant n'est
     *                   disponible
     */
    public static Seance plannifierNouvelleSeance(Long idEleve, Long idMatiere, String description) throws Exception {
        JpaUtil.creerContextePersistance();

        try {
            JpaUtil.ouvrirTransaction();

            if (SeanceDao.findSeanceEnCours(idEleve) != null) {
                throw new Exception("L'eleve a déjà une séance en cours");
            }

            Eleve eleve = EleveDao.findById(idEleve);

            if (eleve == null) {
                throw new Exception("Impossible de trouver l'eleve");
            }

            Intervenant intervenant = IntervenantDao.findByNiveauEtDisponible(eleve);

            if (intervenant == null) {
                throw new Exception("Impossible de trouver un intervenant disponible");
            }

            Matiere matiere = MatiereDao.findById(idMatiere);

            if (matiere == null) {
                throw new Exception("Impossible de trouver la matiere");
            }

            String lien = "https://insa-lyon.fr";

            Seance seance = new Seance(eleve, matiere, description);

            seance.setIntervenant(intervenant);

            Date date = new Date(Instant.now().getEpochSecond());

            seance.start(date, lien);

            SeanceDao.create(seance);

            JpaUtil.validerTransaction();

            Messaging.envoyerNotification(intervenant.getContact().getTelephone(),
                    "Vous avez une nouvelle séance prévue pour le " + date);

            return seance;
        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            throw ex;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    /**
     * Permet de terminer une séance
     * 
     * @param seanceId Id de la séance
     * @throws Exception Si la séance n'existe pas
     */
    public static void terminerSeance(Long seanceId) throws Exception {
        JpaUtil.creerContextePersistance();

        try {
            JpaUtil.ouvrirTransaction();

            Seance seance = SeanceDao.findById(seanceId);

            if (seance == null) {
                throw new Exception("Impossible de trouver la séance");
            }

            seance.stop(new Date(Instant.now().getEpochSecond()), comprehension);

            SeanceDao.update(seance);

            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            throw ex;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    public static void redigerNoteComprehension(Long seanceId, Integer note) {
        JpaUtil.creerContextePersistance();

        try {
            JpaUtil.ouvrirTransaction();

            Seance seance = SeanceDao.findById(seanceId);

            if (seance == null) {
                throw new Exception("Impossible de trouver la séance");
            }

            seance.setNoteComprehension(note);

            SeanceDao.update(seance);

            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            throw ex;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    /**
     * Permet de rédiger un bilan pour une séance
     * 
     * @param seanceId Id de la séance
     * @param contenu  Contenu du bilan
     * @throws Exception Si la séance n'existe pas ou si la séance n'est pas
     *                   terminée
     */
    public static void redigerBilan(Long seanceId, String contenu) throws Exception {
        JpaUtil.creerContextePersistance();

        try {
            JpaUtil.ouvrirTransaction();

            Seance seance = SeanceDao.findById(seanceId);

            if (seance == null) {
                throw new Exception("Impossible de trouver la séance");
            }

            Bilan bilan = new Bilan(contenu);

            seance.setBilan(bilan);

            SeanceDao.update(seance);

            JpaUtil.validerTransaction();

            Messaging.envoyerMail("noreply@insa-lyon.fr", seance.getEleve().getContact().getEmail(),
                    "Bilan de votre seance du " + seance.getDebut() + " de " + seance.getMatiere(), contenu);

        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            throw ex;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    /**
     * Permet de récupérer l'historique des séances d'une personne
     * 
     * @param personneId Id de la personne
     * @return Liste des séances passées de la personne
     * @throws Exception Si la personne n'existe pas
     */
    public static List<Seance> recupererHistoriqueSeances(Long personneId) throws Exception {
        JpaUtil.creerContextePersistance();

        try {
            return SeanceDao.findHistoriqueParPersonne(personneId);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    /**
     * Permet de récupérer le profil d'une personne
     * 
     * @param personneId Id de la personne
     * @return Le profile de la personne recherchée
     * @throws Exception Si la personne n'existe pas
     */
    public static Personne recupererProfilePersonne(Long personneId) throws Exception {
        JpaUtil.creerContextePersistance();

        try {
            Personne personne = PersonneDao.findById(personneId);

            if (personne == null) {
                throw new Exception("Impossible de trouver la personne");
            }

            return personne;

        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    /**
     * Permet de récupérer la séance en cours d'une personne
     * 
     * @param personneId Id de la personne
     * @return La séance en cours de la personne
     * @throws Exception Si la personne n'existe pas ou si la personne n'a pas de
     *                   séance en cours
     */
    public static Seance recupererSeanceEnCours(Long personneId) throws Exception {
        JpaUtil.creerContextePersistance();

        try {
            Seance seance = SeanceDao.findSeanceEnCours(personneId);

            if (seance == null) {
                throw new Exception("Pas de séance en cours pour la personne");
            }

            return seance;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }
}
