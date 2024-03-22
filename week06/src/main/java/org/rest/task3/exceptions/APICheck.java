package org.rest.task3.exceptions;

import io.javalin.http.Context;

public class APICheck {
    public static Context isError404(Object o, Context ctx)
    {
        if(o == null)
        {
            return ctx.status(404).result("Nothing where found");
        } else
        {
           return ctx.status(200).result("a ok!");
        }
    }
}
