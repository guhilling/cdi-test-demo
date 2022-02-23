package de.hilling.cdi.sampleapp.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/ping")
@ApplicationScoped
public class ConnectionTestResource {

    @Inject
    @ConfigProperty(name = "cdi-test-demo.pong")
    private String pong;

    @Path("/")
    @GET
    public String ping() {
        return pong;
    }

}
