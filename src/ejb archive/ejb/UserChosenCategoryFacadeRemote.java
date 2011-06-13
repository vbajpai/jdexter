/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.UserChosenCategory;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rahul
 */
@Remote
public interface UserChosenCategoryFacadeRemote {

    void create(UserChosenCategory userChosenCategory);

    void edit(UserChosenCategory userChosenCategory);

    void remove(UserChosenCategory userChosenCategory);

    UserChosenCategory find(Object id);

    List<UserChosenCategory> findAll();

}
