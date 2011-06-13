/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.UserIMDetail;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rahul
 */
@Remote
public interface UserIMDetailFacadeRemote {

    void create(UserIMDetail userIMDetail);

    void edit(UserIMDetail userIMDetail);

    void remove(UserIMDetail userIMDetail);

    UserIMDetail find(Object id);

    List<UserIMDetail> findAll();

}
