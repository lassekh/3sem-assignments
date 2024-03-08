package org.rest.task1;

import io.javalin.Javalin;

import java.util.List;
import java.util.Map;

public class DogExercise {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7008);

        //Takes a Handler as paramater that requires a Context
        //A Handler is a functional interface, that takes a consumer
        //A consumer takes something, and does something, but returns nothing
        app.get("/dogs", DogController.getAll());
        app.get("/dog/{id}", DogController.getById());
        app.post("/dog", DogController.create());

    }
}
