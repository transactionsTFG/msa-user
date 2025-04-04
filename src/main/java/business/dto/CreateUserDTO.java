package business.dto;

import lombok.Data;

@Data
public class CreateUserDTO {
    private String born;
    private String email;
    private String name;
    private String password;
    private String phone;
    private String surname;
    private String typeUser;
}
