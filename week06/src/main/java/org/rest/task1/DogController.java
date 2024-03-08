package org.rest.task1;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.Map;

public class DogController {
    private static DogDAO dogDAO = DogDAO.getInstance();
    public static Handler getAll()
    {
        return ctx -> ctx.json(dogDAO.getAll());
    }
    public static Handler getById()
    {
        //Use DAO in controller to find stuff
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Dog dog = dogDAO.getDogById(id);
            ctx.json(dog);
        };
    }
    public static Handler create()
    {
        return ctx -> {
            System.out.println("Received POST request: " + ctx.body());
            Dog dog = ctx.bodyAsClass(Dog.class);
            dogDAO.createDog(dog);
            ctx.json(dog);
        };
    }
}
