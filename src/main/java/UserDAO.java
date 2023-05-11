import java.util.List;

public interface UserDAO {
    void createUser(User user);

    List<User> getAllUsers();

    User getUserById(int id);

    User updateUser(User user);

    void deleteUser(User user);

    public void addRoleFromUser(User user, Role role);
}