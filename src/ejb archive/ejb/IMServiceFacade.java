/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.IMService;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rahul
 */
@Stateless
public class IMServiceFacade implements IMServiceFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(IMService iMService) {
        em.persist(iMService);
    }

    public void edit(IMService iMService) {
        em.merge(iMService);
    }

    public void remove(IMService iMService) {
        em.remove(em.merge(iMService));
    }

    public IMService find(Object id) {
        return em.find(IMService.class, id);
    }

    public List<IMService> findAll() {
        return em.createQuery("select object(o) from IM_SERVICE_TABLE as o").getResultList();
    }
    public IMService findByServiceName(String serviceName){
        if(em.createQuery("select s from IM_SERVICE_TABLE s where SERVICE_NAME=?1").setParameter(1, serviceName).getResultList().isEmpty())
            return null;
        else
            return (IMService) em.createQuery("select s from IM_SERVICE_TABLE s where SERVICE_NAME=?1").setParameter(1, serviceName).getSingleResult();
    }

}
