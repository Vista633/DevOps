package com.magasin.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

// servlet qui affiche la liste des articles disponibles
@WebServlet("/catalogue")
public class CatalogueServlet extends HttpServlet {

    // liste des articles en dur vu qu'on a pas de bdd
    public static final List<Article> CATALOGUE = Arrays.asList(
        new Article("ART-101", "Crayon graphite HB",     0.90,  "Ecriture"),
        new Article("ART-102", "Bloc-notes A5",          2.75,  "Papier"),
        new Article("ART-103", "Equerre 45 degres",      1.80,  "Geometrie"),
        new Article("ART-104", "Pochette plastique A4",  3.20,  "Rangement"),
        new Article("ART-105", "Sac bandouliere",       19.90,  "Bagagerie"),
        new Article("ART-106", "Trousse ronde",          6.50,  "Bagagerie"),
        new Article("ART-107", "Planning semainier",     8.40,  "Papier"),
        new Article("ART-108", "Calculatrice scientif.", 15.90,  "Materiel")
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("articles", CATALOGUE);
        req.getRequestDispatcher("/WEB-INF/jsp/catalogue.jsp")
           .forward(req, resp);
    }
}
