package fr.school.boutique;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PanierTest {

    @Test
    void ajouterUnProduitIncrementeLeCompteur() {
        Panier p = new Panier();
        Article crayon = new Article("P-010", "Crayon HB", 0.80);
        p.ajouterArticle(crayon, 4);

        assertEquals(1, p.nombreArticles());
    }

    @Test
    void totalCorrespondALaSommeDesSousTotaux() {
        Panier p = new Panier();
        Article encre = new Article("P-020", "Cartouche encre", 3.00);
        Article papier = new Article("P-021", "Ramette A4", 5.50);
        p.ajouterArticle(encre, 3);
        p.ajouterArticle(papier, 2);

        assertEquals(20.00, p.calculerTotal(), 0.01);
    }

    @Test
    void panierSansArticleEstConsidereVide() {
        Panier p = new Panier();
        assertTrue(p.estVide());
    }

    @Test
    void panierRempliNEstPasVide() {
        Panier p = new Panier();
        Article scotch = new Article("P-030", "Ruban adhesif", 1.75);

        p.ajouterArticle(scotch, 2);
        assertFalse(p.estVide());
    }

    @Test
    void ajouterArticleNullDeclencheErreur() {
        Panier p = new Panier();
        assertThrows(IllegalArgumentException.class,
                () -> p.ajouterArticle(null, 3));
    }

    @Test
    void quantiteAZeroDeclencheErreur() {
        Panier p = new Panier();
        Article colle = new Article("P-040", "Tube de colle", 2.10);
        assertThrows(IllegalArgumentException.class,
                () -> p.ajouterArticle(colle, 0));
    }

    @Test
    void quantiteNegativeDeclencheErreur() {
        Panier p = new Panier();
        Article ciseaux = new Article("P-050", "Ciseaux", 3.40);
        assertThrows(IllegalArgumentException.class,
                () -> p.ajouterArticle(ciseaux, -2));
    }

    @Test
    void codePromoVideDeclencheErreur() {
        Panier p = new Panier();
        assertThrows(IllegalArgumentException.class,
                () -> p.appliquerCodeReduction(""));
    }

    @Test
    void codePromoNullDeclencheErreur() {
        Panier p = new Panier();
        assertThrows(IllegalArgumentException.class,
                () -> p.appliquerCodeReduction(null));
    }

    @Test
    void ajouterExactementUnExemplaireFonctionne() {
        Panier p = new Panier();
        Article carnet = new Article("P-060", "Carnet A5", 4.50);

        p.ajouterArticle(carnet, 1);

        assertEquals(4.50, p.calculerTotal(), 0.01);
    }

    @Test
    void articleAPrixZeroNeModifiePasLeTotal() {
        Panier p = new Panier();
        Article cadeau = new Article("GIFT-01", "Marque-page offert", 0.0);

        p.ajouterArticle(cadeau, 3);

        assertEquals(0.0, p.calculerTotal(), 0.01);
    }

    @Test
    void articleCherEstBienPrisEnCompte() {
        Panier p = new Panier();
        Article tablette = new Article("TECH-01", "Tablette graphique", 349.99);

        p.ajouterArticle(tablette, 1);

        assertEquals(349.99, p.calculerTotal(), 0.01);
    }

    @Test
    void unSeulArticleDonneUnCompteurDeUn() {
        Panier p = new Panier();
        Article roman = new Article("LIV-01", "Roman policier", 8.90);

        p.ajouterArticle(roman, 5);

        assertEquals(1, p.nombreArticles());
    }

    @Test
    void melangerPlusieursProduitsCalculeCorrectement() {
        Panier p = new Panier();
        Article gomme = new Article("P-070", "Gomme blanche", 0.60);
        Article taille = new Article("P-071", "Taille-crayon", 1.40);
        Article classeur = new Article("P-072", "Classeur A4", 4.00);

        p.ajouterArticle(gomme, 3);       // 1.80
        p.ajouterArticle(taille, 2);      // 2.80
        p.ajouterArticle(classeur, 1);    // 4.00

        assertEquals(3, p.nombreArticles());
        assertEquals(8.60, p.calculerTotal(), 0.01);
    }
}
