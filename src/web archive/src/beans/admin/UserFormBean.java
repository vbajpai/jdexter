package beans.admin;

import org.apache.struts.action.ActionForm;

public class UserFormBean extends ActionForm {

    private String banneduser;
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBanneduser() {
        return banneduser;
    }

    public void setBanneduser(String banneduser) {
        this.banneduser = banneduser;
    }

}
