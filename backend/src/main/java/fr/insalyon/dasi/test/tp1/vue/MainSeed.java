/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.vue;

import java.util.List;

import fr.insalyon.dasi.test.tp1.dao.JpaUtil;
import fr.insalyon.dasi.test.tp1.metier.model.Matiere;
import fr.insalyon.dasi.test.tp1.metier.model.Seance;
import fr.insalyon.dasi.test.tp1.metier.model.personne.Eleve;
import fr.insalyon.dasi.test.tp1.metier.model.personne.Intervenant;
import fr.insalyon.dasi.test.tp1.metier.service.SeedService;

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

        List<Matiere> matieres = SeedService.seedMatieres(10L);

        matieres.forEach(matiere -> {
            System.out.println(matiere);
        });

        List<Intervenant> intervenants = SeedService.seedIntervenants(10L);

        intervenants.forEach(intervenant -> {
            System.out.println(intervenant);
        });

        List<Eleve> eleves = SeedService.seedEleves(10L);

        eleves.forEach(eleve -> {
            System.out.println(eleve);
        });

        List<Seance> seances = SeedService.seedSeances(eleves, matieres);

        seances.forEach(seance -> {
            System.out.println(seance);
        });
    }

    public static List<Eleve> seedEleves(Long c) throws Exception {

        List<Eleve> eleves = new ArrayList<>();

        for (int i = 0; i < c; i++) {
            Login login = new Login("email" + i, "password");
            Contact contact = new Contact("email", "telephone");

            Eleve eleve = new Eleve("nom", "prenom", login, contact);

            FeaturesService.inscrireEleve(eleve, "classe", 1);

            eleves.add(eleve);
        }

        return eleves;
    }

    public static List<Intervenant> seedIntervenants(Long c) throws Exception {

        List<Intervenant> intervenants = new ArrayList<>();

        JpaUtil.ouvrirTransaction();

        for (int i = 0; i < c; i++) {
            Login login = new Login("email" + i, "password");
            Contact contact = new Contact("email", "telephone");

            Intervenant intervenant = new IntervenantEnseignant("nom", "prenom", login, contact, 1, 6, "college");

            FeaturesService.inscrireIntervenant(intervenant);

            intervenants.add(intervenant);
        }

        return intervenants;
    }

    public static List<Matiere> seedMatieres(Long c) throws Exception {

        List<Matiere> matieres = new ArrayList<>();

        for (int i = 0; i < c; i++) {
            Matiere matiere = new Matiere("matiere" + i);

            MatiereDao.create(matiere);

            matieres.add(matiere);
        }

        return matieres;
    }

    public static List<Seance> seedSeances(List<Eleve> eleves, List<Matiere> matieres) throws Exception {

        List<Seance> seances = new ArrayList<>();

        for (int i = 0; i < eleves.size(); i++) {

            Eleve eleve = eleves.get(i);

            Matiere matiere = matieres.get(i % matieres.size());

            Seance seance = FeaturesService.plannifierNouvelleSeance(eleve.getId(), matiere.getId(), "description");

            seances.add(seance);
        }

        return seances;
    }
}
