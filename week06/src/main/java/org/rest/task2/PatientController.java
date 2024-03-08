package org.rest.task2;


import io.javalin.http.Handler;

public class PatientController {
    public static Handler getAll()
    {
        return ctx -> ctx.json(VeterinarianExercise.patients);
    }
    public static Handler getById()
    {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Patient result = VeterinarianExercise.patients.get(id);
            ctx.json(result);
        };
    }
}
