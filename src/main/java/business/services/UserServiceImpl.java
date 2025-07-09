package business.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import business.dto.CreateUserDTO;
import business.mapper.UserMapper;
import business.user.User;
import business.user.UserDTO;

import msa.commons.saga.SagaPhases;

@Stateless
public class UserServiceImpl implements UserService {

    private EntityManager entityManager;

    public UserServiceImpl() {}

    @Inject
    public UserServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    
    @Override
    public UserDTO validateUserByEmail(String email) {
        UserDTO u = this.getUserByEmail(email);
        if (u == null || !u.getActive() || u.getStatus() != SagaPhases.COMPLETED)
            return null;
        return u;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        List<User> listUser = this.entityManager.createNamedQuery("User.findByEmail", User.class)
                .setParameter("email", email).getResultList();
        return listUser.isEmpty() ? null : UserMapper.INSTANCE.entityToDto(listUser.get(0));
    }

    @Override
    public UserDTO beginCreateUser(CreateUserDTO userDTO) {
        if (!this.entityManager.createNamedQuery("User.findByEmail", User.class)
                .setParameter("email", userDTO.getEmail()).getResultList().isEmpty()) 
            return null;
        
        User u = UserMapper.INSTANCE.createUserDTOtoEntity(userDTO, SagaPhases.STARTED);
        this.entityManager.persist(u);
        this.entityManager.flush();
        return UserMapper.INSTANCE.entityToDto(u);
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
