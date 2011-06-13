/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.URL;
import entity.URLReport;
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
public class URLReportFacade implements URLReportFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(URLReport uRLReport) {
        em.persist(uRLReport);
    }

    public void edit(URLReport uRLReport) {
        em.merge(uRLReport);
    }

    public void remove(URLReport uRLReport) {
        em.remove(em.merge(uRLReport));
    }

    public URLReport find(Object id) {
        return em.find(URLReport.class, id);
    }

    public List<URLReport> findAll() {
        return em.createQuery("select object(o) from URL_REPORT_TABLE as o").getResultList();
    }
    public boolean ifURLReportedByUser(User user,URL url){
        if(em.createQuery("select u from URL_REPORT_TABLE u where REPORTED_BY=?1 AND REPORTED_URL_ID=?2").setParameter(1, user.getUserId()).setParameter(2, url.getId()).getResultList().isEmpty())
            return false;
        else
            return true;
        
    }
    
}
