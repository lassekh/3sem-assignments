package org.rest.task3.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.HttpStatus;
import org.rest.task3.controller.ISecurityController;
import org.rest.task3.controller.SecurityController;
import org.rest.task3.dto.UserDTO;
import org.rest.task3.exceptions.APIException;

import java.util.Set;
import java.util.stream.Collectors;

public class AppConfig {
    private static Javalin app;
    private static AppConfig instance;
    private ISecurityController securityController = new SecurityController();
    private ObjectMapper om = new ObjectMapper();
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
            config.routing.contextPath = "/api";
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
    public AppConfig checkSecurityRoles() {
        // Check roles on the user (ctx.attribute("username") and compare with permittedRoles using securityController.authorize()
        app.updateConfig(config -> {

            config.accessManager((handler, ctx, permittedRoles) -> {
                // permitted roles are defined in the last arg to routes: get("/", ctx -> ctx.result("Hello World"), Role.ANYONE);

                Set<String> allowedRoles = permittedRoles.stream().map(role -> role.toString().toUpperCase()).collect(Collectors.toSet());
                if(allowedRoles.contains("ANYONE") || ctx.method().toString().equals("OPTIONS")) {
                    // Allow requests from anyone and OPTIONS requests (preflight in CORS)
                    handler.handle(ctx);
                    return;
                }

                UserDTO user = ctx.attribute("user");
                System.out.println("USER IN CHECK_SEC_ROLES: "+user);
                if(user == null)
                    ctx.status(HttpStatus.FORBIDDEN)
                            .json(om.createObjectNode()
                                    .put("msg","Not authorized. No username were added from the token"));

                if (securityController.authorize(user, allowedRoles))
                    handler.handle(ctx);
                else
                    throw new APIException(HttpStatus.FORBIDDEN.getCode(), "Unauthorized with roles: "+allowedRoles);
            });
        });
        return instance;
    }
    public AppConfig exceptionHandling()
    {
        app.exception(Exception.class, (e,ctx) -> {
            if(e.getClass() != IllegalStateException.class)
            {
                ObjectNode node = om.createObjectNode().put("errorMessage", e.getMessage());
                ctx.status(500).json(node);
            } else
            {
                ctx.status(400).result("400 - Bad request");
            }
        });
        app.error(404, ctx -> ctx.status(404).result("404 - Not found"));
        return instance;
    }
}
