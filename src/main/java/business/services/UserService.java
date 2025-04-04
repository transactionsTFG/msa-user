package business.services;

import business.dto.CreateUserDTO;

public interface UserService {
    boolean beginCreateUser(CreateUserDTO userDTO);
    void confirmCreateUser(long id);
    void cancelCreateUser(long id);
}
