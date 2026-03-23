package fr.school.boutique;

import java.time.LocalDateTime;

// record pour stocker les infos de la commande validee
public record Commande(String identifiantClient, double total, LocalDateTime dateCreation) {}
