package business.command;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import business.command.handler.EventHandler;
import business.command.qualifier.FailCreateUserEventQualifier;
import business.services.UserService;
import msa.commons.parser.NumberParser;

@Stateless
@FailCreateUserEventQualifier
public class CancelCreateUserEvent implements EventHandler {
    
    private UserService userService;
    
    @Override
    public void handleCommand(Object event) {
        long idTypeUser = NumberParser.toLong(event);
        this.userService.cancelCreateUser(idTypeUser);
    }

    @EJB
    public void setTypeUserServices(UserService userService) {
        this.userService = userService;
    }
}
