/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.BannedUser;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rahul
 */
@Remote
public interface BannedUserFacadeRemote {

    void create(BannedUser bannedUser);

    void edit(BannedUser bannedUser);

    void remove(BannedUser bannedUser);

    BannedUser find(Object id);

    List<BannedUser> findAll();

    public boolean ifBanned(entity.User user);

    public BannedUser findByUserId(Long userId);

}
