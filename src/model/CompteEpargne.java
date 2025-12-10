package model;

public class CompteEpargne extends CompteBancaire {

    private double tauxInteret;

    public CompteEpargne(String numero, Client client, double tauxInteret) {
        super(numero, client);
        this.tauxInteret = tauxInteret;
    }

    public void appliquerInteret() {
        solde += solde * tauxInteret;
    }

    @Override
    public void retirer(double montant) {
        if (montant > solde) {
            throw new IllegalArgumentException("Solde insuffisant.");
        }
        solde -= montant;
    }
}

