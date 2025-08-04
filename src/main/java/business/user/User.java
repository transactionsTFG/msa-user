package business.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import msa.commons.saga.SagaPhases;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
@NamedQuery(name = "User.findByEmailAndPassword", query = "SELECT u FROM User u WHERE u.email = :email AND u.active = true AND u.password = :password")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private int version;
    @Column(columnDefinition = "boolean default true")
    private Boolean active;
    private String born;
    @Column(unique = true, nullable = false)
    private String email;
    private String name;
    private String password;
    private String phone;
    private String surname;
    @Column(nullable = false)
    private long typeUser;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SagaPhases status;
    
    public User(String email, String name, String password, String phone, String surname, long typeUser) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.surname = surname;
        this.typeUser = typeUser;
    }
}
