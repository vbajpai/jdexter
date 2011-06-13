/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Shouts;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rahul
 */
@Remote
public interface ShoutsFacadeRemote {

    void create(Shouts shouts);

    void edit(Shouts shouts);

    void remove(Shouts shouts);

    Shouts find(Object id);

    List<Shouts> findAll();

    public java.util.List<entity.Shouts> findBySentTo(entity.User to);

    public java.util.List<entity.Shouts> findRecentShoutsBysentTo(entity.User to, int noOfRecords);

}
