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
        Personne intervenant = null;

        while (!exit) {
            System.out.println("=== Menu Intervenant ===");
            if (intervenant == null) {
                System.out.println("1. Login");
                System.out.println("2. Quitter");
            } else {
                System.out.println("1. Rédiger Bilan");
                System.out.println("2. Récupérer Historique Séances");
                System.out.println("3. Récupérer Profil Personne");
                System.out.println("4. Récupérer Séance en Cours");
                System.out.println("5. Déconnexion");
                System.out.println("6. Quitter");
            }

            int choix = Integer.parseInt(scanner.nextLine());

            try {
                if (intervenant == null) {
                    switch (choix) {
                        case 1:
                            intervenant = login(scanner);
                            break;
                        case 2:
                            exit = true;
                            break;
                        default:
                            System.out.println("Choix invalide, veuillez réessayer.");
                    }
                } else {
                    switch (choix) {
                        case 1:
                            redigerBilan(scanner, intervenant);
                            break;
                        case 2:
                            recupererHistoriqueSeances(scanner, intervenant);
                            break;
                        case 3:
                            recupererProfilePersonne(intervenant);
                            break;
                        case 4:
                            recupererSeanceEnCours(intervenant);
                            break;
                        case 5:
                            intervenant = null;
                            break;
                        case 6:
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

        Personne intervenant = FeaturesService.login(email, password);
        if (intervenant != null) {
            System.out.println("Connexion réussie.");
            return intervenant;
        } else {
            System.out.println("Email ou mot de passe incorrect.");
            return null;
        }
    }

    private static void redigerBilan(Scanner scanner, Personne intervenant) throws Exception {
        Seance seanceEnCours = FeaturesService.recupererSeanceEnCours(intervenant.getId());
        if (seanceEnCours != null) {
            System.out.print("Veuillez entrer le contenu du bilan: ");
            String contenu = scanner.nextLine();
            FeaturesService.redigerBilan(seanceEnCours.getId(), contenu);
            System.out.println("Bilan rédigé avec succès.");
        } else {
            System.out.println("Aucune séance en cours.");
        }
    }

    private static void recupererHistoriqueSeances(Scanner scanner, Personne intervenant) throws Exception {
        List<Seance> historique = FeaturesService.recupererHistoriqueSeances(intervenant.getId());
        for (Seance seance : historique) {
            System.out.println(seance);
        }
    }

    private static void recupererProfilePersonne(Personne intervenant) throws Exception {
        Personne profil = FeaturesService.recupererProfilePersonne(intervenant.getId());
        System.out.println(profil);
    }

    private static void recupererSeanceEnCours(Personne intervenant) throws Exception {
        Seance seanceEnCours = FeaturesService.recupererSeanceEnCours(intervenant.getId());
        if (seanceEnCours != null) {
            System.out.println(seanceEnCours);
        } else {
            System.out.println("Aucune séance en cours.");
        }
    }
}
