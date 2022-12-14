package servlet;

import dao.UtilisateurJPADAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Utilisateur;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/utilisateurs")
public class ListeUtilisateursServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UtilisateurJPADAO utilisateurDAO = new UtilisateurJPADAO();
        List<Utilisateur> listeUtilisateurs = utilisateurDAO.findAll();
        Utilisateur admin = (Utilisateur) req.getSession().getAttribute("utilisateur");
        boolean isSuperAdmin = admin.getRole().getIntitule().equals("superAdmin");
        Map<Utilisateur, Boolean> canUpdate = new HashMap<>();
        if(isSuperAdmin){
            for(Utilisateur utilisateur : listeUtilisateurs){
                canUpdate.put(utilisateur, true);
            }
        } else{
            for(Utilisateur utilisateur : listeUtilisateurs) {
                canUpdate.put(utilisateur, utilisateur.getRole().getIntitule().equals("utilisateur"));
            }
        }

        if(req.getParameter("utilisateurabsent") != null) {
            req.setAttribute("utilisateurAbsent", true);
        }

        req.setAttribute("canUpdate", canUpdate);
        req.setAttribute("utilisateurs", listeUtilisateurs);
        req.getRequestDispatcher("/WEB-INF/liste-utilisateurs.jsp").forward(req, resp);
    }
}
