/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Comment;
import entity.URL;
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
public class CommentFacade implements CommentFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Comment comment) {
        em.persist(comment);
    }

    public void edit(Comment comment) {
        em.merge(comment);
    }

    public void remove(Comment comment) {
        em.remove(em.merge(comment));
    }

    public Comment find(Object id) {
        return em.find(Comment.class, id);
    }

    public List<Comment> findAll() {
        return em.createQuery("select object(o) from COMMENT_TABLE as o").getResultList();
    }
    public  ArrayList<Comment> findByUrl(URL url){
        return (ArrayList<Comment>) em.createQuery("select u from COMMENT_TABLE u where URL_ID=?1 order by COMMENT_ID").setParameter(1, url.getId()).getResultList();
    }

}
