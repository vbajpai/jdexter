/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.URLReport;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rahul
 */
@Remote
public interface URLReportFacadeRemote {

    void create(URLReport uRLReport);

    void edit(URLReport uRLReport);

    void remove(URLReport uRLReport);

    URLReport find(Object id);

    List<URLReport> findAll();

    public boolean ifURLReportedByUser(entity.User user, entity.URL url);

}
