package servlet;

import dao.RoleJPADAO;
import dao.StatutCompteJPADAO;
import dao.UtilisateurJPADAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Role;
import model.StatutCompte;
import model.Utilisateur;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = "/utilisateurs/modifier")
public class ModifierUtilisateurServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoleJPADAO roleDAO = new RoleJPADAO();
        List<Role> listeRoles = roleDAO.findAll();
        StatutCompteJPADAO statutDAO = new StatutCompteJPADAO();
        List<StatutCompte> listeStatuts = statutDAO.findAll();

        req.setAttribute("roles", listeRoles);
        req.setAttribute("statuts", listeStatuts);

        Long id = Long.parseLong(req.getParameter("id"));
        UtilisateurJPADAO utilisateurDAO = new UtilisateurJPADAO();
        Optional<Utilisateur> utilisateurOptional = utilisateurDAO.findById(id);

        if(utilisateurOptional.isPresent()) {
            Utilisateur utilisateur = utilisateurOptional.get();
            req.setAttribute("utilisateur", utilisateur);
            req.getRequestDispatcher("/WEB-INF/modifier-utilisateur.jsp").forward(req, resp);
        } else {
            //todo : utilisateur non trouvé
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idUtilisateur = Long.parseLong(req.getParameter("id"));
        String nom = req.getParameter("nomUtilisateur");
        String prenom = req.getParameter("prenomUtilisateur");
        String mail = req.getParameter("mailUtilisateur");
        String mdp = req.getParameter("mdpUtilisateur");
        String ville = req.getParameter("villeUtilisateur");
        String pays = req.getParameter("paysUtilisateur");
        Long idRole = Long.parseLong(req.getParameter("roleUtilisateur"));
        Long idStatut = Long.parseLong(req.getParameter("statutCompteUtilisateur"));

        RoleJPADAO roleDAO = new RoleJPADAO();
        StatutCompteJPADAO statutDAO = new StatutCompteJPADAO();
        UtilisateurJPADAO utilisateurDAO = new UtilisateurJPADAO();

        Optional<Utilisateur> utilisateurOptional = utilisateurDAO.findById(idUtilisateur);

        Role role = roleDAO.findById(idRole).get();
        StatutCompte statut = statutDAO.findById(idStatut).get();

        if(utilisateurOptional.isPresent()) {
            Utilisateur utilisateur = new Utilisateur(idUtilisateur, nom, prenom, mail, mdp, ville, pays, role, statut);
            utilisateurDAO.update(utilisateur);
            resp.sendRedirect(req.getContextPath() + "/utilisateurs");
        } else {
            //todo : utilisateur non trouvé
        }
    }
}
