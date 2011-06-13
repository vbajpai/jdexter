package beans;

public class LoginFormBean extends org.apache.struts.action.ActionForm {
    
    private String username;
    private String password;
    private String error;

    // used to get search string and page number from search page to action servlet
    private String searchstring;
    private String searchpage;

    // used to get idexter page from idexter to action servlet
    private String idexterpage;

    // used to enable disabled accounts;
    private boolean enable;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getSearchpage() {
        return searchpage;
    }

    public void setSearchpage(String searchpage) {
        this.searchpage = searchpage;
    }

    public String getIdexterpage() {
        return idexterpage;
    }

    public void setIdexterpage(String idexterpage) {
        this.idexterpage = idexterpage;
    }

    public String getSearchstring() {
        return searchstring;
    }

    public void setSearchstring(String searchstring) {
        this.searchstring = searchstring;
    }   

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setError(String error){
        this.error = error;
    }

    public String getError(){
        return error;
    }
}


    
