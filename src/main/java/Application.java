import java.util.HashSet;
import java.util.Set;

public class Application {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAOImpl();
        RoleDAO roleDAO = new RoleDAOImpl();
        Role role1 = new Role(RoleType.DEVELOPER);
        Role role2 = new Role(RoleType.DEFAULT);
        Role role3 = new Role(RoleType.ANALYST);
        Role role4 = new Role(RoleType.MANAGER);
        Role role5 = new Role(RoleType.TESTER);
        Role role6 = new Role(RoleType.DESIGNER);
        roleDAO.createRole(role1);
        roleDAO.createRole(role2);
        roleDAO.createRole(role3);
        roleDAO.createRole(role4);
        roleDAO.createRole(role5);
        roleDAO.createRole(role6);

        User user1 = new User(
                "Иван",
                "Ivan",
                "777"
        );
        User user2 = new User(
                "Дмитрий",
                "Dmitry",
                "890"
        );
        User user3 = new User(
                "Маша",
                "Masha",
                "999"
        );
        User user4 = new User(
                "Владимир",
                "Vladimir",
                "035"
        );
        userDAO.createUser(user1);
        userDAO.createUser(user2);
        userDAO.createUser(user3);
        userDAO.createUser(user4);
        userDAO.addRoleFromUser(userDAO.getUserById(1), roleDAO.getRoleById(1));
        userDAO.addRoleFromUser(userDAO.getUserById(2), roleDAO.getRoleById(2));
        userDAO.addRoleFromUser(userDAO.getUserById(2), roleDAO.getRoleById(3));
        userDAO.addRoleFromUser(userDAO.getUserById(3), roleDAO.getRoleById(4));

        System.out.println("Получили список пользователей из БД (без ролей):" + '\n' + userDAO.getAllUsers());
        System.out.println("У пользователя " + user1.getUserName() + " имеются следующие роли: " + '\n' + userDAO.getUserById(1).getRoles());
        System.out.println("Список пользователей по роли " + role2.getRoleName() + '\n' + roleDAO.getRoleById(2).getUsers());
        userDAO.deleteUser(userDAO.getUserById(4));
        System.out.println("Удалили пользователя " + user4.getUserName());

//Добавим нового пользователя с ролями
        Set<Role> roleSet1 = new HashSet<>();
        roleSet1.add(roleDAO.getRoleById(1));
        roleSet1.add(roleDAO.getRoleById(2));
        roleSet1.add(roleDAO.getRoleById(3));
        User user5 = new User(
                "Mao",
                "Maony",
                "555",
                roleSet1
        );
        userDAO.createUser(user5);

//Обновим пользователя (имя и роли)
        Set<Role> roleSet2 = new HashSet<>();
        roleSet2.add(roleDAO.getRoleById(5));
        roleSet2.add(roleDAO.getRoleById(6));
        User user1Update = new User(
                3,
                "Leo",
                user3.getLogin(),
                user3.getPassword(),
                roleSet2);
        userDAO.updateUser(user1Update);

        CreateEntityManager.emf.close();
    }
}
