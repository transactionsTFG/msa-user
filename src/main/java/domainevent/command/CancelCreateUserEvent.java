package domainevent.command;

import javax.ejb.Local;
import javax.ejb.Stateless;

import business.qualifier.RollbackUserQualifier;
import domainevent.command.handler.BaseHandler;
import domainevent.command.handler.EventHandler;
import msa.commons.parser.NumberParser;

@Stateless
@RollbackUserQualifier
@Local(EventHandler.class)
public class CancelCreateUserEvent extends BaseHandler {    
    @Override
    public void handleCommand(Object event) {
        long idTypeUser = NumberParser.toLong(event);
        this.userService.cancelCreateUser(idTypeUser);
    }
}
