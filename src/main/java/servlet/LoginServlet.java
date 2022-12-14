package servlet;

import dao.UtilisateurJPADAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Utilisateur;

import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = LoginServlet.URL)
public class LoginServlet extends HttpServlet {

    public static final String URL = "/login";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null && session.getAttribute("utilisateur") != null && !((Utilisateur)session.getAttribute("utilisateur")).getRole().getIntitule().equals("utilisateur") && ((Utilisateur)session.getAttribute("utilisateur")).getStatutCompte().getIntitule().equals("actif")){
            resp.sendRedirect("/utilisateurs");
        } else {
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Optional<Utilisateur> optUtilisateur = new UtilisateurJPADAO().getByMailAndPwd(email, password);
        if(optUtilisateur.isPresent() && !optUtilisateur.get().isBasicUser()) {
            HttpSession session = req.getSession(true);
            session.setAttribute("utilisateur", optUtilisateur.get());
            session.setMaxInactiveInterval(30 * 60);
            /*Cookie cookieUser = new Cookie("username", username);
            resp.addCookie(cookieUser);*/

        } else {
            if(!optUtilisateur.isPresent()) {
                req.setAttribute("loginFail", true);
            }
            if(optUtilisateur.isPresent() && optUtilisateur.get().isBasicUser()){
                req.setAttribute("basicUser", true);
            }
            doGet(req, resp);
        }
        resp.sendRedirect("/utilisateurs");
    }
}