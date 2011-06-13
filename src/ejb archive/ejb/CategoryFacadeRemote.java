/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Category;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rahul
 */
@Remote
public interface CategoryFacadeRemote {

    void create(Category category);

    void edit(Category category);

    void remove(Category category);

    Category find(Object id);

    List<Category> findAll();
    Category findByName(String name);

    public java.util.List<entity.Category> findAllParentCategory();

    public boolean ifParent(entity.Category category);

}
