package org.jpa.task1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.jpa.HibernateConfig;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class DolphinDAOTest {
    static private EntityManagerFactory emf;
    static private DolphinDAO dao;
    @BeforeAll
    static void setUpAll() {
        emf = HibernateConfig.getEntityManagerFactoryForTesting();
        dao = new DolphinDAO();
    }
    @BeforeEach
    void setUp() {
        //delete all rows
        //insert test data
        //do something with ids

    }

    @AfterEach
    void tearDown() {
        emf.close();
    }

    @Test
    @DisplayName("insert name")
    void create() {
        //given
        //when
        //then
        //use variables 'expected' and 'actual'

        Person person = new Person("Tesla");
        dao.create(person);
        //use .find() from JPA

    }

    @Test
    void update() {
    }
}