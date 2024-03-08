package org.rest.task3.config;

import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;

public class AppConfig {
    private static Javalin app;
    private static AppConfig instance;
    private AppConfig(){}
    public static AppConfig getInstance()
    {
        if(instance == null) instance = new AppConfig();
        return instance;
    }
    public AppConfig setup()
    {
        app = Javalin.create(config -> {
            config.http.defaultContentType = "application/json";
            config.routing.contextPath = "/api/vet";
        });
        return instance;
    }
    public AppConfig startServer()
    {
        app.start(7070);
        return instance;
    }
    public AppConfig setRoute(EndpointGroup endpoints)
    {
        app.routes(endpoints);
        return instance;
    }
}
