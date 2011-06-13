/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.UserPrivacy;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rahul
 */
@Remote
public interface UserPrivacyFacadeRemote {

    void create(UserPrivacy userPrivacy);

    void edit(UserPrivacy userPrivacy);

    void remove(UserPrivacy userPrivacy);

    UserPrivacy find(Object id);

    List<UserPrivacy> findAll();

}
