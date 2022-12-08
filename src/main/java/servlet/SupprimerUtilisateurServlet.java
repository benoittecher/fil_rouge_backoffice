package servlet;

import dao.UtilisateurJPADAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Utilisateur;

import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/utilisateurs/supprimer")
public class SupprimerUtilisateurServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("idUtilisateur"));
        UtilisateurJPADAO utilisateurDAO = new UtilisateurJPADAO();

        Optional<Utilisateur> utilisateurOptional = utilisateurDAO.findById(id);

        if(utilisateurOptional.isPresent()) {
            utilisateurDAO.delete(id);
            resp.sendRedirect(req.getContextPath()+ "/utilisateurs");
        } else {
            //TODO gérer utilisateur non trouvé
        }
    }
}
