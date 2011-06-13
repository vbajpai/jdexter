/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.CommentReport;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rahul
 */
@Remote
public interface CommentReportFacadeRemote {

    void create(CommentReport commentReport);

    void edit(CommentReport commentReport);

    void remove(CommentReport commentReport);

    CommentReport find(Object id);

    List<CommentReport> findAll();

}
