package domainevent.publisher;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import com.google.gson.Gson;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import msa.commons.event.EventId;
import msa.commons.consts.JMSQueueNames;
import msa.commons.event.Event;

@Stateless
public class JMSEventPublisher implements IJMSEventPublisher {
    private static final Logger LOGGER = LogManager.getLogger(JMSEventPublisher.class);
    private ConnectionFactory connectionFactory;
    private Queue orchestratorQueue;
    private Gson gson;

    @Override
    public void publish(EventId eventId, Object data){
        try(JMSContext jmsContext = connectionFactory.createContext()) {
            Event sendMsg = new Event(eventId, data);
            final String msg = this.gson.toJson(sendMsg);
            LOGGER.info("Publicando en Cola {}, Evento Id: {}, Mensaje: {}", JMSQueueNames.AGENCY_ORCHESTATOR_QUEUE, eventId, msg);
            jmsContext.createProducer().send(this.orchestratorQueue, msg);
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
