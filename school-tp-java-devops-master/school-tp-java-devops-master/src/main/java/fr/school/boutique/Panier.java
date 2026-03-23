package fr.school.boutique;

import java.util.ArrayList;
import java.util.List;

// le panier contient les lignes de commande et gere les reductions
public class Panier {
    private List<LigneCommande> lignes = new ArrayList<>();
    private String codeReduction = null;

    // ajoute un article avec sa quantite dans le panier
    public void ajouterArticle(Article article, int quantite) {
        if (article == null)
            throw new IllegalArgumentException("L'article fourni est invalide");
        if (quantite <= 0)
            throw new IllegalArgumentException("Merci d'indiquer une quantité strictement positive");
        lignes.add(new LigneCommande(article, quantite));
    }

    // applique un code promo si il est valide
    public void appliquerCodeReduction(String code) {
        if (code == null || code.isBlank())
            throw new IllegalArgumentException("Veuillez saisir un code de réduction valide");
        this.codeReduction = code;
    }

    // calcule le total en appliquant la reduction si il y en a une
    public double calculerTotal() {
        double sousTotal = lignes.stream().mapToDouble(LigneCommande::sousTotal).sum();
        if ("REDUC10".equals(codeReduction))
            return sousTotal * 0.90;
        if ("REDUC20".equals(codeReduction))
            return sousTotal * 0.80;
        return sousTotal;
    }

    // retourne le nombre de lignes dans le panier
    public int nombreArticles() {
        return lignes.size();
    }

    public boolean estVide() {
        return lignes.isEmpty();
    }

    public List<LigneCommande> getLignes() {
        return lignes;
    }
}
