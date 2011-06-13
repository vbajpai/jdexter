/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.UserPreference;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rahul
 */
@Remote
public interface UserPreferenceFacadeRemote {

    void create(UserPreference userPreference);

    void edit(UserPreference userPreference);

    void remove(UserPreference userPreference);

    UserPreference find(Object id);

    List<UserPreference> findAll();

}
