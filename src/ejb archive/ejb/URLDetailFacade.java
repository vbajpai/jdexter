/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.URL;
import entity.URLDetail;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rahul
 */
@Stateless
public class URLDetailFacade implements URLDetailFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(URLDetail uRLDetail) {
        em.persist(uRLDetail);
    }

    public void edit(URLDetail uRLDetail) {
        em.merge(uRLDetail);
    }

    public void remove(URLDetail uRLDetail) {
        em.remove(em.merge(uRLDetail));
    }

    public URLDetail find(Object id) {
        return em.find(URLDetail.class, id);
    }

    public List<URLDetail> findAll() {
        return em.createQuery("select object(o) from URL_DETAIL_TABLE as o").getResultList();
    }
    public URLDetail findByURL(URL url)
    {
        if(em.createQuery("select u from URL_DETAIL_TABLE u where URL_ID=?1").setParameter(1, url.getId()).getResultList().isEmpty())
            return null;
        else
            return (URLDetail) em.createQuery("select u from URL_DETAIL_TABLE u where URL_ID=?1").setParameter(1, url.getId()).getSingleResult();
            
    }

}
