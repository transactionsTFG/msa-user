package business.command;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.mapstruct.control.MappingControl.Use;

import business.command.handler.EventHandler;
import business.command.qualifier.SuccessUserEventQualifier;
import business.services.UserService;
import msa.commons.parser.NumberParser;

@Stateless
@SuccessUserEventQualifier
public class ConfirmCreateUserEvent implements EventHandler {

    private UserService userService;
    
    @Override
    public void handleCommand(Object event) {
        long idTypeUser = NumberParser.toLong(event);
        this.userService.confirmCreateUser(idTypeUser);
    } 

    @EJB
    public void setTypeUserServices(UserService userService) {
        this.userService = userService;
    }
}
