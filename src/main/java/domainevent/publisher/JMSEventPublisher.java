package domainevent.publisher;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import com.google.gson.Gson;

import msa.commons.event.EventId;
import msa.commons.event.Event;

@Stateless
public class JMSEventPublisher implements IJMSEventPublisher {

    private ConnectionFactory connectionFactory;
    private Queue orchestratorQueue;
    private Gson gson;

    @Override
    public void publish(EventId eventId, Object data){
        try(JMSContext jmsContext = connectionFactory.createContext()) {
            Event sendMsg = new Event(eventId, data);
            jmsContext.createProducer().send(this.orchestratorQueue, this.gson.toJson(sendMsg));
        }
    }

    @Inject
    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
    @Inject
    public void setOrchestratorQueue(Queue orchestratorQueue) {
        this.orchestratorQueue = orchestratorQueue;
    }
    @Inject
    public void setGson(Gson gson) {
        this.gson = gson;
    }
}
