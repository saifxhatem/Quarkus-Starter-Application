package http;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import http.services.CatFactService;
import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.mutiny.Uni;
import models.CatFact;

@Path("/hello")
public class HelloWorldResource {
    private static final Logger LOG = Logger.getLogger(HelloWorldResource.class);

    @Inject
    @RestClient
    CatFactService catFactService;
    
    @GET
    @NonBlocking
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        LOG.info("hello");
        return "hello";
    }

    @GET
    @Path("/cats")
    @NonBlocking
    public Uni<CatFact> getFact() {
        return catFactService.get();
    }
}
