package org.jpa.task1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.jpa.HibernateConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DolphinDAOTest {
    EntityManagerFactory emf;
    DolphinDAO dao;
    @BeforeEach
    void setUp() {
        emf = HibernateConfig.getEntityManagerFactoryForTesting();
        dao = new DolphinDAO(emf);
    }

    @AfterEach
    void tearDown() {
        emf.close();
    }

    @Test
    void create() {
        Person person = new Person("Tesla");
        dao.create(person);
        //how?

    }

    @Test
    void update() {
    }
}