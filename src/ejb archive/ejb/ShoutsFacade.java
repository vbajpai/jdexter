/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Shouts;
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
public class ShoutsFacade implements ShoutsFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Shouts shouts) {
        em.persist(shouts);
    }

    public void edit(Shouts shouts) {
        em.merge(shouts);
    }

    public void remove(Shouts shouts) {
        em.remove(em.merge(shouts));
    }

    public Shouts find(Object id) {
        return em.find(Shouts.class, id);
    }
  
    public List<Shouts> findAll() {

        return em.createQuery("select object(o) from SHOUTS_TABLE as o").getResultList();
        
    }
    public List<Shouts> findBySentTo(User to){
        return em.createQuery("select u from SHOUTS_TABLE u WHERE USER_ID_TO=?1").setParameter(1, to.getUserId()).getResultList();
        
    }
    public List<Shouts> findRecentShoutsBysentTo(User to, int noOfRecords){
        return em.createQuery("select u from SHOUTS_TABLE u order by SHOUT_TIME desc where USER_ID_TO=?1").setParameter(1,to.getUserId()).setFirstResult(0).setMaxResults(noOfRecords).getResultList();
    }

}
