package org.rest.task2;

import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;

public class ApplicationConfig {
    private static Javalin app;
    private static ApplicationConfig instance;
    private ApplicationConfig(){}
    public static ApplicationConfig getInstance()
    {
        if(instance == null) instance = new ApplicationConfig();
        return instance;
    }
    public ApplicationConfig setup()
    {
        app = Javalin.create(config -> {
            config.http.defaultContentType = "application/json";
            config.routing.contextPath = "/api/vet";
        });
        return instance;
    }
    public ApplicationConfig startServer()
    {
        app.start(7070);
        return instance;
    }
    public ApplicationConfig setRoute(EndpointGroup endpoints)
    {
        app.routes(endpoints);
        return instance;
    }
}
