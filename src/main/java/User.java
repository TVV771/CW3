import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "new_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "roles")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "user_name")
    private String userName;
    @Column
    private String login;
    @Column
    private String password;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date dateCreate;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date dateModified;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User(String userName, String login, String password) {
        this.userName = userName;
        this.login = login;
        this.password = password;
    }

    public User(String userName, String login, String password, Set<Role> roles) {
        this.userName = userName;
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    public User(Integer userId, String userName, String login, String password, Set<Role> roles) {
        this.userId = userId;
        this.userName = userName;
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Пользователь{" +
                "Id= " + userId +
                ", имя=" + userName +
                ", логин=" + login +
                ", пароль=" + password +
                ", дата создания пользователя=" + dateCreate +
                ", дата обновления пользователя=" + dateModified + '\n';
    }
}
