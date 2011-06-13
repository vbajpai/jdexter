package beans.admin;

public class ModeratorFormBean extends org.apache.struts.action.ActionForm {

    private String moderator;
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getModerator() {
        return moderator;
    }

    public void setModerator(String moderator) {
        this.moderator = moderator;
    }
  
}
