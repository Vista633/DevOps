package fr.school.boutique;

// represente une ligne du panier avec un article et sa quantite
public record LigneCommande(Article article, int quantite) {
    // validation des donnees dans le constructeur compact
    public LigneCommande {
        if (article == null)
            throw new IllegalArgumentException("Un article valide est requis");
        if (quantite <= 0)
            throw new IllegalArgumentException("La quantité doit être supérieure à zéro");
    }

    // calcul du sous-total de la ligne
    public double sousTotal() {
        return article.getPrix() * quantite;
    }
}
