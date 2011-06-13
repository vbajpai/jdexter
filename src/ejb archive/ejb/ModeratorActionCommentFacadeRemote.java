/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.ModeratorActionComment;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rahul
 */
@Remote
public interface ModeratorActionCommentFacadeRemote {

    void create(ModeratorActionComment moderatorActionComment);

    void edit(ModeratorActionComment moderatorActionComment);

    void remove(ModeratorActionComment moderatorActionComment);

    ModeratorActionComment find(Object id);

    List<ModeratorActionComment> findAll();

}
