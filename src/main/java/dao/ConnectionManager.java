package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class ConnectionManager {

    private static EntityManagerFactory CONNECTION_INSTANCE;

    private static EntityManager em;

    public static EntityManager getEntityManager() {

        if(CONNECTION_INSTANCE == null) {
            CONNECTION_INSTANCE = Persistence.createEntityManagerFactory("myPU");
        }
        if(em == null || !em.isOpen()) {
            em = CONNECTION_INSTANCE.createEntityManager();
        }

        return em;
    }

    private ConnectionManager(){

    }

    public static void closeConnection() {
        try {
            CONNECTION_INSTANCE.close();
        } catch (Exception e) {
            System.err.println("Fermeture de la connexion impossible");
        }
    }
}
