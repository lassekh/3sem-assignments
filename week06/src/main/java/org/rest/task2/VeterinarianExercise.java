package org.rest.task2;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.get;


public class VeterinarianExercise {
    public static Map<Integer, Patient> patients = new HashMap<>() {{
        put(1, new Patient("Fido", "A1", "M1", 12));
        put(2, new Patient("Hansi", "A1", "M2", 1));
        put(3, new Patient("Lis", "A2", "M2", 3));
    }};
    public static Map<Integer, Appointment> appointments = new HashMap<>() {{
        put(1, new Appointment(LocalDateTime.of(2024,4,4,10,45), patients.get(1)));
        put(2, new Appointment(LocalDateTime.of(2024,4,11,8,45), patients.get(1)));
        put(3, new Appointment(LocalDateTime.of(2024,4,4,11,45), patients.get(2)));
        put(4, new Appointment(LocalDateTime.of(2024,4,1,10,0), patients.get(1)));
    }};
//    public static Map<Integer, Appointment> appointments = new HashMap<>() {{
//        put(1, new Appointment("LocalDateTime.of(2024,4,4,10,45", patients.get(1)));
//        put(2, new Appointment("LocalDateTime.of(2024,4,11,8,45", patients.get(1)));
//        put(3, new Appointment("LocalDateTime.of(2024,4,4,11,45", patients.get(2)));
//        put(4, new Appointment("LocalDateTime.of(2024,4,1,10,0", patients.get(1)));
//    }};
    public static void main(String[] args) {

        ApplicationConfig config = ApplicationConfig.getInstance();
        config.setup()
                .startServer()
                .setRoute(() -> {
                    path("/", () -> {
                        get("/patients", PatientController.getAll());
                        get("/appointments", AppointmentController.getAll());
                        get("/patient/{id}", PatientController.getById());
                        get("/appointment/{id}", AppointmentController.getById());
                    });
                });
    }
}
