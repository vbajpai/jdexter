/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.User;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rahul
 */
@Remote
public interface UserFacadeRemote {

    void create(User user);

    void edit(User user);

    void remove(User user);
   
    User find(Object id);

    List<User> findAll();

    public User findByUserName(java.lang.String userName);
   
}
