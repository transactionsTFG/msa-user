package domainevent.command;

import javax.ejb.Local;
import javax.ejb.Stateless;

import business.qualifier.RollbackUserQualifier;
import domainevent.command.handler.BaseHandler;
import domainevent.command.handler.EventHandler;
import msa.commons.commands.user.CreateUserCommand;
import msa.commons.event.EventData;

@Stateless
@RollbackUserQualifier
@Local(EventHandler.class)
public class CancelCreateUserEvent extends BaseHandler {    
    @Override
    public void handleCommand(String json) {
        EventData eventData = EventData.fromJson(json, CreateUserCommand.class);
        CreateUserCommand command = (CreateUserCommand) eventData.getData();
        this.userService.cancelCreateUser(command.getIdUser());
    }
}
