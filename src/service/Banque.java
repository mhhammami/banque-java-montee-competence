package service;

import model.*;
import java.util.*;
import java.util.stream.Collectors;

public class Banque {

    private List<Client> clients = new ArrayList<>();
    private List<CompteBancaire> comptes = new ArrayList<>();

    public void ajouterClient(Client client) {
        clients.add(client);
    }

    public CompteBancaire ouvrirCompte(CompteBancaire compte) {
        comptes.add(compte);
        return compte;
    }

    public List<CompteBancaire> getComptes() {
        return comptes;
    }

    // Recherche avec Optional
    public Optional<CompteBancaire> chercherCompte(String numero) {
        return comptes.stream()
                .filter(c -> c.getNumero().equals(numero))
                .findFirst();
    }

    // Opérations
    public void deposer(String numero, double montant) {
        CompteBancaire c = chercherCompte(numero)
                .orElseThrow(() -> new IllegalArgumentException("Compte introuvable"));
        c.deposer(montant);
    }

    public void retirer(String numero, double montant) {
        CompteBancaire c = chercherCompte(numero)
                .orElseThrow(() -> new IllegalArgumentException("Compte introuvable"));
        c.retirer(montant);
    }

    // STREAMS
    public List<CompteBancaire> comptesADecouvert() {
        return comptes.stream()
                .filter(c -> c.getSolde() < 0)
                .collect(Collectors.toList());
    }

    public double soldeTotal() {
        return comptes.stream()
                .mapToDouble(CompteBancaire::getSolde)
                .sum();
    }

    public Optional<CompteBancaire> compteLePlusRiche() {
        return comptes.stream()
                //.max(Comparator.comparingDouble(CompteBancaire::getSolde));
                .max(Comparator.naturalOrder());
    }

    public Optional<Client> clientLePlusAge() {
        return clients.stream()
                .min(Comparator.comparing(Client::getDateNaissance));
    }


}
