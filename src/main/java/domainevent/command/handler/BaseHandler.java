package domainevent.command.handler;

import javax.ejb.EJB;
import javax.inject.Inject;

import com.google.gson.Gson;

import business.services.UserService;
import domainevent.publisher.IJMSEventPublisher;

public abstract class BaseHandler implements EventHandler {
    protected UserService userService;
    protected IJMSEventPublisher jmsEventDispatcher;
    protected Gson gson;
    @EJB
    public void setTypeUserServices(UserService userService) {
        this.userService = userService;
    }
    @Inject
    public void setJmsEventDispatcher(IJMSEventPublisher jmsEventDispatcher) {
        this.jmsEventDispatcher = jmsEventDispatcher;
    }

    @Inject
    public void setGson(Gson gson) {
        this.gson = gson;
    }
}
