package com.example.planningmeteobackoffice.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class ConnectionManager {

    private static EntityManagerFactory CONNECTION_INSTANCE;

    private static EntityManager DAO_LAYER;

    private final static String persistenceUnitName = "myPU";

    private ConnectionManager(){

    }

    public static EntityManager getEntityManager() {

        if(CONNECTION_INSTANCE == null || !CONNECTION_INSTANCE.isOpen()) {
            CONNECTION_INSTANCE = Persistence.createEntityManagerFactory(persistenceUnitName);
            DAO_LAYER = CONNECTION_INSTANCE.createEntityManager();
        }

        return DAO_LAYER;
    }


    public static void closeConnection() {
        try {
            CONNECTION_INSTANCE.close();
        } catch (Exception e) {
            System.err.println("Fermeture de la connexion impossible");
        }
    }
}
