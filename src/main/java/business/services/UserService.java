package business.services;

import business.dto.CreateUserDTO;
import business.user.UserDTO;

public interface UserService {
    UserDTO beginCreateUser(CreateUserDTO userDTO);
    void confirmCreateUser(long id);
    void cancelCreateUser(long id);
    UserDTO getUserByEmail(String email);
    UserDTO validateUserByEmail(String email);
}
