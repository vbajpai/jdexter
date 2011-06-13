package beans;

public class PasswordFormBean extends org.apache.struts.action.ActionForm {
    
    private String currentpwd;
    private String newpwd;
    private String confirmnewpwd;
    private String error;
    private String success;

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

    public String getConfirmnewpwd() {
        return confirmnewpwd;
    }

    public void setConfirmnewpwd(String confirmnewpwd) {
        this.confirmnewpwd = confirmnewpwd;
    }

    public String getNewpwd() {
        return newpwd;
    }

    public void setNewpwd(String newpwd) {
        this.newpwd = newpwd;
    }

    public String getCurrentpwd() {
        return currentpwd;
    }

    public void setCurrentpwd(String currentpwd) {
        this.currentpwd = currentpwd;
    }

}
