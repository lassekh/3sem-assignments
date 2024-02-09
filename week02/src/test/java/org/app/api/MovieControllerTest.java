package org.app.api;

import static org.junit.jupiter.api.Assertions.*;

class MovieControllerTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.Test
    void searchAndFindMovie() {
        String movieTitle = "killers of the flower moon";
        MovieDTO movie = MovieController.search(movieTitle);
        System.out.println(movie.getOriginal_title());
        assertTrue(movie.getOriginal_title().equalsIgnoreCase(movieTitle));
    }
}