package listeners;

import dao.ConnectionManager;
import dao.RoleJPADAO;
import dao.StatutCompteJPADAO;
import dao.UtilisateurJPADAO;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class PersistenceAppListener implements ServletContextListener {
    // Call on application initialization
        public void contextInitialized(ServletContextEvent evt){
        new RoleJPADAO().initialize();
        new StatutCompteJPADAO().initialize();
        new UtilisateurJPADAO().initialize();
         }
        // Call on application destruction
        public void contextDestroyed(ServletContextEvent evt) {
        ConnectionManager.closeEntityManagerFactory();
        }
}
