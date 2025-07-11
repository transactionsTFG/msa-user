package domainevent.publisher;

import msa.commons.event.EventId;
import msa.commons.event.eventoperation.EventOperation;
public interface IJMSEventPublisher {
    void publish(EventId event, Object data);    
}
