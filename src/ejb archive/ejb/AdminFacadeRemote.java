/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Admin;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rahul
 */
@Remote
public interface AdminFacadeRemote {

    void create(Admin admin);

    void edit(Admin admin);

    void remove(Admin admin);
       
    Admin find(Object id);

    List<Admin> findAll();

}
