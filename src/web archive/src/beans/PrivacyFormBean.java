package beans;

import org.apache.struts.action.ActionForm;

public class PrivacyFormBean extends ActionForm {

    private int age;
    private int email;
    private int gender;
    private int location;
    private int name;
    private int profilelinks;
    private int shouts;
    private int userid;

    private String success;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getShouts() {
        return shouts;
    }

    public void setShouts(int shouts) {
        this.shouts = shouts;
    }

    public int getProfilelinks() {
        return profilelinks;
    }

    public void setProfilelinks(int profilelinks) {
        this.profilelinks = profilelinks;
    }


    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getEmail() {
        return email;
    }

    public void setEmail(int email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
}
