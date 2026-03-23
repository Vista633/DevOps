package fr.school.boutique;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class PanierReductionTest {

    @ParameterizedTest
    @CsvSource({
            "       , 50.0",
            "REDUC10, 45.0",
            "REDUC20, 40.0"
    })
    void verifierApplicationDuCodePromoSurUnArticle(String codePromo, double montantAttendu) {
        Panier p = new Panier();
        Article marker = new Article("MK-01", "Marqueur permanent", 5.0);
        p.ajouterArticle(marker, 10); // sous-total = 50.0

        if (codePromo != null && !codePromo.isBlank()) {
            p.appliquerCodeReduction(codePromo.trim());
        }

        assertEquals(montantAttendu, p.calculerTotal(), 0.01);
    }

    @ParameterizedTest
    @CsvSource({
            "       , 27.0",
            "REDUC10, 24.3",
            "REDUC20, 21.6"
    })
    void verifierReductionAvecPanierMixte(String codePromo, double montantAttendu) {
        Panier p = new Panier();
        Article stylo = new Article("ST-05", "Stylo plume", 4.50);
        Article cahier = new Article("CA-08", "Cahier 96 pages", 3.00);
        p.ajouterArticle(stylo, 4);    // 18.0
        p.ajouterArticle(cahier, 3);   // 9.0

        if (codePromo != null && !codePromo.isBlank()) {
            p.appliquerCodeReduction(codePromo.trim());
        }

        assertEquals(montantAttendu, p.calculerTotal(), 0.01);
    }
}
