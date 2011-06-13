/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Activity;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rahul
 */
@Remote
public interface ActivityFacadeRemote {

    void create(Activity activity);

    void edit(Activity activity);

    void remove(Activity activity);

    Activity find(Object id);

    List<Activity> findAll();

   
    public boolean ifVotedUp(entity.User user, entity.URL url);

    public entity.Activity findByUserUrl(entity.User user, entity.URL url);

    public java.util.List<entity.Activity> findRecentActivityByUser(entity.User user, int noOfRecords);

    public java.util.ArrayList<entity.Activity> findActivityVotedUpByUser(entity.User user);

    public java.util.ArrayList<entity.Activity> findWhoVotedUpURL(entity.URL url);

}
