/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.UserChosenCategory;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rahul
 */
@Stateless
public class UserChosenCategoryFacade implements UserChosenCategoryFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(UserChosenCategory userChosenCategory) {
        em.persist(userChosenCategory);
    }

    public void edit(UserChosenCategory userChosenCategory) {
        em.merge(userChosenCategory);
    }

    public void remove(UserChosenCategory userChosenCategory) {
        em.remove(em.merge(userChosenCategory));
    }

    public UserChosenCategory find(Object id) {
        return em.find(UserChosenCategory.class, id);
    }

    public List<UserChosenCategory> findAll() {
        return em.createQuery("select object(o) from USER_CHOSEN_CATEGORY_TABLE as o").getResultList();
    }

}
