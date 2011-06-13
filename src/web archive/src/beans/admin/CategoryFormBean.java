/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beans.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author rahul
 */
public class CategoryFormBean extends org.apache.struts.action.ActionForm {
    
    private String createCategory;
    private String removeCategory;
    private String parentCategory;
    private String addSuccess;
    private String addError;
    private String removeSuccess;
    private String removeError;

    public String getCreateCategory() {
        return createCategory;
    }

    public void setCreateCategory(String createCategory) {
        this.createCategory = createCategory;
    }

   
    public String getAddError() {
        return addError;
    }

    public void setAddError(String addError) {
        this.addError = addError;
    }

    public String getAddSuccess() {
        return addSuccess;
    }

    public void setAddSuccess(String addSuccess) {
        this.addSuccess = addSuccess;
    }

    public String getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(String parentCategory) {
        this.parentCategory = parentCategory;
    }

    public String getRemoveCategory() {
        return removeCategory;
    }

    public void setRemoveCategory(String removeCategory) {
        this.removeCategory = removeCategory;
    }

    public String getRemoveError() {
        return removeError;
    }

    public void setRemoveError(String removeError) {
        this.removeError = removeError;
    }

    public String getRemoveSuccess() {
        return removeSuccess;
    }

    public void setRemoveSuccess(String removeSuccess) {
        this.removeSuccess = removeSuccess;
    }
    
}
