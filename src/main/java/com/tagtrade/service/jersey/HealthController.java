package com.tagtrade.service.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;



@Component
@Path("/health")
public class HealthController {
    @GET
    @Produces("application/json")
    public void health() {
    	System.out.println("TYSTYSTYSTSYTYS");
    }
}
