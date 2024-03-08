package org.rest.friday_review;

import io.javalin.http.Handler;

public class ExampleController {
    public static Handler sayHello()
    {
        return ctx -> ctx.json("hello");
    }
}
