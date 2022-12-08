package dao;

import model.Utilisateur;

public class DAOFactory {
    private DAOFactory() {

    }
    public static CrudDAO<Utilisateur> getUtilisateurJPADao(){
        return new UtilisateurJPADAO();
    }
}