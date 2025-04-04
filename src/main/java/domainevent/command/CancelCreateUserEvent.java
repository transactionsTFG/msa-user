package domainevent.command;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import business.services.UserService;
import domainevent.command.handler.EventHandler;
import domainevent.command.qualifier.FailCreateUserEventQualifier;
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
