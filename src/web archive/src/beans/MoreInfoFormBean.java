/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author rahul
 */
public class MoreInfoFormBean extends org.apache.struts.action.ActionForm {
    
    private String report;
    private String success;
    private String error;
    private String shoutSuccess;
    private String shoutError;
    private String userName;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getShoutError() {
        return shoutError;
    }

    public void setShoutError(String shoutError) {
        this.shoutError = shoutError;
    }

    public String getShoutSuccess() {
        return shoutSuccess;
    }

    public void setShoutSuccess(String shoutSuccess) {
        this.shoutSuccess = shoutSuccess;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
    
}
