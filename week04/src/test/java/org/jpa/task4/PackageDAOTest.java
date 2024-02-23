package org.jpa.task4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.jpa.HibernateConfig;
import org.jpa.task1.DolphinDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PackageDAOTest {
    static private EntityManagerFactory emf;
    static private PackageDAO dao;
    //static private ShipmentDAO;
    @BeforeAll
    static void setUpAll() {
        emf = HibernateConfig.getEntityManagerFactoryForTesting();
        dao = new PackageDAO(emf);
    }

    @BeforeEach
    void setUp() {
        //delete all rows
        //EntityManager em = emf.createEntityManager();
        //Query q = em.createQuery("delete from Package p");
        //insert test data
        Location l1 = new Location(11,22,"Lyngby");
        Location l2 = new Location(22,33,"Odense");
        Location l3 = new Location(33,44,"Aarhus");
        Shipment s1 = new Shipment(l1,l2,LocalDateTime.now());
        Shipment s2 = new Shipment(l2,l3,LocalDateTime.now());
        dao.save(new Package("ERD234","Lasse","Rasmus"));
        dao.save(new Package("RTY123","Peter","Gudrun"));
        dao.save(new Package("POI321","Carsten","Lonni"));
        dao.save(new Package("LKJ456","Molly","Ida"));
        dao.save(new Package("GHJ098","Susanne","Henrik"));
        //do something with ids
    }

    @AfterEach
    void tearDown() {
        emf.close();
    }

    @Test
    void save() {
    }

    @Test
    void getByTrackingNumber() {
    }

    @Test
    void updateStatus() {
    }

    @Test
    void delete() {
    }
}