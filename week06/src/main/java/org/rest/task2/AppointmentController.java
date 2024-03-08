package org.rest.task2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.javalin.http.Handler;

import java.time.LocalDateTime;

public class AppointmentController {
    private static ObjectMapper om = new ObjectMapper();
    public static Handler getAll()
    {
        return ctx -> {
            ctx.json(VeterinarianExercise.appointments);
        };
    }
    public static Handler getById()
    {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Appointment result = VeterinarianExercise.appointments.get(id);
            AppointmentDTO dto = new AppointmentDTO(result.getPatient().getName(), result.getTime().toString());
            ctx.json(dto);
        };
    }
}
