package domainevent.command.handler;

import javax.ejb.EJB;

import business.services.UserService;

public abstract class BaseHandler implements EventHandler {
    protected UserService userService;
    
    @EJB
    public void setTypeUserServices(UserService userService) {
        this.userService = userService;
    }
}
