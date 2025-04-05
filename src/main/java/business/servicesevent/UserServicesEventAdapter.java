package business.servicesevent;

import business.dto.CreateUserDTO;

public interface UserServicesEventAdapter {
    boolean beginCreateUser(CreateUserDTO userDTO);
}
