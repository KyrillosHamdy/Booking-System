package com.mycompany.Softwarepr1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.Softwarepr1.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class DatabaseIntegrationTest {

    @Test
    public void testDatabaseConnection() {
        // This test will attempt to connect to the database configured in database.properties
        // If the database is not running, this test will fail, which is expected for integration tests.
        
        try {
            EntityManagerFactory emf = DatabaseUtil.getEntityManagerFactory();
            assertNotNull(emf, "EntityManagerFactory should not be null");
            assertTrue(emf.isOpen(), "EntityManagerFactory should be open");
            
            EntityManager em = DatabaseUtil.createEntityManager();
            assertNotNull(em, "EntityManager should not be null");
            assertTrue(em.isOpen(), "EntityManager should be open");
            
            em.close();
        } catch (Exception e) {
            fail("Failed to connect to the database. Ensure SQL Server is running and credentials are correct. Error: " + e.getMessage());
        }
    }
}
