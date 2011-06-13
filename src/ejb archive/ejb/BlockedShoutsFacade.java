/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.BlockedShouts;
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
public class BlockedShoutsFacade implements BlockedShoutsFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(BlockedShouts blockedShouts) {
        em.persist(blockedShouts);
    }

    public void edit(BlockedShouts blockedShouts) {
        em.merge(blockedShouts);
    }

    public void remove(BlockedShouts blockedShouts) {
        em.remove(em.merge(blockedShouts));
    }

    public BlockedShouts find(Object id) {
        return em.find(BlockedShouts.class, id);
    }

    public List<BlockedShouts> findAll() {
        return em.createQuery("select object(o) from BLOCKED_SHOUTS_TABLE as o").getResultList();
    }
    public boolean isShoutBlocked(User from, User to){
        if(em.createQuery("select u from BLOCKED_SHOUTS_TABLE u where USER_ID_FROM=?1 AND USER_ID_TO=?2").setParameter(1, from.getUserId()).setParameter(2, to.getUserId()).getResultList().isEmpty())
            return false;
        else
            return true;
    }
    public BlockedShouts findBlockedShoutsByFromTo(User from,User to)
    {
        if(em.createQuery("select u from BLOCKED_SHOUTS_TABLE u where USER_ID_FROM=?1 AND USER_ID_TO=?2").setParameter(1, from.getUserId()).setParameter(2, to.getUserId()).getResultList().isEmpty())
            return null;
        else
            return (BlockedShouts) em.createQuery("select u from BLOCKED_SHOUTS_TABLE u where USER_ID_FROM=?1 AND USER_ID_TO=?2").setParameter(1, from.getUserId()).setParameter(2, to.getUserId()).getSingleResult();
    }
}
