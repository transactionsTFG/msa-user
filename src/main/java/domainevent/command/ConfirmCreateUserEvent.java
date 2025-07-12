package domainevent.command;

import javax.ejb.Local;
import javax.ejb.Stateless;

import business.qualifier.CommitUserQualifier;
import domainevent.command.handler.BaseHandler;
import domainevent.command.handler.EventHandler;
import msa.commons.commands.user.CreateUserCommand;
import msa.commons.event.EventData;

@Stateless
@CommitUserQualifier
@Local(EventHandler.class)
public class ConfirmCreateUserEvent extends BaseHandler {    
    @Override
    public void handleCommand(String json) {
        EventData eventData = EventData.fromJson(json, CreateUserCommand.class);
        CreateUserCommand command = (CreateUserCommand) eventData.getData();
        this.userService.confirmCreateUser(command.getIdUser());
    } 
}
