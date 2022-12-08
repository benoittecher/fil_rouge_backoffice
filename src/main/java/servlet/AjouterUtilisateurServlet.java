package servlet;

import dao.RoleJPADAO;
import dao.StatutCompteJPADAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Role;
import model.StatutCompte;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/utilisateurs/ajouter")
public class AjouterUtilisateurServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoleJPADAO roleDAO = new RoleJPADAO();
        List<Role> listeRoles = roleDAO.findAll();
        StatutCompteJPADAO statutDAO = new StatutCompteJPADAO();
        List<StatutCompte> listeStatuts = statutDAO.findAll();

        req.setAttribute("roles", listeRoles);
        req.setAttribute("status", listeStatuts);
        req.getRequestDispatcher("/WEB-INF/ajouter-utilisateur.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
