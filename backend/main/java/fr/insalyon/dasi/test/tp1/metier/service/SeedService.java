/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.metier.service;

import fr.insalyon.dasi.test.tp1.dao.JpaUtil;
import fr.insalyon.dasi.test.tp1.dao.MatiereDao;
import fr.insalyon.dasi.test.tp1.metier.model.Contact;
import fr.insalyon.dasi.test.tp1.metier.model.Login;
import fr.insalyon.dasi.test.tp1.metier.model.Matiere;
import fr.insalyon.dasi.test.tp1.metier.model.Seance;
import fr.insalyon.dasi.test.tp1.metier.model.personne.Eleve;
import fr.insalyon.dasi.test.tp1.metier.model.personne.Intervenant;
import fr.insalyon.dasi.test.tp1.metier.model.personne.intervenant.IntervenantEnseignant;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nhajjhassa
 */
public class SeedService {

    public static List<Eleve> seedEleves(Long c) throws Exception {
        JpaUtil.creerContextePersistance();

        List<Eleve> eleves = new ArrayList<>();

        try {
            for (int i = 0; i < c; i++) {
                Login login = new Login("email" + i, "password");
                Contact contact = new Contact("email", "telephone");

                Eleve eleve = new Eleve("nom", "prenom", login, contact);

                FeaturesService.inscrireEleve(eleve, "classe", 1);

                eleves.add(eleve);
            }
        } catch (Exception e) {
            JpaUtil.annulerTransaction();
            throw e;
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return eleves;
    }

    public static List<Intervenant> seedIntervenants(Long c) throws Exception {
        JpaUtil.creerContextePersistance();

        List<Intervenant> intervenants = new ArrayList<>();

        try {
            for (int i = 0; i < c; i++) {
                Login login = new Login("email" + i, "password");
                Contact contact = new Contact("email", "telephone");

                Intervenant intervenant = new IntervenantEnseignant("nom", "prenom", login, contact, 1, 6, "college");

                FeaturesService.inscrireIntervenant(intervenant);

                intervenants.add(intervenant);
            }
        } catch (Exception e) {
            JpaUtil.annulerTransaction();
            throw e;
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return intervenants;
    }

    public static List<Matiere> seedMatieres(Long c) throws Exception {
        JpaUtil.creerContextePersistance();

        List<Matiere> matieres = new ArrayList<>();

        try {
            for (int i = 0; i < c; i++) {
                Matiere matiere = new Matiere("matiere" + i);

                MatiereDao.create(matiere);

                matieres.add(matiere);
            }
        } catch (Exception e) {
            JpaUtil.annulerTransaction();
            throw e;
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return matieres;
    }

    public static List<Seance> seedSeances(List<Eleve> eleves, List<Matiere> matieres) throws Exception {
        JpaUtil.creerContextePersistance();

        List<Seance> seances = new ArrayList<>();

        try {

            for (int i = 0; i < eleves.size(); i++) {

                Eleve eleve = eleves.get(i);

                Matiere matiere = matieres.get(i % matieres.size());

                Seance seance = FeaturesService.plannifierNouvelleSeance(eleve.getId(), matiere.getId(), "description");

                seances.add(seance);
            }

        } catch (Exception e) {
            JpaUtil.annulerTransaction();
            throw e;
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return seances;
    }
}
