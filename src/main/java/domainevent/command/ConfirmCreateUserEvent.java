package domainevent.command;

import javax.ejb.Local;
import javax.ejb.Stateless;

import business.qualifier.CommitUserQualifier;
import domainevent.command.handler.BaseHandler;
import domainevent.command.handler.EventHandler;
import msa.commons.parser.NumberParser;

@Stateless
@CommitUserQualifier
@Local(EventHandler.class)
public class ConfirmCreateUserEvent extends BaseHandler {    
    @Override
    public void handleCommand(String json) {
        long idTypeUser = NumberParser.toLong(json);
        this.userService.confirmCreateUser(idTypeUser);
    } 
}
