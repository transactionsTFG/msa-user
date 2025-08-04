package business.servicesevent;

import business.dto.CreateUserDTO;
import business.dto.LoginUserDTO;
import business.user.UserDTO;

public interface UserServicesEventAdapter {
    boolean beginCreateUser(CreateUserDTO userDTO);
    UserDTO getUser(LoginUserDTO loginUserDTO);
}
