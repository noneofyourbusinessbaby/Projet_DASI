/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.vue;

import java.util.List;
import java.util.Scanner;

import fr.insalyon.dasi.test.tp1.metier.model.Contact;
import fr.insalyon.dasi.test.tp1.metier.model.Login;
import fr.insalyon.dasi.test.tp1.metier.model.Personne;
import fr.insalyon.dasi.test.tp1.metier.model.Seance;
import fr.insalyon.dasi.test.tp1.metier.model.personne.Eleve;
import fr.insalyon.dasi.test.tp1.metier.model.personne.Intervenant;
import fr.insalyon.dasi.test.tp1.metier.model.personne.intervenant.IntervenantEnseignant;
import fr.insalyon.dasi.test.tp1.metier.service.FeaturesService;

public class MainCLI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Inscrire un intervenant");
            System.out.println("2. Inscrire un élève");
            System.out.println("3. Connexion");
            System.out.println("4. Planifier une nouvelle séance");
            System.out.println("5. Terminer une séance");
            System.out.println("6. Rédiger un bilan");
            System.out.println("7. Récupérer historique des séances");
            System.out.println("8. Récupérer profil personne");
            System.out.println("9. Récupérer séance en cours");
            System.out.println("0. Quitter");
            System.out.print("Choisissez une option : ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                        inscrireIntervenant(scanner);
                        break;
                    case 2:
                        inscrireEleve(scanner);
                        break;
                    case 3:
                        login(scanner);
                        break;
                    case 4:
                        planifierNouvelleSeance(scanner);
                        break;
                    case 5:
                        terminerSeance(scanner);
                        break;
                    case 6:
                        redigerBilan(scanner);
                        break;
                    case 7:
                        recupererHistoriqueSeances(scanner);
                        break;
                    case 8:
                        recupererProfilePersonne(scanner);
                        break;
                    case 9:
                        recupererSeanceEnCours(scanner);
                        break;
                    case 0:
                        System.out.println("Au revoir!");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Option invalide. Veuillez réessayer.");
                }
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
    }

    private static void inscrireIntervenant(Scanner scanner) throws Exception {
        System.out.print("Nom: ");
        String nom = scanner.nextLine();

        System.out.print("Prénom: ");
        String prenom = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Mot de passe: ");
        String password = scanner.nextLine();

        Intervenant intervenant = new IntervenantEnseignant(nom, prenom, new Login(email, password),
                new Contact(email, "telephone"), 0, 6, "College");

        FeaturesService.inscrireIntervenant(intervenant);

        System.out.println("Intervenant inscrit avec succès.");
    }

    private static void inscrireEleve(Scanner scanner) throws Exception {
        System.out.print("Nom: ");
        String nom = scanner.nextLine();

        System.out.print("Prénom: ");
        String prenom = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Mot de passe: ");
        String password = scanner.nextLine();

        System.out.print("Code Etablissement: ");
        String codeEtablissement = scanner.nextLine();

        System.out.print("Classe: ");
        int classe = scanner.nextInt();

        Eleve eleve = new Eleve(nom, prenom, new Login(email, password), new Contact(email, "telephone"));
        FeaturesService.inscrireEleve(eleve, codeEtablissement, classe);

        System.out.println("Élève inscrit avec succès.");
    }

    private static void login(Scanner scanner) throws Exception {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Mot de passe: ");
        String password = scanner.nextLine();
        Personne personne = FeaturesService.login(email, password);
        System.out.println("Connecté en tant que " + personne.getNom());
    }

    private static void planifierNouvelleSeance(Scanner scanner) throws Exception {
        System.out.print("ID Élève: ");
        long idEleve = scanner.nextLong();
        System.out.print("ID Matière: ");
        long idMatiere = scanner.nextLong();
        scanner.nextLine(); // Consomme la nouvelle ligne
        System.out.print("Description: ");
        String description = scanner.nextLine();
        Seance seance = FeaturesService.plannifierNouvelleSeance(idEleve, idMatiere, description);
        System.out.println("Séance planifiée avec succès.");
    }

    private static void terminerSeance(Scanner scanner) throws Exception {
        System.out.print("ID Séance: ");
        long seanceId = scanner.nextLong();
        System.out.print("Note: ");
        int note = scanner.nextInt();
        scanner.nextLine(); // Consomme la nouvelle ligne
        FeaturesService.terminerSeance(seanceId, note);
        System.out.println("Séance terminée avec succès.");
    }

    private static void redigerBilan(Scanner scanner) throws Exception {
        System.out.print("ID Séance: ");
        long seanceId = scanner.nextLong();
        scanner.nextLine(); // Consomme la nouvelle ligne
        System.out.print("Contenu: ");
        String contenu = scanner.nextLine();
        FeaturesService.redigerBilan(seanceId, contenu);
        System.out.println("Bilan rédigé avec succès.");
    }

    private static void recupererHistoriqueSeances(Scanner scanner) throws Exception {
        System.out.print("ID Personne: ");
        long personneId = scanner.nextLong();
        scanner.nextLine(); // Consomme la nouvelle ligne
        List<Seance> seances = FeaturesService.recupererHistoriqueSeances(personneId);
        System.out.println("Historique des séances:");
        for (Seance seance : seances) {
            System.out.println(seance);
        }
    }

    private static void recupererProfilePersonne(Scanner scanner) throws Exception {
        System.out.print("ID Personne: ");
        long personneId = scanner.nextLong();
        scanner.nextLine(); // Consomme la nouvelle ligne
        Personne personne = FeaturesService.recupererProfilePersonne(personneId);
        System.out.println("Profil de la personne: " + personne);
    }

    private static void recupererSeanceEnCours(Scanner scanner) throws Exception {
        System.out.print("ID Personne: ");
        long personneId = scanner.nextLong();
        scanner.nextLine(); // Consomme la nouvelle ligne
        Seance seance = FeaturesService.recupererSeanceEnCours(personneId);
        System.out.println("Séance en cours: " + seance);
    }
}
