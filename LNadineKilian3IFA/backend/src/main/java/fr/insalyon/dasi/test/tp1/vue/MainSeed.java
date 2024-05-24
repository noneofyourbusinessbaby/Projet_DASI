/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.vue;

import java.util.List;

import fr.insalyon.dasi.test.tp1.dao.JpaUtil;
import fr.insalyon.dasi.test.tp1.dao.MatiereDao;
import fr.insalyon.dasi.test.tp1.metier.model.Contact;
import fr.insalyon.dasi.test.tp1.metier.model.Login;
import fr.insalyon.dasi.test.tp1.metier.model.Matiere;
import fr.insalyon.dasi.test.tp1.metier.model.Seance;
import fr.insalyon.dasi.test.tp1.metier.model.personne.Eleve;
import fr.insalyon.dasi.test.tp1.metier.model.personne.Intervenant;
import fr.insalyon.dasi.test.tp1.metier.model.personne.intervenant.IntervenantEnseignant;
import fr.insalyon.dasi.test.tp1.metier.service.FeaturesService;
import java.util.ArrayList;

/**
 *
 * @author nhajjhassa
 */
public class MainSeed {

    /**
     * @param args the command line arguments
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        JpaUtil.creerFabriquePersistance();

        List<Matiere> matieres = seedMatieres(10L);

        matieres.forEach(matiere -> {
            System.out.println(matiere);
        });

        List<Intervenant> intervenants = seedIntervenants(10L);

        intervenants.forEach(intervenant -> {
            System.out.println(intervenant);
        });

        List<Eleve> eleves = seedEleves(10L);

        eleves.forEach(eleve -> {
            System.out.println(eleve);
        });

        List<Seance> seances = seedSeances(eleves, matieres);

        seances.forEach(seance -> {
            System.out.println(seance);
        });
    }

    
    private static List<Eleve> seedEleves(Long c) throws Exception {

        List<Eleve> eleves = new ArrayList<>();

        for (int i = 0; i < c; i++) {
            Login login = new Login("emailEleve" + i, "passwordEleve"+i);
            Contact contact = new Contact("emailEleve"+i, "telephoneEleve"+i);

            Eleve eleve = new Eleve("nomEleve", "prenomEleve", login, contact);

            FeaturesService.inscrireEleve(eleve, "0692155T", 1);

            eleves.add(eleve);
        }

        return eleves;
    }

    private static List<Intervenant> seedIntervenants(Long c) throws Exception {

        List<Intervenant> intervenants = new ArrayList<>();
        
        for (int i = 0; i < c; i++) {
            Login login = new Login("emailIntervenant" + i, "passwordIntervenant"+i);
            Contact contact = new Contact("emailIntervenant"+i, "telephoneIntervent"+i);

            Intervenant intervenant = new IntervenantEnseignant("nomIntervent", "prenomIntervenant", login, contact, 1, 6, "college");

            FeaturesService.inscrireIntervenant(intervenant);

            intervenants.add(intervenant);
        }

        return intervenants;
    }

    private static List<Matiere> seedMatieres(Long c) throws Exception {
        
        JpaUtil.creerContextePersistance();

        List<Matiere> matieres = new ArrayList<>();
        
        try {
            JpaUtil.ouvrirTransaction();
            for (int i = 0; i < c; i++) {
                Matiere matiere = new Matiere("matiere" + i);

                MatiereDao.create(matiere);

                matieres.add(matiere);
            }   
            
            JpaUtil.validerTransaction(); 
        }
        catch(Exception ex) {  
            JpaUtil.annulerTransaction();
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }


        return matieres;
    }

    private static List<Seance> seedSeances(List<Eleve> eleves, List<Matiere> matieres) throws Exception {

        List<Seance> seances = new ArrayList<>();

        for (int i = 0; i < eleves.size(); i++) {

            Eleve eleve = eleves.get(i);

            Matiere matiere = matieres.get(i % matieres.size());

            Seance seance = FeaturesService.plannifierNouvelleSeance(eleve.getId(), matiere.getId(), "descriptionSeance");

            seances.add(seance);
        }

        return seances;
    }
}