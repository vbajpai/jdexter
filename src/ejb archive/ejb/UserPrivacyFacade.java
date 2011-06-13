/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.UserPrivacy;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rahul
 */
@Stateless
public class UserPrivacyFacade implements UserPrivacyFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(UserPrivacy userPrivacy) {
        em.persist(userPrivacy);
    }

    public void edit(UserPrivacy userPrivacy) {
        em.merge(userPrivacy);
    }

    public void remove(UserPrivacy userPrivacy) {
        em.remove(em.merge(userPrivacy));
    }

    public UserPrivacy find(Object id) {
        return em.find(UserPrivacy.class, id);
    }

    public List<UserPrivacy> findAll() {
        return em.createQuery("select object(o) from USER_PRIVACY_TABLE as o").getResultList();
    }

}
