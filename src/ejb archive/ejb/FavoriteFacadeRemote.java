/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Favorite;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rahul
 */
@Remote
public interface FavoriteFacadeRemote {

    void create(Favorite favorite);

    void edit(Favorite favorite);

    void remove(Favorite favorite);

    Favorite find(Object id);

    List<Favorite> findAll();

    public ArrayList<Favorite> findByUser(entity.User user);

    public java.util.List<entity.Favorite> findRecentFavoriteByUser(entity.User user, int noOfRecords);

    public entity.Favorite findByUserUrl(entity.User user, entity.URL url);

}
