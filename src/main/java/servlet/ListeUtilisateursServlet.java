package servlet;

import dao.UtilisateurJPADAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Utilisateur;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/utilisateurs")
public class ListeUtilisateursServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UtilisateurJPADAO utilisateurDAO = new UtilisateurJPADAO();
        List<Utilisateur> listeUtilisateurs = utilisateurDAO.findAll();

        req.setAttribute("utilisateurs", listeUtilisateurs);
        req.getRequestDispatcher("/WEB-INF/liste-utilisateurs.jsp").forward(req, resp);
    }
}
