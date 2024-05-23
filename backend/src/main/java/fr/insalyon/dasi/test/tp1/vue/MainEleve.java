/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.vue;

import java.util.List;
import java.util.Scanner;

public class MainEleve {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("=== Menu Élève ===");
            System.out.println("1. Inscrire Élève");
            System.out.println("2. Plannifier Nouvelle Séance");
            System.out.println("3. Terminer Séance");
            System.out.println("4. Rédiger Note de Compréhension");
            System.out.println("5. Récupérer Historique Séances");
            System.out.println("6. Récupérer Profil Personne");
            System.out.println("7. Récupérer Séance en Cours");
            System.out.println("8. Quitter");

            int choix = Integer.parseInt(scanner.nextLine());

            try {
                switch (choix) {
                    case 1:
                        inscrireEleve(scanner);
                        break;
                    case 2:
                        plannifierNouvelleSeance(scanner);
                        break;
                    case 3:
                        terminerSeance(scanner);
                        break;
                    case 4:
                        redigerNoteComprehension(scanner);
                        break;
                    case 5:
                        recupererHistoriqueSeances(scanner);
                        break;
                    case 6:
                        recupererProfilePersonne(scanner);
                        break;
                    case 7:
                        recupererSeanceEnCours(scanner);
                        break;
                    case 8:
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

    private static void inscrireEleve(Scanner scanner) throws Exception {
        // Implémentation de l'inscription d'un élève
    }

    private static void plannifierNouvelleSeance(Scanner scanner) throws Exception {
        // Implémentation de la planification d'une nouvelle séance
    }

    private static void terminerSeance(Scanner scanner) throws Exception {
        System.out.print("Veuillez entrer votre ID d'élève: ");
        Long eleveId = Long.parseLong(scanner.nextLine());

        Seance seanceEnCours = FeaturesService.recupererSeanceEnCours(eleveId);
        if (seanceEnCours != null) {
            FeaturesService.terminerSeance(seanceEnCours.getId());
            System.out.println("Séance terminée avec succès.");
        } else {
            System.out.println("Aucune séance en cours.");
        }
    }

    private static void redigerNoteComprehension(Scanner scanner) throws Exception {
        System.out.print("Veuillez entrer votre ID d'élève: ");
        Long eleveId = Long.parseLong(scanner.nextLine());

        Seance seanceEnCours = FeaturesService.recupererSeanceEnCours(eleveId);
        if (seanceEnCours != null) {
            System.out.print("Veuillez entrer la note de compréhension (0-5): ");
            Integer note = Integer.parseInt(scanner.nextLine());
            FeaturesService.redigerNoteComprehension(seanceEnCours.getId(), note);
            System.out.println("Note de compréhension enregistrée avec succès.");
        } else {
            System.out.println("Aucune séance en cours.");
        }
    }

    private static void recupererHistoriqueSeances(Scanner scanner) throws Exception {
        System.out.print("Veuillez entrer votre ID d'élève: ");
        Long eleveId = Long.parseLong(scanner.nextLine());

        List<Seance> historique = FeaturesService.recupererHistoriqueSeances(eleveId);
        for (Seance seance : historique) {
            System.out.println(seance);
        }
    }

    private static void recupererProfilePersonne(Scanner scanner) throws Exception {
        System.out.print("Veuillez entrer votre ID d'élève: ");
        Long eleveId = Long.parseLong(scanner.nextLine());

        Personne profil = FeaturesService.recupererProfilePersonne(eleveId);
        System.out.println(profil);
    }

    private static void recupererSeanceEnCours(Scanner scanner) throws Exception {
        System.out.print("Veuillez entrer votre ID d'élève: ");
        Long eleveId = Long.parseLong(scanner.nextLine());

        Seance seanceEnCours = FeaturesService.recupererSeanceEnCours(eleveId);
        if (seanceEnCours != null) {
            System.out.println(seanceEnCours);
        } else {
            System.out.println("Aucune séance en cours.");
        }
    }
}
