/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Moderator;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rahul
 */
@Remote
public interface ModeratorFacadeRemote {

    void create(Moderator moderator);

    void edit(Moderator moderator);

    void remove(Moderator moderator);

    Moderator find(Object id);

    List<Moderator> findAll();

    public boolean ifModerator(entity.User user);

}
