package business.services;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import business.dto.CreateUserDTO;
import business.mapper.UserMapper;
import business.user.User;
import domainevent.publisher.IJMSEventPublisher;
import msa.commons.event.EventId;
import msa.commons.microservices.user.commandevent.CreateUserCommand;
import msa.commons.saga.SagaPhases;

@Stateless
public class UserServiceImpl implements UserService {

    private EntityManager entityManager;
    private IJMSEventPublisher jmsEventDispatcher;

    public UserServiceImpl() {}

    @Inject
    public UserServiceImpl(EntityManager entityManager, IJMSEventPublisher jmsEventDispatcher) {
        this.entityManager = entityManager;
        this.jmsEventDispatcher = jmsEventDispatcher;
    }

    @Override
    public boolean beginCreateUser(CreateUserDTO userDTO) {
        if (this.entityManager.createNamedQuery("User.findByEmail", User.class)
                .setParameter("email", userDTO.getEmail()).getResultList().isEmpty()) 
            return false;
        
        User u = UserMapper.INSTANCE.createUserDTOtoEntity(userDTO, SagaPhases.STARTED);
        this.entityManager.persist(u);
        this.entityManager.flush();
        CreateUserCommand c = new CreateUserCommand(u.getId(), userDTO.getTypeUser());
        this.jmsEventDispatcher.publish(EventId.GET_TYPE_USER, c);
        return true;
    }

    @Override
    public void confirmCreateUser(long id) {
        User user = this.entityManager.find(User.class, id, LockModeType.OPTIMISTIC);
        user.setActive(true);
        user.setStatus(SagaPhases.COMPLETED);
        this.entityManager.merge(user);
    }

    @Override
    public void cancelCreateUser(long id) {
        User user = this.entityManager.find(User.class, id, LockModeType.OPTIMISTIC);
        this.entityManager.remove(user);
    }

    
    
}
