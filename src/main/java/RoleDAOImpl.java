import javax.persistence.EntityManager;

public class RoleDAOImpl implements RoleDAO {
    @Override
    public void createRole(Role role) {
        EntityManager em = CreateEntityManager.getEntityManager();
        em.getTransaction().begin();
        em.persist(role);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Role getRoleById(int id) {
        EntityManager em = CreateEntityManager.getEntityManager();
        return em.find(Role.class, id);
    }
}