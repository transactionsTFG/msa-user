package domainevent.registry;

import java.util.EnumMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import domainevent.command.handler.EventHandler;

import msa.commons.event.EventId;
import business.qualifier.CommitUserQualifier;
import business.qualifier.RollbackUserQualifier;
import business.qualifier.ValidateUserQualifier;

@Singleton
@Startup
public class EventHandlerRegistry {
    private Map<EventId, EventHandler> handlers = new EnumMap<>(EventId.class);
    private EventHandler confirmCreateUseHandler;
    private EventHandler cancelCreateUseHandler;
    private EventHandler validateUserHandler;

    @PostConstruct
    public void init(){
        this.handlers.put(EventId.CREATE_USER, confirmCreateUseHandler);
        this.handlers.put(EventId.FAILED_USER, cancelCreateUseHandler);
        this.handlers.put(EventId.VALIDATE_USER, validateUserHandler);
    }

    public EventHandler getHandler(EventId eventId) {
        return this.handlers.get(eventId);
    }

    @Inject
    public void setConfirmCreateUsHandler(@CommitUserQualifier EventHandler confirmCreateUseHandler) {
        this.confirmCreateUseHandler = confirmCreateUseHandler;
    }
    @Inject
    public void setCancelCreateUsHandler(@RollbackUserQualifier EventHandler cancelCreateUseHandler) {
        this.cancelCreateUseHandler = cancelCreateUseHandler;
    }

    @Inject
    public void setValidateUserHandler(@ValidateUserQualifier EventHandler validateUserHandler) {
        this.validateUserHandler = validateUserHandler;
    }

}
