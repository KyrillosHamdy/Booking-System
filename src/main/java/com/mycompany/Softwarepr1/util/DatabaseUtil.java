package com.mycompany.Softwarepr1.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import java.io.InputStream;
import java.util.Properties;

/**
 * Singleton utility for JPA EntityManagerFactory.
 * Loads database.properties once and reuses the factory across the application.
 */
public class DatabaseUtil {

    private static EntityManagerFactory emf;
    private static boolean connected = false;

    private DatabaseUtil() {}

    public static synchronized EntityManagerFactory getEntityManagerFactory() {
        if (emf == null || !emf.isOpen()) {
            Properties dbProps = new Properties();
            try (InputStream in = DatabaseUtil.class.getClassLoader()
                    .getResourceAsStream("database.properties")) {
                if (in != null) {
                    dbProps.load(in);
                }
            } catch (Exception e) {
                System.err.println("Could not load database.properties: " + e.getMessage());
            }

            Properties jpaProps = new Properties();
            if (dbProps.getProperty("db.url") != null) {
                jpaProps.put("javax.persistence.jdbc.url", dbProps.getProperty("db.url"));
                jpaProps.put("javax.persistence.jdbc.user", dbProps.getProperty("db.user"));
                jpaProps.put("javax.persistence.jdbc.password", dbProps.getProperty("db.password"));
            }

            try {
                emf = Persistence.createEntityManagerFactory("project1PU", jpaProps);
                connected = true;
            } catch (Exception e) {
                connected = false;
                JOptionPane.showMessageDialog(null,
                        "Failed to connect to the database!\n\n"
                        + "Please check that:\n"
                        + "  • The database server is running\n"
                        + "  • Your connection settings in database.properties are correct\n"
                        + "  • The database exists and is accessible\n\n"
                        + "Error: " + e.getMessage(),
                        "Database Connection Error",
                        JOptionPane.PLAIN_MESSAGE);
                throw e;
            }
        }
        return emf;
    }

    public static EntityManager createEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    /**
     * Tests the database connection and shows a status message.
     * Call this at application startup to give the user early feedback.
     *
     * @return true if the connection is successful, false otherwise
     */
    public static boolean testConnection() {
        EntityManager em = null;
        try {
            em = createEntityManager();
            // Execute a lightweight query to verify the connection is truly alive
            em.createNativeQuery("SELECT 1").getSingleResult();
            connected = true;
            return true;
        } catch (Exception e) {
            connected = false;
            JOptionPane.showMessageDialog(null,
                    "Cannot connect to the database!\n\n"
                    + "Please check that:\n"
                    + "  • The database server (SQL Server) is running\n"
                    + "  • Your connection settings in database.properties are correct\n"
                    + "  • The database exists and is accessible\n\n"
                    + "Error: " + e.getMessage(),
                    "Database Connection Error",
                    JOptionPane.PLAIN_MESSAGE);
            return false;
        } finally {
            if (em != null) {
                try { em.close(); } catch (Exception ignored) {}
            }
        }
    }

    /**
     * Returns whether the database is currently connected.
     */
    public static boolean isConnected() {
        return connected;
    }

    public static void shutdown() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
        connected = false;
    }
}
