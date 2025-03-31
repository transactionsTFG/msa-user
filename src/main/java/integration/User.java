package integration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
}
