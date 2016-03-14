package org.shullfamily.pvrfileprocessor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * Created by joe on 3/11/2016.
 */

//  http://localhost:8080/testingservice/foo

@Path("foo")
public class Foo {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get() {
        return "\nBar!"
                + "\nApplication Directory: " + FileSystem.getApplicationDirectory();
    }
}