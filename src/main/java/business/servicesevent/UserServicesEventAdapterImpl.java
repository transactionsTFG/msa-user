package business.servicesevent;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import business.dto.CreateUserDTO;
import business.services.UserService;
import business.user.UserDTO;
import domainevent.publisher.IJMSEventPublisher;
import msa.commons.commands.user.CreateUserCommand;
import msa.commons.event.EventId;

@Stateless
public class UserServicesEventAdapterImpl implements UserServicesEventAdapter {
    private UserService userService;
    private IJMSEventPublisher jmsEventDispatcher;

    @Override
    public boolean beginCreateUser(CreateUserDTO userDTO) {
        UserDTO isCreated = this.userService.beginCreateUser(userDTO);
        if (isCreated == null) 
            return false;   
        this.jmsEventDispatcher.publish(EventId.VALIDATE_TYPE_USER, new CreateUserCommand(isCreated.getId(), userDTO.getTypeUser()));
        return true;
    }

    @EJB
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Inject
    public void setJmsEventDispatcher(IJMSEventPublisher jmsEventDispatcher) {
        this.jmsEventDispatcher = jmsEventDispatcher;
    }
}
