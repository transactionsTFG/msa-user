package business.eventdispatcher;

import msa.commons.event.EventId;

public interface IJMSEventDispatcher {
    void publish(EventId event, Object data);    
}
