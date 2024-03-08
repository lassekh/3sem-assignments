package org.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;
import lombok.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;


public class Main {
    //private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Lasse", 34),
                new Person("Terkel", 23),
                new Person("Heinrich", 78)
        );

        List<LocalDate> dates = Arrays.asList(
                LocalDate.of(2002, 2, 2),
                LocalDate.now(),
                LocalDate.of(2023,12,31)
        );
        //Use Jackson library ObjectMapper to convert object to json
        ObjectMapper om = new ObjectMapper();

        Javalin app = Javalin.create().start(7007);

        //Uses Handler from Javalin interface, a functional interface that takes a Context.
        //app.get("/", ctx -> ctx.result("Hello World"));

        //app.get("/persons", ctx -> ctx.json(persons));
        app.get("/dates1", ctx -> ctx.json(dates.toString()));
        app.get("/dates2", ctx -> ctx.json(
                om.createObjectNode().put("current_date", LocalDate.now().toString())
        ));
        //app.get("dad-joke", ctx -> ctx.json(getJoke()));

        app.routes(
                () -> {
                        path("/person", () -> {
                            get("/", ctx -> ctx.json(persons));
                            get("/{name}", ctx -> {
                                String name = ctx.pathParam("name");
                                ctx.json(persons.stream().filter(person -> person.getName().equalsIgnoreCase(name)).findFirst().get());
                        });
                        post("/", ctx -> {
                            Person p = ctx.bodyAsClass(Person.class);
                            ctx.json(p);
                        });
                    });
                });

//        app.get("/person/{name}", ctx -> {
//                String name = ctx.pathParam("name");
//                ctx.json(persons.stream().filter(person -> person.getName().equalsIgnoreCase(name)).findFirst().get());
//            }
//        );


    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode
    private static class Person
    {
        String name;
        int age;
    }


//    private static EndpointGroup getPersonRoutes()
//    {
//        return () -> path("/person", () -> {
//                get("/{name}", ctx -> ctx.json(persons)),
//                get("/{age}", ctx -> {
//                    String name = ctx.pathParam("name");
//                    ctx.json(persons.stream().filter(person -> person.getName().equalsIgnoreCase(name)).findFirst().get());
//                });
//                post("/", ctx -> {
//                    Person p = ctx.bodyAsClass(Person.class);
//
//                });
//            }
//        );
//    }









//    private static JokeDTO getJoke() throws IOException {
//        HttpGet request = new HttpGet("https://icanhazdadjoke.com/");
//        //https://icanhazdadjoke.com/
//        String result = null;
//        try {
//            try(CloseableHttpClient client = HttpClients.createDefault();
//                CloseableHttpResponse response = client.execute(request))
//            {
//                HttpEntity entity = response.getEntity();
//
//                if(entity != null)
//                {
//                    result = EntityUtils.toString(entity);
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return gson.fromJson(result, JokeDTO.class);
//    }
}