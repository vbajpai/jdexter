/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.BlockedShouts;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rahul
 */
@Remote
public interface BlockedShoutsFacadeRemote {

    void create(BlockedShouts blockedShouts);

    void edit(BlockedShouts blockedShouts);

    void remove(BlockedShouts blockedShouts);

    BlockedShouts find(Object id);

    List<BlockedShouts> findAll();

    public boolean isShoutBlocked(entity.User from, entity.User to);

    public entity.BlockedShouts findBlockedShoutsByFromTo(entity.User from, entity.User to);

}
