/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.URLDetail;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rahul
 */
@Remote
public interface URLDetailFacadeRemote {

    void create(URLDetail uRLDetail);

    void edit(URLDetail uRLDetail);

    void remove(URLDetail uRLDetail);

    URLDetail find(Object id);

    List<URLDetail> findAll();

    public entity.URLDetail findByURL(entity.URL url);

}
