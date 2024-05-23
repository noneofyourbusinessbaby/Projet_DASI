/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.vue;

import fr.insalyon.dasi.test.tp1.metier.dao.JpaUtil;
import fr.insalyon.dasi.test.tp1.metier.model.Contact;
import fr.insalyon.dasi.test.tp1.metier.model.Login;
import fr.insalyon.dasi.test.tp1.metier.model.Personne;
import fr.insalyon.dasi.test.tp1.metier.model.Seance;
import fr.insalyon.dasi.test.tp1.metier.model.personne.Eleve;
import fr.insalyon.dasi.test.tp1.metier.service.FeaturesService;
import java.util.List;
import java.util.Scanner;

public class MainEleve {

    public static void main(String[] args) {

        JpaUtil.creerFabriquePersistance();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        Personne eleve = null;

        while (!exit) {
            System.out.println("=== Menu Élève ===");
            if (eleve == null) {
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println("3. Quitter");
            } else {
                System.out.println("1. Inscrire Élève");
                System.out.println("2. Terminer Séance");
                System.out.println("3. Rédiger Note de Compréhension");
                System.out.println("4. Récupérer Historique Séances");
                System.out.println("5. Récupérer Profil Personne");
                System.out.println("6. Récupérer Séance en Cours");
                System.out.println("7. Déconnexion");
                System.out.println("8. Quitter");
            }

            int choix = Integer.parseInt(scanner.nextLine());

            try {
                if (eleve == null) {
                    switch (choix) {
                        case 1:
                            eleve = login(scanner);
                            break;
                        case 2:
                            inscrireEleve(scanner);
                            break;
                        case 3:
                            exit = true;
                            break;
                        default:
                            System.out.println("Choix invalide, veuillez réessayer.");
                    }
                } else {
                    switch (choix) {
                        case 1:
                            inscrireEleve(scanner);
                            break;
                        case 2:
                            terminerSeance(scanner, eleve);
                            break;
                        case 3:
                            redigerNoteComprehension(scanner, eleve);
                            break;
                        case 4:
                            recupererHistoriqueSeances(scanner, eleve);
                            break;
                        case 5:
                            recupererProfilePersonne(eleve);
                            break;
                        case 6:
                            recupererSeanceEnCours(eleve);
                            break;
                        case 7:
                            eleve = null;
                            break;
                        case 8:
                            exit = true;
                            break;
                        default:
                            System.out.println("Choix invalide, veuillez réessayer.");
                    }
                }
            } catch (Exception e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static Personne login(Scanner scanner) throws Exception {
        System.out.print("Veuillez entrer votre email: ");
        String email = scanner.nextLine();
        System.out.print("Veuillez entrer votre mot de passe: ");
        String password = scanner.nextLine();

        Personne eleve = FeaturesService.login(email, password);
        if (eleve != null) {
            System.out.println("Connexion réussie.");
            return eleve;
        } else {
            System.out.println("Email ou mot de passe incorrect.");
            return null;
        }
    }

    private static void inscrireEleve(Scanner scanner) throws Exception {
        System.out.print("Veuillez entrer le nom: ");
        String nom = scanner.nextLine();

        System.out.print("Veuillez entrer le prenom: ");
        String prenom = scanner.nextLine();

        System.out.print("Veuillez entrer le email: ");
        String email = scanner.nextLine();

        System.out.print("Veuillez entrer le numéro de téléphone: ");
        String phone = scanner.nextLine();

        Contact contact = new Contact(email, phone);

        System.out.print("Veuillez entrer le code établissement du collége: ");
        String code = scanner.nextLine();

        System.out.print("Veuillez entrer le numéro de classe au collége (1-6): ");
        Integer classe = Integer.parseInt(scanner.nextLine());

        System.out.print("Veuillez entrer le password: ");
        String password = scanner.nextLine();

        Login login = new Login(email, password);

        Eleve eleve = new Eleve(nom, prenom, login, contact);

        FeaturesService.inscrireEleve(eleve, code, classe);
    }

    private static void terminerSeance(Scanner scanner, Personne eleve) throws Exception {
        Seance seanceEnCours = FeaturesService.recupererSeanceEnCours(eleve.getId());
        if (seanceEnCours != null) {
            FeaturesService.terminerSeance(seanceEnCours.getId());
            System.out.println("Séance terminée avec succès.");
        } else {
            System.out.println("Aucune séance en cours.");
        }
    }

    private static void redigerNoteComprehension(Scanner scanner, Personne eleve) throws Exception {
        Seance seanceEnCours = FeaturesService.recupererSeanceEnCours(eleve.getId());
        if (seanceEnCours != null) {
            System.out.print("Veuillez entrer la note de compréhension (0-5): ");
            Integer note = Integer.parseInt(scanner.nextLine());
            FeaturesService.redigerNoteComprehension(seanceEnCours.getId(), note);
            System.out.println("Note de compréhension enregistrée avec succès.");
        } else {
            System.out.println("Aucune séance en cours.");
        }
    }

    private static void recupererHistoriqueSeances(Scanner scanner, Personne eleve) throws Exception {
        List<Seance> historique = FeaturesService.recupererHistoriqueSeances(eleve.getId());
        for (Seance seance : historique) {
            System.out.println(seance);
        }
    }

    private static void recupererProfilePersonne(Personne eleve) throws Exception {
        Personne profil = FeaturesService.recupererProfilePersonne(eleve.getId());
        System.out.println(profil);
    }

    private static void recupererSeanceEnCours(Personne eleve) throws Exception {
        Seance seanceEnCours = FeaturesService.recupererSeanceEnCours(eleve.getId());
        if (seanceEnCours != null) {
            System.out.println(seanceEnCours);
        } else {
            System.out.println("Aucune séance en cours.");
        }
    }
}
