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
public class IMFormBean extends org.apache.struts.action.ActionForm {
    
    private String createIM;
    private String removeIM;
    private String addSuccess;
    private String addError;
    private String removeSuccess;
    private String removeError;

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

    public String getCreateIM() {
        return createIM;
    }

    public void setCreateIM(String createIM) {
        this.createIM = createIM;
    }

    public String getRemoveError() {
        return removeError;
    }

    public void setRemoveError(String removeError) {
        this.removeError = removeError;
    }

    public String getRemoveIM() {
        return removeIM;
    }

    public void setRemoveIM(String removeIM) {
        this.removeIM = removeIM;
    }

    public String getRemoveSuccess() {
        return removeSuccess;
    }

    public void setRemoveSuccess(String removeSuccess) {
        this.removeSuccess = removeSuccess;
    }
    
}
