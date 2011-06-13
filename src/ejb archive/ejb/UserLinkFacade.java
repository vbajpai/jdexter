/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.User;
import entity.UserLink;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rahul
 */
@Stateless
public class UserLinkFacade implements UserLinkFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(UserLink userLink) {
        em.persist(userLink);
    }

    public void edit(UserLink userLink) {
        em.merge(userLink);
    }

    public void remove(UserLink userLink) {
        em.remove(em.merge(userLink));
    }

    public UserLink find(Object id) {
        return em.find(UserLink.class, id);
    }

    public List<UserLink> findAll() {
        return em.createQuery("select object(o) from USER_LINK_TABLE as o").getResultList();
    }
    public List<UserLink> findByUser(User user){
        return em.createQuery("select u from USER_LINK_TABLE u where USER_ID=?1").setParameter(1, user.getUserId()).getResultList();
    }

}
