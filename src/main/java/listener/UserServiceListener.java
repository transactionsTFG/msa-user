package listener;

import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.transaction.Transactional;

import com.google.gson.Gson;

import business.command.handler.EventHandler;
import integration.startup.EventHandlerRegistry;
import msa.commons.event.Event;


@MessageDriven(mappedName = "jms/userServiceQueue")
public class UserServiceListener implements MessageListener{
    
    private Gson gson;
    private EventHandlerRegistry eventHandlerRegistry;

    @Override
    @Transactional 
    public void onMessage(Message msg) {
        try {
            if(msg instanceof TextMessage m) {
                Event event = this.gson.fromJson(m.getText(), Event.class);
                EventHandler commandHandler = this.eventHandlerRegistry.getHandler(event.getEventId());
                if(commandHandler != null)
                    commandHandler.handleCommand(event.getData());
            }
        } catch (Exception e) {
            System.out.println("Error al recibir el mensaje: " + e.getMessage());
        }
    }
    
    @Inject
    public void setGson(Gson gson) { this.gson = gson; }
    @EJB
    public void setCommandHandlerRegistry(EventHandlerRegistry commandHandlerRegistry) { this.eventHandlerRegistry = commandHandlerRegistry; }
}
