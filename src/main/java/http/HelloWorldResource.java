package http;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

import io.smallrye.common.annotation.NonBlocking;

@Path("/hello")
public class HelloWorldResource {
    private static final Logger LOG = Logger.getLogger(HelloWorldResource.class);

    @GET
    @NonBlocking
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        LOG.info("hello");
        return "hello";
    }
}
