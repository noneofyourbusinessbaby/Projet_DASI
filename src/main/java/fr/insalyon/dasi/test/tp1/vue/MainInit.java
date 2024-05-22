/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.vue;

import java.util.List;

import fr.insalyon.dasi.test.tp1.metier.model.Matiere;
import fr.insalyon.dasi.test.tp1.metier.model.Seance;
import fr.insalyon.dasi.test.tp1.metier.model.personne.Eleve;
import fr.insalyon.dasi.test.tp1.metier.model.personne.Intervenant;
import fr.insalyon.dasi.test.tp1.metier.service.SeedService;

/**
 *
 * @author nhajjhassa
 */
public class MainInit {

    /**
     * @param args the command line arguments
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

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

}
