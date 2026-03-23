package fr.school.boutique;

import java.time.LocalDateTime;

// service qui s'occupe de valider et passer les commandes
public class ServiceCommande {
    private final DepotStock depotStock;

    public ServiceCommande(DepotStock depotStock) {
        this.depotStock = depotStock;
    }

    // verifie que le panier est pas vide, que le client est valide et que le stock est suffisant
    // puis cree la commande
    public Commande passerCommande(Panier panier, String identifiantClient) {
        if (panier.estVide())
            throw new IllegalStateException("La commande ne peut pas être validée avec un panier vide");
        if (identifiantClient == null || identifiantClient.isBlank())
            throw new IllegalArgumentException("Un identifiant client valide est requis");

        // on parcourt chaque ligne pour verifier le stock
        for (LigneCommande ligne : panier.getLignes()) {
            int stockDisponible = depotStock.getStock(ligne.article().getReference());
            if (stockDisponible < ligne.quantite())
                throw new StockInsuffisantException("Rupture de stock pour l'article : " + ligne.article().getNom());
        }
        return new Commande(identifiantClient, panier.calculerTotal(), LocalDateTime.now());
    }
}
