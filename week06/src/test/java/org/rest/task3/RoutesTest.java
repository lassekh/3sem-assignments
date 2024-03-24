package org.rest.task3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rest.task3.config.AppConfig;
import org.rest.task3.config.HibernateConfig;
import org.rest.task3.entities.Hotel;
import org.rest.task3.entities.Room;
import org.rest.task3.entities.User;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

class RoutesTest {
    private static EntityManagerFactory emf;
    private static AppConfig app;
    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "http://localhost:7070/api";
        emf = HibernateConfig.getEntityManagerFactoryConfigTest();
        app = AppConfig.getInstance();

        app.setup()
                .startServer()
                //.checkSecurityRoles()
                .setRoute(Routes.getRoomRoutes())
                .setRoute(Routes.getHotelRoutes())
                .setRoute(Routes.getAuthRoutes())
                .setRoute(Routes.getProtectedRoutes());
    }

    @BeforeEach
    void setUp() {
        Hotel tivoli = new Hotel("Tivoli", "Hovedvejen 1");
        Hotel scandic = new Hotel("Scandic", "Sidevejen 2");
        Hotel radisson = new Hotel("Radisson", "Genvejen 3");

        tivoli.addRoom(new Room(101,250));
        tivoli.addRoom(new Room(102,250));
        tivoli.addRoom(new Room(103,500));

        scandic.addRoom(new Room(101,200));
        scandic.addRoom(new Room(102,200));
        scandic.addRoom(new Room(103,200));

        radisson.addRoom(new Room(101,100));
        radisson.addRoom(new Room(102,200));
        radisson.addRoom(new Room(103,300));

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(tivoli);
        em.persist(scandic);
        em.persist(radisson);
        em.getTransaction().commit();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getHotelRoutes() {
        given().
        when()
                .get("/hotel/1").
        then().
                assertThat().
                body("name", equalTo("Tivoli"));
    }

    @Test
    void createNewHotel() {
        Hotel lassesHotel = new Hotel("Lasses Hotel", "Lassevej 117");
        given().
                contentType(ContentType.JSON).
                body(lassesHotel).
        when().
                post("/hotel").
        then().
                statusCode(200).
                body("name", equalTo(lassesHotel.getName()));
    }

    @Test
    void getRoomRoutes() {
        given().
        when().
                get("/room/1").
        then().
                assertThat().
                body("roomNumber", equalTo(101));

    }

    @Test
    void getAuthRoutes() {
        String expectedToken = "something";
        User user = new User("myName","myPassword");
        given().
                contentType(ContentType.JSON).
                body(user).
        when().
                post("/auth/register").
        then().
                statusCode(201).
                body("token", notNullValue()).
                body("token", equalTo(expectedToken));
    }

    @Test
    void getProtectedRoutes() {
    }
}