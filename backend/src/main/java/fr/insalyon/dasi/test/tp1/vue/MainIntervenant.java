/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.vue;

import fr.insalyon.dasi.test.tp1.metier.model.Personne;
import fr.insalyon.dasi.test.tp1.metier.model.Seance;
import fr.insalyon.dasi.test.tp1.metier.service.FeaturesService;
import java.util.List;

import java.util.Scanner;

public class MainIntervenant {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("=== Menu Intervenant ===");
            System.out.println("1. Rédiger Bilan");
            System.out.println("2. Récupérer Historique Séances");
            System.out.println("3. Récupérer Profil Personne");
            System.out.println("4. Récupérer Séance en Cours");
            System.out.println("5. Quitter");

            int choix = Integer.parseInt(scanner.nextLine());

            try {
                switch (choix) {
                    case 1:
                        redigerBilan(scanner);
                        break;
                    case 2:
                        recupererHistoriqueSeances(scanner);
                        break;
                    case 3:
                        recupererProfilePersonne(scanner);
                        break;
                    case 4:
                        recupererSeanceEnCours(scanner);
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Choix invalide, veuillez réessayer.");
                }
            } catch (Exception e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void redigerBilan(Scanner scanner) throws Exception {
        System.out.print("Veuillez entrer votre ID d'intervenant: ");
        Long intervenantId = Long.parseLong(scanner.nextLine());

        Seance seanceEnCours = FeaturesService.recupererSeanceEnCours(intervenantId);
        if (seanceEnCours != null) {
            System.out.print("Veuillez entrer le contenu du bilan: ");
            String contenu = scanner.nextLine();
            FeaturesService.redigerBilan(seanceEnCours.getId(), contenu);
            System.out.println("Bilan rédigé avec succès.");
        } else {
            System.out.println("Aucune séance en cours.");
        }
    }

    private static void recupererHistoriqueSeances(Scanner scanner) throws Exception {
        System.out.print("Veuillez entrer votre ID d'intervenant: ");
        Long intervenantId = Long.parseLong(scanner.nextLine());

        List<Seance> historique = FeaturesService.recupererHistoriqueSeances(intervenantId);
        for (Seance seance : historique) {
            System.out.println(seance);
        }
    }

    private static void recupererProfilePersonne(Scanner scanner) throws Exception {
        System.out.print("Veuillez entrer votre ID d'intervenant: ");
        Long intervenantId = Long.parseLong(scanner.nextLine());

        Personne profil = FeaturesService.recupererProfilePersonne(intervenantId);

        System.out.println(profil);
    }

    private static void recupererSeanceEnCours(Scanner scanner) throws Exception {
        System.out.print("Veuillez entrer votre ID d'intervenant: ");
        Long intervenantId = Long.parseLong(scanner.nextLine());

        Seance seanceEnCours = FeaturesService.recupererSeanceEnCours(intervenantId);
        if (seanceEnCours != null) {
            System.out.println(seanceEnCours);
        } else {
            System.out.println("Aucune séance en cours.");
        }
    }
}
