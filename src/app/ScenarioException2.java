package app;

import model.Client;
import model.CompteCourant;
import service.Banque;

import java.time.LocalDate;

public class ScenarioException2 {
    public static void main(String[] args) {

        Banque banque = new Banque();

        Client c = new Client("20", "Test", "Decouvert",
                LocalDate.of(1992, 3, 15), "dec@mail.com", "0700000000");

        banque.ajouterClient(c);

        banque.ouvrirCompte(new CompteCourant("CC100", c, 100)); // découvert max = 100€

        System.out.println("Tentative de retrait de 500€ sur un découvert max de 100€...");

        try {
            banque.retirer("CC100", 500);
        } catch (Exception e) {
            System.out.println("EXCEPTION CAPTURÉE : " + e.getMessage());
        }
    }
}

