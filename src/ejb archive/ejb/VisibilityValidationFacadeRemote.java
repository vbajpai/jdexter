/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.VisibilityValidation;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rahul
 */
@Remote
public interface VisibilityValidationFacadeRemote {

    void create(VisibilityValidation visibilityValidation);

    void edit(VisibilityValidation visibilityValidation);

    void remove(VisibilityValidation visibilityValidation);

    VisibilityValidation find(Object id);

    List<VisibilityValidation> findAll();

    public entity.VisibilityValidation findByVisibilityControlName(java.lang.String vcName);

}
