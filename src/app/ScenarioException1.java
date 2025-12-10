package app;

import model.Client;
import model.CompteEpargne;
import service.Banque;

import java.time.LocalDate;

public class ScenarioException1 {
    public static void main(String[] args) {

        Banque banque = new Banque();

        Client c = new Client("10", "Error", "Test",
                LocalDate.of(1995, 10, 10), "test@mail.com", "0600000000");

        banque.ajouterClient(c);

        banque.ouvrirCompte(new CompteEpargne("E001", c, 0.02));

        banque.deposer("E001", 100);

        System.out.println("Tentative de retrait de 500€ sur un solde de 100€...");

        try {
            banque.retirer("E001", 500);
        } catch (Exception e) {
            System.out.println("EXCEPTION CAPTURÉE : " + e.getMessage());
        }
    }
}

