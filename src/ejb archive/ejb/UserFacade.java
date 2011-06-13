/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rahul
 */
@Stateless
public class UserFacade implements UserFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(User user) {
        em.persist(user);
    }

    public void edit(User user) {
        em.merge(user);
    }

    public void remove(User user) {
        em.remove(em.merge(user));
    }

    public User find(Object id) {
        return em.find(User.class, id);
    }
    public List<User> findAll() {
        return em.createQuery("select object(o) from USER_TABLE as o").getResultList();
    }
    public User findByUserName(String userName){
        //returns null if no record exist
        if(!em.createQuery("select u from USER_TABLE u where USER_NAME = ?1").setParameter(1, userName).getResultList().isEmpty())
        {
            return (User)em.createQuery("select u from USER_TABLE u where USER_NAME = ?1").setParameter(1, userName).getSingleResult();
        }
        else
            return null;
    }
     


}
