package org.rest.task3;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.security.RouteRole;
import org.rest.task3.controller.HotelController;
import org.rest.task3.controller.ISecurityController;
import org.rest.task3.controller.RoomController;
import org.rest.task3.controller.SecurityController;
import org.rest.task3.entities.Room;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;
import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.before;

public class Routes {
    private static ISecurityController securityController = new SecurityController();
    private static ObjectMapper om = new ObjectMapper();
    public static EndpointGroup getHotelRoutes()
    {
        return () -> {
            path("/hotel", () -> {
                //reading
                get("/", HotelController.getHotels());
                get("/{id}", HotelController.getHotelById());
                get("/{id}/rooms", HotelController.getHotelRooms());
                //creating
                post("/", HotelController.createHotel());
                //updating
                put("/{id}", HotelController.updateHotel());
                //deleting
                delete("/{id}", HotelController.deleteHotel());
            });
        };
    }
    public static EndpointGroup getRoomRoutes()
    {
        return () -> {
            path("/room", () -> {
                //reading
                get("/", RoomController.getRooms());
                get("/{id}", RoomController.getRoomById());
                //creating
                post("/", RoomController.createRoom());
                //updating
                put("/{id}", RoomController.updateRoom());
                //deleting
                delete("/{id}", RoomController.deleteRoom());
            });
        };
    }
    public static EndpointGroup getAuthRoutes()
    {
        return () -> {
            path("/auth", () -> {
                post("/register", securityController.register(), Role.ANYONE);
                post("/login", securityController.login(), Role.ANYONE);
            });
        };
    }
    public static EndpointGroup getProtectedRoutes()
    {
        return () -> {
            path("/protected", () -> {
                //to check token before giving access
                before(securityController.authenticate());
                //demonstrate route protected by user role
                get("/user", ctx -> ctx.json(om.createObjectNode().put("msg", "Welcome user!")), Role.USER);
                //demonstrate route protected by admin role
                get("/admin", ctx -> ctx.json(om.createObjectNode().put("msg", "Welcome admin!")), Role.ADMIN);
            });
        };
    }
    private static enum Role implements RouteRole {ANYONE, USER, ADMIN}
}
