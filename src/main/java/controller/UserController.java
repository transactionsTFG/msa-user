package controller;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import business.dto.CreateUserDTO;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {
    
    @POST
    @Transactional
    public Response createUser(CreateUserDTO createUserDTO) {
        return Response.status(Response.Status.CREATED)
                        .entity("User created successfully").build();
    }

}
