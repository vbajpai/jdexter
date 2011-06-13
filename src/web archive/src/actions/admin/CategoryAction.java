/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package actions.admin;

import beans.admin.CategoryFormBean;
import ejb.CategoryFacadeRemote;
import entity.Category;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import utilities.EJBUtility;

/**
 *
 * @author rahul
 */
public class CategoryAction extends org.apache.struts.action.Action {
    
    private final static String SUCCESS = "return";
    CategoryFacadeRemote categoryFacadeRemote;
    CategoryFormBean categoryFormBean;

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
            categoryFormBean= (CategoryFormBean) form;
            /*Get Remote Object*/
            categoryFacadeRemote = (CategoryFacadeRemote) EJBUtility.lookup("CategoryFacade");
            /*Create Category*/
            if(categoryFormBean.getCreateCategory()!=null)
                createCategory(categoryFormBean);
            /*Remove Category*/
            if(categoryFormBean.getRemoveCategory()!=null)
                removeCategory(categoryFormBean);
            /*Update Category*/
            HttpSession session = request.getSession();
            session.setAttribute("CategoryBean", categoryFacadeRemote.findAll());
            List<Category> parentCategoryList=categoryFacadeRemote.findAllParentCategory();

            parentCategoryList.add(new Category("null", new Date(), true));
                
            session.setAttribute("ParentCategoryBean", parentCategoryList);
        
        return mapping.findForward(SUCCESS);
    }

    private void createCategory(CategoryFormBean categoryFormBean) {
        if(categoryFormBean.getParentCategory().trim().length()==0)
            categoryFormBean.setAddError("Select Parent Category");
        else
        {

                if(categoryFormBean.getCreateCategory().trim().length()==0)
                    categoryFormBean.setAddError("Category Name not provided");
                else
                {   if(categoryFacadeRemote.findByName(categoryFormBean.getCreateCategory())!=null)
                    {
                        categoryFormBean.setAddError("Category already exists");
                    }else
                    {
                        Category category=new Category(categoryFormBean.getCreateCategory(), new Date(), true);
                        if(!categoryFormBean.getParentCategory().equalsIgnoreCase("null"))
                            category.setParentCategory(categoryFacadeRemote.findByName(categoryFormBean.getParentCategory()));
                       categoryFacadeRemote.create(category);
                       categoryFormBean.setAddSuccess("Category added");
                       categoryFormBean.setCreateCategory(null);
                    }
                }
        }
    }

    private void removeCategory(CategoryFormBean removeCategory) {
        Category category=categoryFacadeRemote.findByName(removeCategory.getRemoveCategory());
        if(category!=null)
        {
                if(categoryFacadeRemote.ifParent(category))
                    categoryFormBean.setAddError("You Cannot delete a category which still has children");
                categoryFacadeRemote.remove(category);
                categoryFormBean.setRemoveSuccess("Category Deleted");
        }
        else
        {
            categoryFormBean.setRemoveError("Category Couldn't be deleted");
        }
    }
}
