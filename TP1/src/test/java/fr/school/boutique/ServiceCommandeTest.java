package fr.school.boutique;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ServiceCommandeTest {

    private DepotStock entrepotPlein = ref -> 50;
    private DepotStock entrepotVide = ref -> 0;

    private ServiceCommande gestionnaire;
    private Panier caddie;
    private Article produit;

    @BeforeEach
    void preparerContexte() {
        gestionnaire = new ServiceCommande(entrepotPlein);
        caddie       = new Panier();
        produit      = new Article("PRD-055", "Classeur souple", 4.75);
    }

    @Test
    void passerUneCommandeValideRetourneUneCommande() {
        caddie.ajouterArticle(produit, 3);

        Commande resultat = gestionnaire.passerCommande(caddie, "USR-007");

        assertNotNull(resultat);
        assertEquals(14.25, resultat.total(), 0.01);
    }

    @Test
    void commanderAvecPanierVideDeclencheErreurEtat() {
        assertThrows(IllegalStateException.class,
                () -> gestionnaire.passerCommande(caddie, "USR-008"));
    }

    @Test
    void clientNullDeclencheErreurArgument() {
        caddie.ajouterArticle(produit, 2);

        assertThrows(IllegalArgumentException.class,
                () -> gestionnaire.passerCommande(caddie, null));
    }

    @Test
    void clientVideDeclencheErreurArgument() {
        caddie.ajouterArticle(produit, 2);

        assertThrows(IllegalArgumentException.class,
                () -> gestionnaire.passerCommande(caddie, "  "));
    }

    @Test
    void stockEpuiseDeclencheExceptionStock() {
        ServiceCommande gestionnaireVide = new ServiceCommande(entrepotVide);
        caddie.ajouterArticle(produit, 3);

        assertThrows(StockInsuffisantException.class,
                () -> gestionnaireVide.passerCommande(caddie, "USR-009"));
    }

    @Test
    void montantCommandeCorrespondAuMontantDuPanier() {
        caddie.ajouterArticle(produit, 4);
        caddie.appliquerCodeReduction("REDUC20");

        Commande resultat = gestionnaire.passerCommande(caddie, "USR-010");

        assertEquals(caddie.calculerTotal(), resultat.total(), 0.01);
    }

    @Test
    void dateDeCreationEstRenseignee() {
        caddie.ajouterArticle(produit, 1);

        Commande resultat = gestionnaire.passerCommande(caddie, "USR-011");

        assertNotNull(resultat.dateCreation());
    }

    @Test
    void identifiantClientEstConserveDansLaCommande() {
        caddie.ajouterArticle(produit, 1);

        Commande resultat = gestionnaire.passerCommande(caddie, "USR-012");

        assertEquals("USR-012", resultat.identifiantClient());
    }
}
