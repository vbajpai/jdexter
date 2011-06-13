/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.KeywordActivity;
import entity.KeywordURL;
import entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rahul
 */
@Stateless
public class KeywordActivityFacade implements KeywordActivityFacadeRemote {
    
    @PersistenceContext
    private EntityManager em;

    public void create(KeywordActivity keywordActivity) {
        em.persist(keywordActivity);
    }

    public void edit(KeywordActivity keywordActivity) {
        em.merge(keywordActivity);
    }

    public void remove(KeywordActivity keywordActivity) {
        em.remove(em.merge(keywordActivity));
    }
    public KeywordActivity find(Object id) {
        return em.find(KeywordActivity.class, id);
    }

    public KeywordActivity findbyUserKeywordURL(User user, KeywordURL keywordURL){
        if(em.createQuery("select k from KEYWORD_ACTIVITY_TABLE k where KEYWORD_URL_ID=?1 AND USER_ID=?2").setParameter(1, keywordURL.getId()).setParameter(2, user.getUserId()).getResultList().isEmpty())
            return null;
        else
            return (KeywordActivity) em.createQuery("select k from KEYWORD_ACTIVITY_TABLE k where KEYWORD_URL_ID=?1 AND USER_ID=?2").setParameter(1, keywordURL.getId()).setParameter(2, user.getUserId()).getSingleResult();
    }

    public entity.KeywordActivity findbyUserKeywordURL(entity.User user, long keywordURLID){

        if(em.createQuery("select k from KEYWORD_ACTIVITY_TABLE k where KEYWORD_URL_ID=?1 AND USER_ID=?2").setParameter(1, keywordURLID).setParameter(2, user.getUserId()).getResultList().isEmpty())
            return null;
        else
            return (KeywordActivity) em.createQuery("select k from KEYWORD_ACTIVITY_TABLE k where KEYWORD_URL_ID=?1 AND USER_ID=?2").setParameter(1, keywordURLID).setParameter(2, user.getUserId()).getSingleResult();

    }
 
}
