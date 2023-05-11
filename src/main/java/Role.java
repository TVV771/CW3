import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "users")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;
    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private RoleType roleName;
    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    public Role(RoleType roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Роль {" +
                "Id=" + roleId +
                ", наименование=" + roleName + '\n';
    }
}