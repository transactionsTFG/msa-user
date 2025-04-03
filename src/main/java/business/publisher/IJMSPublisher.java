package business.publisher;


public interface IJMSPublisher {
    void publish(String event, Object data);    
}
