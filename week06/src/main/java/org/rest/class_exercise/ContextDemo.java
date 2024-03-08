package org.rest.class_exercise;

import io.javalin.Javalin;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.get;


public class ContextDemo {
    public static void main(String[] args) {
        //Javalin app1 = Javalin.create().start(7007);
        ApplicationConfig appConfig = ApplicationConfig.getInstance();
        appConfig
                .initiateServer()
                .startServer(7070)
                .setExceptionHandling()
                .setRoutes(() -> {
                    path("demo", () -> {
                        get("/", ctx -> ctx.json(ctx.headerMap()));
                        get("error", ctx -> {
                            throw new Exception("This is a test");
                        });
                    });
                });

//        app1.get("/hello/{name}", ctx -> {
//            String name = ctx.pathParam("name");
//            String result = "Welcome, " + name;
//            ctx.json(result);
//        });
//
//        app1.get("/headers", ctx -> {
//            Map<String, String> headers = ctx.headerMap();
//            ctx.json(headers);
//        });
//
//        app1.get("queryParam", ctx -> {
//            String name = ctx.queryParam("name");
//            ctx.json(name);
//        });
//
//        app1.get("/responseHeaders", ctx -> {
//
//            ctx.header("X-EXAMPLE-HEADER", "Hello World!");
//        });
    }
}
