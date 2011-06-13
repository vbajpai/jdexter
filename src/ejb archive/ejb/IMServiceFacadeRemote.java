/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.IMService;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rahul
 */
@Remote
public interface IMServiceFacadeRemote {

    void create(IMService iMService);

    void edit(IMService iMService);

    void remove(IMService iMService);

    IMService find(Object id);

    List<IMService> findAll();

    public entity.IMService findByServiceName(java.lang.String serviceName);

}
