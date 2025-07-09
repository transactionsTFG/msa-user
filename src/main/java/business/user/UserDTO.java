package business.user;

import lombok.Data;
import msa.commons.saga.SagaPhases;

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
    private String typeUser;
    private SagaPhases status;
}
