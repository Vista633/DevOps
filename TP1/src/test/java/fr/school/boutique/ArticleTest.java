package fr.school.boutique;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {

    @Test
    void instancierAvecParametresCorrectsRetourneUnArticle() {
        Article produit = new Article("ART-100", "Trousse", 7.25);

        assertEquals("ART-100", produit.getReference());
        assertEquals("Trousse", produit.getNom());
        assertEquals(7.25, produit.getPrix(), 0.01);
    }

    @Test
    void changerLePrixMetAJourLaValeur() {
        Article produit = new Article("ART-200", "Compas", 4.80);
        produit.setPrix(5.20);
        assertEquals(5.20, produit.getPrix(), 0.01);
    }

    @Test
    void referenceAbsenteDeclencheUneErreur() {
        assertThrows(IllegalArgumentException.class,
                () -> new Article(null, "Gomme", 0.75));
    }

    @Test
    void nomBlancDeclencheUneErreur() {
        assertThrows(IllegalArgumentException.class,
                () -> new Article("ART-300", "   ", 2.0));
    }

    @Test
    void prixInferieurAZeroALaCreationDeclencheUneErreur() {
        assertThrows(IllegalArgumentException.class,
                () -> new Article("ART-400", "Equerre", -3.50));
    }

    @Test
    void mettreAJourAvecPrixNegatifDeclencheUneErreur() {
        Article produit = new Article("ART-500", "Feutre", 1.20);

        assertThrows(IllegalArgumentException.class,
                () -> produit.setPrix(-0.50));
    }

    @Test
    void prixAZeroEstAutorise() {
        Article gratuit = new Article("PROMO-01", "Echantillon", 0.0);
        assertEquals(0.0, gratuit.getPrix(), 0.01);
    }

    @Test
    void toStringContientLeNomEtLaReference() {
        Article produit = new Article("ART-600", "Surligneur", 2.30);
        String representation = produit.toString();

        assertTrue(representation.contains("Surligneur"));
        assertTrue(representation.contains("ART-600"));
    }
}
