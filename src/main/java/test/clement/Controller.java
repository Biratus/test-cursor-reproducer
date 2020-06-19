package test.clement;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/mysql")
@Produces(MediaType.TEXT_PLAIN)
@Slf4j
public class Controller {

    @Inject
    Repository repository;

    @GET
    public String test() {
        repository.findAllMulti().subscribe().with(client -> log.info("Client "+client.getAccountNumber()));
        return "PROCESSING";
    }
}
