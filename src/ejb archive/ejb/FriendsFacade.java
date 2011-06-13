/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Friends;
import entity.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author rahul
 */
@Stateless
public class FriendsFacade implements FriendsFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Friends friends) {
        em.persist(friends);
    }

    public void edit(Friends friends) {
        em.merge(friends);
    }

    public void remove(Friends friends) {
        em.remove(em.merge(friends));
    }

    public Friends find(Object id) {
        return em.find(Friends.class, id);
    }

    public List<Friends> findAll() {
        return em.createQuery("select object(o) from FRIENDS_TABLE as o").getResultList();
    }
    public Friends findByUsernameFromTo(User from,User to){
        Query q=em.createQuery("select f from FRIENDS_TABLE f where USER_ID_TO = ?1 AND USER_ID_FROM=?2" );
        q=q.setParameter(1, to.getUserId());
        q=q.setParameter(2, from.getUserId());
        if(!q.getResultList().isEmpty())
            return (Friends) q.getSingleResult();
        else
            return null;
    }
    public List<Friends> findFriends(User user){
        Query q=em.createQuery("select f from FRIENDS_TABLE f where USER_ID_TO=?1 AND IF_FRIEND=?2 OR USER_ID_FROM=?1 AND IF_FRIEND=?2");
        q=q.setParameter(1, user.getUserId());
        q=q.setParameter(2, true);
        return q.getResultList();
    }
    public List<Friends> findFriendRequest(User user){
        Query q = em.createQuery("select f from FRIENDS_TABLE f where USER_ID_TO=?1 AND IF_FRIEND=?2 order by DATE_REQUESTED desc");
        q = q.setParameter(1, user.getUserId());
        q = q.setParameter(2, false);
        return q.getResultList();
    }
    public List<Friends> findFewFriends(User user, int noOfRecords){
        Query q=em.createQuery("select f from FRIENDS_TABLE f where USER_ID_TO=?1 AND IF_FRIEND=?2 OR USER_ID_FROM=?1 AND IF_FRIEND=?2");
        q=q.setParameter(1, user.getUserId());
        q=q.setParameter(2, true);
        q=q.setFirstResult(0).setMaxResults(noOfRecords);
        return q.getResultList();
    }


}
