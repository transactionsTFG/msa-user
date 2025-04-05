package domainevent.command;

import javax.ejb.Local;
import javax.ejb.Stateless;

import domainevent.command.handler.BaseHandler;
import domainevent.command.handler.EventHandler;
import msa.commons.microservices.user.qualifier.CommitUserQualifier;
import msa.commons.parser.NumberParser;

@Stateless
@CommitUserQualifier
@Local(EventHandler.class)
public class ConfirmCreateUserEvent extends BaseHandler {    
    @Override
    public void handleCommand(Object event) {
        long idTypeUser = NumberParser.toLong(event);
        this.userService.confirmCreateUser(idTypeUser);
    } 
}
