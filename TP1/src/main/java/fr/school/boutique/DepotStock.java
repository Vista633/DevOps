package fr.school.boutique;

// interface pour gerer le stock
// on l'utilise dans ServiceCommande pour pouvoir mocker dans les tests
public interface DepotStock {
    int getStock(String referenceArticle);
}
