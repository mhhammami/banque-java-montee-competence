package model;

public abstract class CompteBancaire implements Comparable<CompteBancaire> {

    protected String numero;
    protected Client client;
    protected double solde;

    public CompteBancaire(String numero, Client client) {
        this.numero = numero;
        this.client = client;
        this.solde = 0.0;
    }

    public String getNumero() { return numero; }
    public Client getClient() { return client; }
    public double getSolde() { return solde; }

    public void deposer(double montant) {
        solde += montant;
    }

    public abstract void retirer(double montant);

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "numero='" + numero + '\'' +
                ", client=" + client.getNom() + " " + client.getPrenom() +
                ", solde=" + solde +
                '}';
    }

    @Override
    public int compareTo(CompteBancaire autre) {
        return Double.compare(this.solde, autre.solde);
    }
}
