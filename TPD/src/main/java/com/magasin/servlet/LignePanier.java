package com.magasin.servlet;

// represente un article et sa quantite dans le panier
public class LignePanier {
    private final Article article;
    private int quantite;

    public LignePanier(Article article, int quantite) {
        this.article = article;
        this.quantite = quantite;
    }

    public Article getArticle()     { return article; }
    public int getQuantite()        { return quantite; }
    public void setQuantite(int q)  { this.quantite = q; }

    // calcul du sous total de la ligne
    public double getSousTotal()    { return article.getPrix() * quantite; }
}
