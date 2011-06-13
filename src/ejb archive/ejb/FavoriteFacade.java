/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Favorite;
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
public class FavoriteFacade implements FavoriteFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Favorite favorite) {
        em.persist(favorite);
    }

    public void edit(Favorite favorite) {
        em.merge(favorite);
    }

    public void remove(Favorite favorite) {
        em.remove(em.merge(favorite));
    }

    public Favorite find(Object id) {
        return em.find(Favorite.class, id);
    }

    public List<Favorite> findAll() {
        return em.createQuery("select object(o) from FAVORITE_TABLE as o order by FAVORITED_AT_TIME desc").getResultList();
    }

     public ArrayList<Favorite> findByUser(User user){
        return (ArrayList<Favorite>) em.createQuery("select u from FAVORITE_TABLE u WHERE USER_ID=?1 order by FAVORITED_AT_TIME desc").setParameter(1, user.getUserId()).getResultList();
     }
     public List<Favorite> findRecentFavoriteByUser(User user, int noOfRecords){
        return em.createQuery("select u from FAVORITE_TABLE u order by FAVORITED_AT_TIME desc WHERE USER_ID=?1 order by FAVORITED_AT_TIME desc").setParameter(1, user.getUserId()).setMaxResults(noOfRecords).getResultList();
     }
     public Favorite findByUserUrl(User user, URL url){
        if(em.createQuery("select u from FAVORITE_TABLE u WHERE USER_ID=?1 AND URL_ID=?2 order by FAVORITED_AT_TIME desc").setParameter(1, user.getUserId()).setParameter(2, url.getId()).getResultList().isEmpty())
            return null;
        else
            return (Favorite) em.createQuery("select u from FAVORITE_TABLE u WHERE USER_ID=?1 AND URL_ID=?2 order by FAVORITED_AT_TIME desc").setParameter(1, user.getUserId()).setParameter(2, url.getId()).getSingleResult();

     }
}
