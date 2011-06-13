/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.CommentReport;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rahul
 */
@Stateless
public class CommentReportFacade implements CommentReportFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(CommentReport commentReport) {
        em.persist(commentReport);
    }

    public void edit(CommentReport commentReport) {
        em.merge(commentReport);
    }

    public void remove(CommentReport commentReport) {
        em.remove(em.merge(commentReport));
    }

    public CommentReport find(Object id) {
        return em.find(CommentReport.class, id);
    }

    public List<CommentReport> findAll() {
        return em.createQuery("select object(o) from COMMENT_REPORT_TABLE as o").getResultList();
    }

}
