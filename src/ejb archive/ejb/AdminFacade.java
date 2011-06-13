/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Admin;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rahul
 */
@Stateless
public class AdminFacade implements AdminFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Admin admin) {
        em.persist(admin);
    }

    public void edit(Admin admin) {
        em.merge(admin);
    }

    public void remove(Admin admin) {
        em.remove(em.merge(admin));
    }

    public Admin find(Object id) {
        return em.find(Admin.class, id);
    }

    public List<Admin> findAll() {
        return em.createQuery("select object(o) from Admin as o").getResultList();
    }

   

}
