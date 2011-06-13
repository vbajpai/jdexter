package beans;

public class SignupFormBean extends org.apache.struts.action.ActionForm {

    private String country;
    private String username;
    private String password;
    private String confirmpassword;
    private String firstname;
    private String lastname;
    private String email;
    private String city;
    private String website;
    private String gender;
    private String day;
    private String month;
    private String year;
    // TODO VB: Find How to Get File
    private String file;
    private String dayjoined;
    private String monthjoined;
    private String yearjoined;

    public String getYearjoined() {
        return yearjoined;
    }

    public void setYearjoined(String yearjoined) {
        this.yearjoined = yearjoined;
    }

    public String getMonthjoined() {
        return monthjoined;
    }

    public void setMonthjoined(String monthjoined) {
        this.monthjoined = monthjoined;
    }

    public String getDayjoined() {
        return dayjoined;
    }

    public void setDayjoined(String dayejoined) {
        this.dayjoined = dayejoined;
    }


    /* frags to display on JSP */
    private String error;
    private String success;

    /* fields having default values, not asked in signup */
    private String landingpage;
    private String activityprivacy;

    private static final String CLASSIC = "classic";
    private static final String IDEXTER = "idexter";
    private static final String EVERYONE = "everyone";
    private static final String FRIENDS = "friends";
    private static final String ME = "me";

    public SignupFormBean() {
        landingpage=CLASSIC;
        activityprivacy=EVERYONE;        
    }

    public String getActivityprivacy() {
        return activityprivacy;
    }

    public void setActivityprivacy(String activityprivacy) {
        this.activityprivacy = activityprivacy;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public String getLandingpage() {
        return landingpage;
    }

    public void setLandingpage(String landingpage) {
        this.landingpage = landingpage;
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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }   

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
