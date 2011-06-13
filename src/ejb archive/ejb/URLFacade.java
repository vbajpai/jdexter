/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Category;
import entity.URL;
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
public class URLFacade implements URLFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(URL uRL) {
        em.persist(uRL);
    }

    public void edit(URL uRL) {
        em.merge(uRL);
    }

    public void remove(URL uRL) {
        em.remove(em.merge(uRL));
    }

    public URL find(Object id) {
        return em.find(URL.class, id);
    }
    public URL findByURL(URL url){
        if(!em.createQuery("select u from URL_TABLE u where UNESCAPEDURL=?1").setParameter(1,url.getUsescapedURL()).getResultList().isEmpty())
            return (URL)em.createQuery("select u from URL_TABLE u where UNESCAPEDURL=?1").setParameter(1,url.getUsescapedURL()).getSingleResult();
        else
            return null;

    }
    public List<URL> findAll() {
        return em.createQuery("select object(o) from URL_TABLE as o").getResultList();
    }
    public List<URL> findTop10URLinCategory(Category category, int page){
        Query q = em.createQuery("select u from URL_TABLE u order by u.VOTE_UP-u.VOTE_DOWN where CATEGORY_NAME=?1").setParameter(1, category.getCategoryName());
        q.setFirstResult((page-1)*10).setMaxResults(10);
        return q.getResultList();
        
    }
    public boolean isUnescapedurlPresent(String unescapedurl){
        if(em.createQuery("select u from URL_TABLE u where UNESCAPEDURL=?1").setParameter(1, unescapedurl).getResultList().isEmpty())
            return false;
        else
            return true;
    }
    public List<URL> findReportedURLOrderByNoOfReports(){
        return em.createQuery("select u from URL_TABLE u where NO_OF_REPORTS>'0' order by NO_OF_REPORTS desc").getResultList();
    }
    public List<URL> findReportedURLOrderBySubmissionDate(){
        return em.createQuery("select u from URL_TABLE u where NO_OF_REPORTS>'0' order by SUBMISSION_DATE desc").getResultList();
    }

}
