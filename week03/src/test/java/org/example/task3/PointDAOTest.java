package org.example.task3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.task1.HibernateConfig;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PointDAOTest {
    static EntityManagerFactory emf;
    static EntityManager em;
    static PointDAO pointDAO;
    @BeforeAll
    static void setUp() {
        emf = HibernateConfig.getEntityManagerFactoryConfigForTesting();
        em = emf.createEntityManager();
        pointDAO = new PointDAO();
        pointDAO.add1000Points(em);
    }

    @AfterAll
    static void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void addPoints()
    {

    }

    @Test
    void getTotalPoints()
    {
        long totalNumberOfPoints = pointDAO.getTotalPoints(em);
        assertEquals(1000,totalNumberOfPoints);
    }

    @Test
    void getAvgXValue()
    {
        double avgXValue = pointDAO.getAvgXValue(em);
        assertEquals(500,avgXValue);
    }

    @Test
    void getAllPoints()
    {
        List<Point> allPoints = pointDAO.getAllPoints(em);
        assertNotNull(allPoints);
        assertEquals(1000,allPoints.size());
    }
}