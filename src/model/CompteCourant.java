package model;

public class CompteCourant extends CompteBancaire {

    private double plafondDecouvert;

    public CompteCourant(String numero, Client client, double plafondDecouvert) {
        super(numero, client);
        this.plafondDecouvert = plafondDecouvert;
    }

    @Override
    public void retirer(double montant) {
        if (solde - montant < -plafondDecouvert) {
            throw new IllegalArgumentException("Dépassement du découvert autorisé !");
        }
        solde -= montant;
    }
}

