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
public class ChannelFormBean extends org.apache.struts.action.ActionForm {
    
    private String createChannel;
    private String visibleUrl;
    private String removeChannel;
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

    
    public String getVisibleUrl() {
        return visibleUrl;
    }

    public void setVisibleUrl(String visibleUrl) {
        this.visibleUrl = visibleUrl;
    }
    
    public String getCreateChannel() {
        return createChannel;
    }

    public void setCreateChannel(String createChannel) {
        this.createChannel = createChannel;
    }

    public String getRemoveChannel() {
        return removeChannel;
    }

    public void setRemoveChannel(String removeChannel) {
        this.removeChannel = removeChannel;
    }

    
    
}
