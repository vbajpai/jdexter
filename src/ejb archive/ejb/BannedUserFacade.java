/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.BannedUser;
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
public class BannedUserFacade implements BannedUserFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(BannedUser bannedUser) {
        em.persist(bannedUser);
    }

    public void edit(BannedUser bannedUser) {
        em.merge(bannedUser);
    }

    public void remove(BannedUser bannedUser) {
        em.remove(em.merge(bannedUser));
    }

    public BannedUser find(Object id) {
        return em.find(BannedUser.class, id);
    }

    public List<BannedUser> findAll() {
        return em.createQuery("select object(o) from BANNED_USER_TABLE as o").getResultList();
    }

    public boolean ifBanned(User user){
        if(!em.createQuery("select u from BANNED_USER_TABLE u where USER_ID_BANNED=?1").setParameter(1, user.getUserId()).getResultList().isEmpty())
            return true;
        else
            return false;
    }
    

    public BannedUser findByUserId(Long userId){
       if(!em.createQuery("select u from BANNED_USER_TABLE u where USER_ID_BANNED=?1").setParameter(1, userId).getResultList().isEmpty())
       {
           return    (BannedUser) em.createQuery("select u from BANNED_USER_TABLE u where USER_ID_BANNED=?1").setParameter(1, userId).getSingleResult();
       }
       else
           return null;
   }
}
