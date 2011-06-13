/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Friends;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rahul
 */
@Remote
public interface FriendsFacadeRemote {

    void create(Friends friends);

    void edit(Friends friends);

    void remove(Friends friends);

    Friends find(Object id);

    List<Friends> findAll();

    public Friends findByUsernameFromTo(entity.User from, entity.User to);

    public List<Friends> findFriends(entity.User user);

    public List<Friends> findFriendRequest(entity.User user);

    public java.util.List<entity.Friends> findFewFriends(entity.User user, int noOfRecords);

}
