package fr.school.boutique;

// classe qui represente un article de la boutique
public class Article {
    private final String reference;
    private final String nom;
    private double prix;

    // constructeur avec verification des parametres
    public Article(String reference, String nom, double prix) {
        if (reference == null || reference.isBlank())
            throw new IllegalArgumentException("Veuillez fournir une référence valide");
        if (nom == null || nom.isBlank())
            throw new IllegalArgumentException("Le nom de l'article est obligatoire");
        if (prix < 0)
            throw new IllegalArgumentException("Un prix négatif n'est pas autorisé");
        this.reference = reference;
        this.nom = nom;
        this.prix = prix;
    }

    // getters
    public String getReference() {return reference;}
    public String getNom() {return nom;}
    public double getPrix() {return prix;}

    // modifie le prix, on verifie que c'est pas negatif
    public void setPrix(double nouveauPrix) {
        if (nouveauPrix < 0)
            throw new IllegalArgumentException("Impossible d'attribuer un prix négatif");
        this.prix = nouveauPrix;
    }

    // affichage sous forme de texte
    @Override
    public String toString() {
        return reference + " - " + nom + " : " + prix + " EUR";
    }
}
