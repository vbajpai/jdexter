/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Activity;
import entity.URL;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rahul
 */
@Stateless
public class ActivityFacade implements ActivityFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Activity activity) {
        em.persist(activity);
    }

    public void edit(Activity activity) {
        em.merge(activity);
    }

    public void remove(Activity activity) {
        em.remove(em.merge(activity));
    }

    public Activity find(Object id) {
        return em.find(Activity.class, id);
    }

    public List<Activity> findAll() {
        return em.createQuery("select object(o) from ACTIVITY_TABLE as o").getResultList();
    }
    public ArrayList<Activity> findActivityVotedUpByUser(User user){
        return (ArrayList<Activity>) em.createQuery("select u from ACTIVITY_TABLE u where USER_ID=?1 AND IF_VOTED_UP=TRUE order by ACTIVITY_TIME desc").setParameter(1,user.getUserId()).getResultList();
    }
    public List<Activity> findRecentActivityByUser(User user, int noOfRecords){
        return em.createQuery("select u from ACTIVITY_TABLE u order by ACTIVITY_TIME desc where USER_ID=?1").setParameter(1,user.getUserId()).setFirstResult(0).setMaxResults(noOfRecords).getResultList();
    }
    public Activity findByUserUrl(User user, URL url)
    {
        if(!em.createQuery("select u from ACTIVITY_TABLE u where USER_ID=?1 AND URL_ID=?2").setParameter(1, user.getUserId()).setParameter(2, url.getId()).getResultList().isEmpty())
            return (Activity) em.createQuery("select u from ACTIVITY_TABLE u where USER_ID=?1 AND URL_ID=?2").setParameter(1, user.getUserId()).setParameter(2, url.getId()).getSingleResult();
        else
            return null;

    }
    public boolean ifVotedUp(User user, URL url)
    {
        if(!em.createQuery("select u from ACTIVITY_TABLE u where USER_ID=?1 AND ACTIVITY_ID=?2 AND IF_VOTED_UP=?3").setParameter(1, user.getUserId()).setParameter(2, url.getId()).setParameter(3, true).getResultList().isEmpty())
            return true;
        else
            return false;

    }
    public ArrayList<Activity> findWhoVotedUpURL(URL url)
    {
        return (ArrayList<Activity>) em.createQuery("select a from ACTIVITY_TABLE a where URL_ID=?1 AND IF_VOTED_UP=TRUE").setParameter(1, url.getId()).getResultList();
    }

}
