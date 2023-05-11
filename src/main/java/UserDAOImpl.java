
import javax.persistence.EntityManager;
import java.util.Iterator;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public void createUser(User user) {
        EntityManager em = CreateEntityManager.getEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<User> getAllUsers() {
        EntityManager em = CreateEntityManager.getEntityManager();
        return em.createNativeQuery("SELECT * FROM new_user", User.class).getResultList();
    }

    @Override
    public User getUserById(int id) {
        EntityManager em = CreateEntityManager.getEntityManager();
        return em.find(User.class, id);
    }

    @Override
    public void addRoleFromUser(User user, Role role) {
        EntityManager em = CreateEntityManager.getEntityManager();
        em.getTransaction().begin();
        User user1 = em.find(User.class, user.getUserId());
        Role role1 = em.find(Role.class, role.getRoleId());
        user1.getRoles().add(role1);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public User updateUser(User user) {
        EntityManager em = CreateEntityManager.getEntityManager();
        em.getTransaction().begin();
        User updateUser = em.find(User.class, user.getUserId());
        updateUser.setUserName(user.getUserName());
        updateUser.setLogin(user.getLogin());
        updateUser.setPassword(user.getPassword());
        Iterator<Role> roleIterator = updateUser.getRoles().iterator();
        while (roleIterator.hasNext()) {
            roleIterator.next();
            roleIterator.remove();
        }
        updateUser.setRoles(user.getRoles());
        for (Role role : user.getRoles()) {
            user.getRoles().add(role);
        }
        em.merge(updateUser);
        em.getTransaction().commit();
        em.close();
        return updateUser;
    }

    @Override
    public void deleteUser(User user) {
        EntityManager em = CreateEntityManager.getEntityManager();
        em.getTransaction().begin();
        User user1 = em.find(User.class, user.getUserId());
        em.remove(user1);
        em.getTransaction().commit();
        em.close();
    }
}