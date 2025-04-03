package business.usecase.createuser;


import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import business.publisher.IJMSPublisher;
import integration.User;


@Stateless
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private IJMSPublisher jmsPublisher;
    private EntityManager entityManager;

    public CreateUserUseCaseImpl() {}

    @Inject
    public CreateUserUseCaseImpl(IJMSPublisher jmsPublisher, EntityManager entityManager) {
        this.jmsPublisher = jmsPublisher;
        this.entityManager = entityManager;
    }

    @Override
    public void handleCreateUser(String payload) {
        User u = new User("e","e", "e", "e", "e", 1);
        this.entityManager.persist(u);
        this.jmsPublisher.publish("CreateUserCommand", u);
    }
    
}
