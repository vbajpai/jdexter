/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.UserIMDetail;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rahul
 */
@Stateless
public class UserIMDetailFacade implements UserIMDetailFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(UserIMDetail userIMDetail) {
        em.persist(userIMDetail);
    }

    public void edit(UserIMDetail userIMDetail) {
        em.merge(userIMDetail);
    }

    public void remove(UserIMDetail userIMDetail) {
        em.remove(em.merge(userIMDetail));
    }

    public UserIMDetail find(Object id) {
        return em.find(UserIMDetail.class, id);
    }

    public List<UserIMDetail> findAll() {
        return em.createQuery("select object(o) from USER_IM_DETAIL_TABLE as o").getResultList();
    }

}
