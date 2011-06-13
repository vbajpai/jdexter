/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.URL;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rahul
 */
@Remote
public interface URLFacadeRemote {

    void create(URL uRL);

    void edit(URL uRL);

    void remove(URL uRL);

    URL find(Object id);

    List<URL> findAll();

    public java.util.List<entity.URL> findTop10URLinCategory(entity.Category category, int page);

    public entity.URL findByURL(entity.URL url);

    public boolean isUnescapedurlPresent(java.lang.String unescapedurl);

    public java.util.List<entity.URL> findReportedURLOrderByNoOfReports();

    public java.util.List<entity.URL> findReportedURLOrderBySubmissionDate();

}
