package com.magasin.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

// servlet qui gere l'affichage et les actions sur le panier
@WebServlet("/panier")
public class PanierServlet extends HttpServlet {

    // affiche le contenu du panier avec le total
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Map<String, LignePanier> panier = getPanier(req);
        double total = panier.values().stream()
                             .mapToDouble(LignePanier::getSousTotal)
                             .sum();

        req.setAttribute("panier", panier);
        req.setAttribute("total", total);
        req.getRequestDispatcher("/WEB-INF/jsp/panier.jsp")
           .forward(req, resp);
    }

    // traite les actions : ajouter, supprimer ou vider le panier
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action    = req.getParameter("action");
        String reference = req.getParameter("reference");

        Map<String, LignePanier> panier = getPanier(req);

        if ("ajouter".equals(action) && reference != null) {
            // on cherche l'article dans le catalogue
            Article article = CatalogueServlet.CATALOGUE.stream()
                .filter(a -> a.getReference().equals(reference))
                .findFirst().orElse(null);

            if (article != null) {
                if (panier.containsKey(reference)) {
                    // article deja present, on incremente la quantite
                    LignePanier ligne = panier.get(reference);
                    ligne.setQuantite(ligne.getQuantite() + 1);
                } else {
                    panier.put(reference, new LignePanier(article, 1));
                }
            }
        } else if ("supprimer".equals(action) && reference != null) {
            panier.remove(reference);
        } else if ("vider".equals(action)) {
            panier.clear();
        }

        resp.sendRedirect(req.getContextPath() + "/panier");
    }

    // recupere le panier depuis la session, le cree si il existe pas encore
    @SuppressWarnings("unchecked")
    private Map<String, LignePanier> getPanier(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Map<String, LignePanier> panier =
            (Map<String, LignePanier>) session.getAttribute("panier");
        if (panier == null) {
            panier = new LinkedHashMap<>();
            session.setAttribute("panier", panier);
        }
        return panier;
    }
}
