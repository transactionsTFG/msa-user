package controller;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import business.dto.CreateUserDTO;
import business.services.UserService;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {
    
    private UserService userService;

    @POST
    @Transactional
    public Response createUser(CreateUserDTO createUserDTO) {
        this.userService.beginCreateUser(createUserDTO);
        return Response.status(Response.Status.CREATED)
                        .entity("User created successfully").build();
    }

    @Inject
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
