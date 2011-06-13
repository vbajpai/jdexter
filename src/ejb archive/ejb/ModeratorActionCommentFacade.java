/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.ModeratorActionComment;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rahul
 */
@Stateless
public class ModeratorActionCommentFacade implements ModeratorActionCommentFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(ModeratorActionComment moderatorActionComment) {
        em.persist(moderatorActionComment);
    }

    public void edit(ModeratorActionComment moderatorActionComment) {
        em.merge(moderatorActionComment);
    }

    public void remove(ModeratorActionComment moderatorActionComment) {
        em.remove(em.merge(moderatorActionComment));
    }

    public ModeratorActionComment find(Object id) {
        return em.find(ModeratorActionComment.class, id);
    }

    public List<ModeratorActionComment> findAll() {
        return em.createQuery("select object(o) from MODERATOR_ACTION_COMMENT_TABLE as o").getResultList();
    }

}
