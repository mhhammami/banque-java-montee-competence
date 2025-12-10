package app;

import model.*;
import service.Banque;
import java.time.LocalDate;

public class ApplicationBanque {
    public static void main(String[] args) {

        Banque banque = new Banque();

        // === CLIENTS ===
        Client c1 = new Client("1", "Doe", "John",
                LocalDate.of(1990, 5, 12), "john@mail.com", "0601020304");

        Client c2 = new Client("2", "Dupont", "Julie",
                LocalDate.of(1988, 9, 3), "julie@mail.com", "0605060708");

        Client c3 = new Client("3", "Martin", "Lucas",
                LocalDate.of(2002, 1, 25), "lucas@mail.com", "0611223344");

        banque.ajouterClient(c1);
        banque.ajouterClient(c2);
        banque.ajouterClient(c3);

        // === COMPTES ===
        banque.ouvrirCompte(new CompteCourant("CC001", c1, 500));  // découvert autorisé
        banque.ouvrirCompte(new CompteEpargne("CE001", c1, 0.02));

        banque.ouvrirCompte(new CompteCourant("CC002", c2, 300));
        banque.ouvrirCompte(new CompteEpargne("CE002", c2, 0.03));

        banque.ouvrirCompte(new CompteCourant("CC003", c3, 100));

        // === OPÉRATIONS SUR 3 COMPTES ===

        // --- Client 1 ---
        banque.deposer("CC001", 500);
        banque.deposer("CC001", 100);
        banque.retirer("CC001", 250);

        banque.deposer("CE001", 2000);
        banque.deposer("CE001", 500);
        // Appliquer les intérêts
        banque.chercherCompte("CE001").ifPresent(c -> ((CompteEpargne) c).appliquerInteret());

        // --- Client 2 ---
        banque.deposer("CC002", 1000);
        banque.retirer("CC002", 150);
        banque.retirer("CC002", 50);
        banque.deposer("CE002", 300);

        // --- Client 3 ---
        banque.deposer("CC003", 100);
        banque.deposer("CC003", 200);
        banque.retirer("CC003", 50);
        banque.retirer("CC003", 300); // Doit passer en découvert mais autorisé (jusqu'à -100)
        banque.deposer("CC003", 500);

        // === AFFICHAGES ===

        System.out.println("\n==== LISTE DES COMPTES ====");
        banque.getComptes().forEach(System.out::println);

        System.out.println("\nSolde total de la banque : " + banque.soldeTotal());

        banque.compteLePlusRiche()
                .ifPresent(c -> System.out.println("Compte le plus riche : " + c));


        banque.clientLePlusAge()
                .ifPresent(c -> System.out.println("Client le plus âgé : " + c));

        System.out.println("\nComptes à découvert :");
        banque.comptesADecouvert().forEach(System.out::println);
    }
}