/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Moderator;
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
public class ModeratorFacade implements ModeratorFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Moderator moderator) {
        em.persist(moderator);
    }

    public void edit(Moderator moderator) {
        em.merge(moderator);
    }

    public void remove(Moderator moderator) {
        em.remove(em.merge(moderator));
    }

    public Moderator find(Object id) {
        return em.find(Moderator.class, id);
    }

    public List<Moderator> findAll() {
        return em.createQuery("select object(o) from MODERATOR_TABLE as o").getResultList();
    }
    public boolean ifModerator(User user){
        if(!em.createQuery("select u from MODERATOR_TABLE u where USER_ID=?1").setParameter(1, user.getUserId()).getResultList().isEmpty())
            return true;
        else
            return false;
            

    }

}
