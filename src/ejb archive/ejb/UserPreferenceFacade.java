/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.UserPreference;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rahul
 */
@Stateless
public class UserPreferenceFacade implements UserPreferenceFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(UserPreference userPreference) {
        em.persist(userPreference);
    }

    public void edit(UserPreference userPreference) {
        em.merge(userPreference);
    }

    public void remove(UserPreference userPreference) {
        em.remove(em.merge(userPreference));
    }

    public UserPreference find(Object id) {
        return em.find(UserPreference.class, id);
    }

    public List<UserPreference> findAll() {
        return em.createQuery("select object(o) from USER_PREFERENCE_TABLE as o").getResultList();
    }

}
