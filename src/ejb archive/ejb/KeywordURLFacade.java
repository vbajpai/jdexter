/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.KeywordURL;
import entity.URL;
import java.util.ArrayList;
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
public class KeywordURLFacade implements KeywordURLFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(KeywordURL keywordURL) {
        em.persist(keywordURL);
    }

    public void edit(KeywordURL keywordURL) {
        em.merge(keywordURL);
    }

    public void remove(KeywordURL keywordURL) {
        em.remove(em.merge(keywordURL));
    }

    public KeywordURL find(Object id) {
        return em.find(KeywordURL.class, id);
    }

    public List<KeywordURL> findAll() {
        return em.createQuery("select object(o) from KEYWORD_URL_TABLE as o").getResultList();
    }
    public boolean ifExistKeywordURL(KeywordURL keywordURL)
    {
        if(em.createQuery(("select u from KEYWORD_URL_TABLE u where URL_ID=?1 AND KEYWORD=?2")).setParameter(1, keywordURL.getUrl().getId()).setParameter(2, keywordURL.getKeyword()).getResultList().isEmpty())
            return false;
        else
            return true;
    }

    public KeywordURL findbyURLandKeyword(KeywordURL keywordURL){

        Query q = em.createQuery("select u from KEYWORD_URL_TABLE u where URL_ID=?1 AND KEYWORD=?2").setParameter(1, keywordURL.getUrl().getId()).setParameter(2, keywordURL.getKeyword());
        KeywordURL keywordURLRecord = (KeywordURL) q.getSingleResult();
        return keywordURLRecord;
    }
    
    public KeywordURL findbyURLandKeywordID(URL url, long id){

        Query q = em.createQuery("select u from KEYWORD_URL_TABLE u where URL_ID=?1 AND KEYWORD_URL_ID=?2").setParameter(1, url.getId()).setParameter(2, id);
        KeywordURL keywordURLRecord = (KeywordURL) q.getSingleResult();
        return keywordURLRecord;
    }



    public ArrayList<KeywordURL> getListwithKeyword(String keyword)
    {
        return (ArrayList<KeywordURL>) em.createQuery("select k from KEYWORD_URL_TABLE k where KEYWORD=?1").setParameter(1, keyword).getResultList();
    }
}
