package domainevent.command.handler;

import msa.commons.event.eventoperation.EventOperation;

public interface EventHandler {
    void handleCommand(String json, EventOperation operation);
}
