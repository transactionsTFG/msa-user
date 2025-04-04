package domainevent.registry;

import java.util.EnumMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import domainevent.command.handler.EventHandler;
import domainevent.command.qualifier.FailCreateUserEventQualifier;
import domainevent.command.qualifier.SuccessUserEventQualifier;
import msa.commons.event.EventId;

@Singleton
@Startup
public class EventHandlerRegistry {
    private Map<EventId, EventHandler> handlers = new EnumMap<>(EventId.class);
    private EventHandler confirmCreateUseHandler;
    private EventHandler cancelCreateUseHandler;

    @PostConstruct
    public void init(){
        this.handlers.put(EventId.CREATE_USER, confirmCreateUseHandler);
        this.handlers.put(EventId.FAILED_USER, cancelCreateUseHandler);
    }

    public EventHandler getHandler(EventId eventId) {
        return this.handlers.get(eventId);
    }

    @Inject
    public void setConfirmCreateUsHandler(@SuccessUserEventQualifier EventHandler confirmCreateUseHandler) {
        this.confirmCreateUseHandler = confirmCreateUseHandler;
    }
    @Inject
    public void setCancelCreateUsHandler(@FailCreateUserEventQualifier EventHandler cancelCreateUseHandler) {
        this.cancelCreateUseHandler = cancelCreateUseHandler;
    }

}
