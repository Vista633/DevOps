package com.magasin.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Map;

// gere la connexion des utilisateurs
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    // comptes en dur pour le tp, pas de base de donnees
    private static final Map<String, String> COMPTES = Map.of(
        "alice",  "alice123",
        "bob",    "bob456",
        "admin",  "admin"
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // si l'utilisateur est deja connecte on redirige vers le catalogue
        if (req.getSession().getAttribute("utilisateur") != null) {
            resp.sendRedirect(req.getContextPath() + "/catalogue");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp")
           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String login    = req.getParameter("login");
        String password = req.getParameter("password");

        String mdpAttendu = COMPTES.get(login);

        if (mdpAttendu != null && mdpAttendu.equals(password)) {
            // authentification reussie, on stocke le user en session
            req.getSession().setAttribute("utilisateur", login);
            resp.sendRedirect(req.getContextPath() + "/catalogue");
        } else {
            // mauvais identifiants
            req.setAttribute("erreur", "Identifiants incorrects. Veuillez réessayer.");
            req.setAttribute("loginSaisi", login);
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp")
               .forward(req, resp);
        }
    }
}
