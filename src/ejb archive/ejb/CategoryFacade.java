/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Category;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rahul
 */
@Stateless
public class CategoryFacade implements CategoryFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Category category) {
        em.persist(category);
    }

    public void edit(Category category) {
        em.merge(category);
    }

    public void remove(Category category) {
        em.remove(em.merge(category));
    }

    public Category find(Object id) {
        return em.find(Category.class, id);
    }

    public List<Category> findAll() {
        return em.createQuery("select object(o) from CATEGORY_TABLE as o").getResultList();
    }
    public Category findByName(String name){
        if(!em.createQuery("select c from CATEGORY_TABLE c where CATEGORY_NAME = ?1").setParameter(1, name).getResultList().isEmpty())
            return (Category)em.createQuery("select c from CATEGORY_TABLE c where CATEGORY_NAME = ?1").setParameter(1, name).getSingleResult();
       else
            return null;
    }
        public List<Category> findAllParentCategory(){
        return  em.createQuery("select c from CATEGORY_TABLE c where PARENT_CATEGORY_ID = NULL").getResultList();
    }
    public boolean ifParent(Category category){
        if(em.createQuery("select c from CATEGORY_TABLE c where PARENT_CATEGORY_ID=?1").setParameter(1, category.getId()).getResultList().isEmpty())
            return false;
        else
            return true;
    }

}
