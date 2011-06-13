/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.VisibilityValidation;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rahul
 */
@Stateless
public class VisibilityValidationFacade implements VisibilityValidationFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(VisibilityValidation visibilityValidation) {
        em.persist(visibilityValidation);
    }

    public void edit(VisibilityValidation visibilityValidation) {
        em.merge(visibilityValidation);
    }

    public void remove(VisibilityValidation visibilityValidation) {
        em.remove(em.merge(visibilityValidation));
    }

    public VisibilityValidation find(Object id) {
        return em.find(VisibilityValidation.class, id);
    }

    public List<VisibilityValidation> findAll() {
        return em.createQuery("select object(o) from VISIBILITY_VALIDATION_TABLE as o").getResultList();
    }
    public VisibilityValidation findByVisibilityControlName(String vcName){
        if(em.createQuery("select s from VISIBILITY_VALIDATION_TABLE s where VISIBILITY_NAME=?1").setParameter(1, vcName).getResultList().isEmpty())
            return null;
        else
            return (VisibilityValidation) em.createQuery("select s from VISIBILITY_VALIDATION_TABLE s where VISIBILITY_NAME=?1").setParameter(1, vcName).getSingleResult();
    }


}
