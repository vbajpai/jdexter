/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.UserLink;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rahul
 */
@Remote
public interface UserLinkFacadeRemote {

    void create(UserLink userLink);

    void edit(UserLink userLink);

    void remove(UserLink userLink);

    UserLink find(Object id);

    List<UserLink> findAll();

    public java.util.List<entity.UserLink> findByUser(entity.User user);

}
