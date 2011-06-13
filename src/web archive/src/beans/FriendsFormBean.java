package beans;

import org.apache.struts.action.ActionForm;

public class FriendsFormBean extends ActionForm {

    private String username;

    /* To Echo Confirmation */
    private String success;
    private String error;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
