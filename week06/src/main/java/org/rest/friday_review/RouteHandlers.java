package org.rest.friday_review;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicReference;

public class RouteHandlers {
    public static void main(String[] args) {
        //Route Handlers: Learn how to define and implement route handlers in Javalin
        // to handle specific HTTP endpoints,
        // including how to use the ctx parameter to interact with the request and response.

        Javalin myApp = Javalin.create().start(7009);

        //A Handler is a functional interface that only takes a Context object
        new Handler() {
            @Override
            public void handle(@NotNull Context context) throws Exception {
                context.json("hello"); //todo: something with the Context
            }
        };

        //A http method in Javalin takes a String route, and a Handler
        myApp.get("/welcome", ctx -> ctx.json("hello"));

        //The handler can also be returned via a method, usually will be from a Controller class
        myApp.get("/welcomeMethod", ExampleController.sayHello());

        //You can do many things with the Context object (CRUD operation)
        myApp.put("/newName/{name}", ctx -> {
            String name = ctx.pathParam("name");
            //todo: set persons new name
        });
    }
}
