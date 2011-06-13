/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.ModeratorActionURL;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rahul
 */
@Stateless
public class ModeratorActionURLFacade implements ModeratorActionURLFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(ModeratorActionURL moderatorActionURL) {
        em.persist(moderatorActionURL);
    }

    public void edit(ModeratorActionURL moderatorActionURL) {
        em.merge(moderatorActionURL);
    }

    public void remove(ModeratorActionURL moderatorActionURL) {
        em.remove(em.merge(moderatorActionURL));
    }

    public ModeratorActionURL find(Object id) {
        return em.find(ModeratorActionURL.class, id);
    }

    public List<ModeratorActionURL> findAll() {
        return em.createQuery("select object(o) from MODERATOR_ACTION_URL_TABLE as o").getResultList();
    }

}
