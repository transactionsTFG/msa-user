package business.publisher;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

@Stateless
public class JMSPublisher implements IJMSPublisher {

    private ConnectionFactory connectionFactory;
    private Queue orchestratorQueue;

    public JMSPublisher() {}

    @Inject
    public JMSPublisher(ConnectionFactory connectionFactory, Queue orchestratorQueue) {
        this.connectionFactory = connectionFactory;
        this.orchestratorQueue = orchestratorQueue;
    }

    @Override
    public void publish(String event, Object data){
        try(JMSContext jmsContext = connectionFactory.createContext()) {
            jmsContext.createProducer().send(this.orchestratorQueue, event);
        }
    }

}
