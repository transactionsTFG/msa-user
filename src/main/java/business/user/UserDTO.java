package business.user;

import lombok.Data;

@Data
public class UserDTO {
    private long id;
    private Boolean active;
    private String born;
    private String email;
    private String name;
    private String password;
    private String phone;
    private String surname;
    private long typeUser;
}
