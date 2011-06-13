/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.ModeratorActionURL;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rahul
 */
@Remote
public interface ModeratorActionURLFacadeRemote {

    void create(ModeratorActionURL moderatorActionURL);

    void edit(ModeratorActionURL moderatorActionURL);

    void remove(ModeratorActionURL moderatorActionURL);

    ModeratorActionURL find(Object id);

    List<ModeratorActionURL> findAll();

}
