package controller;

import javax.ejb.EJB;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import business.dto.CreateUserDTO;
import business.servicesevent.UserServicesEventAdapter;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {
    private static final Logger LOGGER = LogManager.getLogger(UserController.class);
    private UserServicesEventAdapter userServiceEventAdapter;
    @POST
    @Transactional
    public Response createUser(CreateUserDTO createUserDTO) {
        LOGGER.info("Creando usuario: {}", createUserDTO);
        boolean isCreated = this.userServiceEventAdapter.beginCreateUser(createUserDTO);
        if (!isCreated) 
            return Response.status(Response.Status.CONFLICT).entity("Usuario ya existente").build();
        return Response.status(Response.Status.CREATED).entity("Usuario creado correctamente").build();
    }

    @EJB
    public void setUserService(UserServicesEventAdapter userServiceEventAdapter) {
        this.userServiceEventAdapter = userServiceEventAdapter;
    }

}
