package org.example.task4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.task1.HibernateConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PointDAOTest {
    EntityManagerFactory emf;
    EntityManager em;
    PointDAO pointDAO;
    @BeforeEach
    void setUp() {
        emf = HibernateConfig.getEntityManagerFactoryConfig();
        em = emf.createEntityManager();
        pointDAO = new PointDAO();
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void getTotalPoints() {
        long totalNumberOfPoints = pointDAO.getTotalPoints(em);
        assertEquals(1000,totalNumberOfPoints);
    }

    @Test
    void getAvgXValue() {
        double avgXValue = pointDAO.getAvgXValue(em);
        assertEquals(500,avgXValue);
    }

    @Test
    void getAllPoints() {
        List<Point> allPoints = pointDAO.getAllPoints(em);
        assertEquals(1000,allPoints.size());
    }
}