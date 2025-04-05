package controller;

import javax.ejb.EJB;
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
        boolean isCreated = this.userService.beginCreateUser(createUserDTO);
        if (!isCreated) 
            return Response.status(Response.Status.CONFLICT).entity("Usuario ya existente").build();
            
        return Response.status(Response.Status.CREATED).entity("Usuario creado correctamente").build();
    }

    @EJB
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
