package beans.utilities;

import beans.SignupFormBean;
import ejb.UserFacadeRemote;
import ejb.UserLinkFacadeRemote;
import ejb.VisibilityValidationFacadeRemote;
import entity.User;
import entity.UserLink;
import entity.VisibilityValidation;
import java.sql.Date;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.naming.NamingException;
import utilities.EJBUtility;
import java.util.regex.*;
public class SignupFormBeanUtility {

    private static final String MALE = "male";
    private static final String EDITPROFILE = "edit";
    private static final String CREATEPROFILE = "create";
    private static final String SELECTCOUNTRY = "Select Country";
    private UserFacadeRemote userFacadeRemote;

    public SignupFormBeanUtility() throws NamingException {
        userFacadeRemote = (UserFacadeRemote) EJBUtility.lookup("UserFacade");
    }

    public boolean emailexists(String email) {

        /* Get User Table Recordset */
        List<User> recordSet = userFacadeRemote.findAll();
        if (!recordSet.isEmpty()) {

            User userRecord;
            Iterator<User> iterator = recordSet.iterator();
            /* Iterate the Recordset */
            while (iterator.hasNext()) {
                userRecord = iterator.next();
                if (userRecord.getEmail().equals(email)) {      // email exists!
                    return (true);
                }
            }
        }
        return (false);
    }

    public boolean ifMandatoryNotEmpty(SignupFormBean formbean) {

        if (formbean.getUsername().trim().equals("")) {
            formbean.setError("Username Missing!");
            return (false);
        }
        if (formbean.getPassword().trim().equals("")) {
            formbean.setError("Password Missing!");
            return (false);
        }
        if (formbean.getConfirmpassword().trim().equals("")) {
            formbean.setError("Confirm Password Missing!");
            return (false);
        }
        if (formbean.getEmail().trim().equals("")) {
            formbean.setError("Email Missing!");
            return (false);
        }

        formbean.setError("");      // empty error field to prevent showing up in editProfile page.
        return (true);
    }

    public boolean ifNotMisformed(SignupFormBean formbean) {
        //TODO VB Signup Form Validation
        // Need to check FormBean Values against constraints set in the Database
        
        String password = formbean.getPassword().trim();
        String confirmpassword = formbean.getConfirmpassword().trim();
        String email =formbean.getEmail().trim();
        int day,month,year;
        try
        {
            day=Integer.parseInt(formbean.getDay());
            month=Integer.parseInt(formbean.getMonth());
            year=Integer.parseInt(formbean.getYear());
        }catch(NumberFormatException e)
        {
            formbean.setError("Date of Birth is invalid");
            return false;
        }
        if(year<1900)
        {
            formbean.setError("You can't be that old");
            return false;
        }
        if(month>12||month<1)
        {
            formbean.setError("Invalid Date");
                return false;
        }
        if(month==1 || month ==3 || month ==5|| month==7||month==8 || month==10 ||month==12)
        {
            if(day>31||day<1)
            {
                formbean.setError("Invalid Date");
                return false;
            }
        }
        else
        {
            if(month==2)
            {
                if(year%4==0)
                {
                    if(day>29)
                    {
                        formbean.setError("Invalid Date");
                        return false;

                    }
                }
                else
                {   if(day>28)
                    {
                        formbean.setError("Invalid Date");
                        return false;

                    }

                }
            }
            else
            {
                if(day>30)
                    {
                        formbean.setError("Invalid Date");
                        return false;

                    }
            }
        }

        if (!password.equals(confirmpassword)) {
            formbean.setError("Passwords Do Not Match!");
            return (false);
        }


        if(!validateEmail(email)){
            formbean.setError("Invalid Email Specified");
            return false;
        }


        /***********CHECK LENGTH*************/
        if(formbean.getCity().length()>255){
            formbean.setError("City name should be less than 255 characters long");
            return false;
        }
        if(formbean.getCountry().length()>255){
            formbean.setError("Country name should be less than 255 characters long");
            return false;
        }
        if(formbean.getEmail().length()>255){
            formbean.setError("Email should be less than 255 characters long");
            return false;
        }
        if(formbean.getFirstname().length()>255){
            formbean.setError("First name should be less than 255 characters long");
            return false;
        }
        if(formbean.getLastname().length()>255){
            formbean.setError("Last name should be less than 255 characters long");
            return false;
        }
        if(formbean.getPassword().length()>255){
            formbean.setError("Password should be less than 255 characters long");
            return false;
        }
        if(formbean.getPassword().length()<6){
            formbean.setError("Password should be atleast 6 characters long");
            return false;
        }
        if(formbean.getUsername().length()>255){
            formbean.setError("User name should be less than 255 characters long");
            return false;
        }
        
        return (true);
    }
    public boolean validateEmail(String email)
    {


      //Set the email pattern string
      Pattern p = Pattern.compile(".+@.+\\.[a-z]+");

      //Match the given string with the pattern
      Matcher m = p.matcher(email);

      //check whether match is found
      boolean matchFound = m.matches();

      return matchFound;
    }
    public boolean commitToDB(SignupFormBean userbean, User userRecord) throws NamingException {

        String action = null;

        /* check whether profile being created of edited */
        if (ifNewProfile(userRecord)) {
            action = CREATEPROFILE;
        } else {
            action = EDITPROFILE;
        }

        /* Push POJO properties to EJB */
        pushPOJO(userbean, userRecord);

        /* Update Changes to Database */
        if (action.equals(EDITPROFILE)) {
            userFacadeRemote.edit(userRecord);
            setWebsite(userbean, userRecord);
        } else {
            userFacadeRemote.create(userRecord);            
        }        
        return (true);
    }

    public boolean userexists(String username) {

        /* Get User Table Recordset */
        List<User> recordSet = userFacadeRemote.findAll();
        if (!recordSet.isEmpty()) {

            User userRecord;
            Iterator<User> iterator = recordSet.iterator();
            /* Iterate the Recordset */
            while (iterator.hasNext()) {
                userRecord = iterator.next();
                if (userRecord.getUserName().equals(username)) {      // username exists!
                    return (true);
                }
            }
        }
        return (false);
    }

    private boolean ifNewProfile(User userRecord) {
        if (userRecord.isIfValid()) {
            return (false);
        } else {
            return (true);
        }
    }

    private void pushPOJO(SignupFormBean userbean, User userRecord) throws NamingException {

        /* Set Mandatory Fields */
        userRecord.setEmail(userbean.getEmail());
        userRecord.setIfValid(true);

        /* Set Optional Fields */
        if (!userbean.getFirstname().equals("")) {
            userRecord.setFirstName(userbean.getFirstname());
        } else {
            /* left blank implies null */
            userRecord.setFirstName("");
        }
        if (!userbean.getLastname().equals("")) {
            userRecord.setLastName(userbean.getLastname());
        } else {
            userRecord.setLastName("");
        }
        if (!userbean.getCountry().equals(SELECTCOUNTRY)) {
            userRecord.setCountry(userbean.getCountry());
        } else {
            userRecord.setCountry("");
        }
        if (!userbean.getCity().equals("")) {
            userRecord.setLocation(userbean.getCity());
        } else {
            userRecord.setLocation("");
        }

        if (!userbean.getGender().equals(MALE)) {
            userRecord.setGender(true);
        } else {
            userRecord.setGender(false);
        }

        userRecord.setLandingPage(userbean.getLandingpage());
        setActivityPrivacy(userbean, userRecord);
        setDate(userbean, userRecord);

    //TODO: VB: PROFILE PICTURE
    }

    private void setActivityPrivacy(SignupFormBean userbean, User userRecord) throws NamingException {

        List<VisibilityValidation> visibilityRecordSet;
        VisibilityValidation vvobject = null;
        VisibilityValidationFacadeRemote vvfr = (VisibilityValidationFacadeRemote) EJBUtility.lookup("VisibilityValidationFacade");

        String activityprivacy = userbean.getActivityprivacy();
        visibilityRecordSet = vvfr.findAll();
        Iterator<VisibilityValidation> iterator = visibilityRecordSet.iterator();

        while (iterator.hasNext()) {
            VisibilityValidation record = iterator.next();
            if (record.getVisibilityName().equals(activityprivacy)) {
                vvobject = (VisibilityValidation) record;
            }
        }
        userRecord.setActivityVisibleTo(vvobject);
    }

    private void setDate(SignupFormBean userbean, User userRecord) {

        if (!userbean.getDay().equals("") && !userbean.getMonth().equals("") & !userbean.getYear().equals("")) {

            int day = Integer.parseInt(userbean.getDay());
            int month = Integer.parseInt(userbean.getMonth());
            int year = Integer.parseInt(userbean.getYear());

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month - 1, day);   // month starts with 0
            Date dateOfBirth = new Date(calendar.getTimeInMillis());
            userRecord.setDateOfBirth(dateOfBirth);
        } else {
            userRecord.setDateOfBirth(null);
        }
    }

    private void setWebsite(SignupFormBean userbean, User userRecord) throws NamingException {

        UserLink userLinkRecord = null;
                
        /* Get UserLink corressponding to User */
        UserLinkFacadeRemote userLinkFacadeRemote = (UserLinkFacadeRemote) EJBUtility.lookup("UserLinkFacade");
        List<UserLink> findAll = userLinkFacadeRemote.findAll();

        if (!findAll.isEmpty()) {
            Iterator<UserLink> iterator = findAll.iterator();
            while (iterator.hasNext()) {
                UserLink record = iterator.next();
                if (record.getUser().equals(userRecord)) {
                    /* get the first record that matches as of now */
                    userLinkRecord = record;
                }
            }
        }

        if (!userbean.getWebsite().equals("")) {

            if (userLinkRecord == null) {   // userLink table is empty or could not find record for the user

                userLinkRecord = new UserLink(userRecord, userbean.getWebsite(), true);
                /* Add New Record to Database */
                userLinkFacadeRemote.create(userLinkRecord);
            } else { // userLinkRecord exists!

                /* getting the first link for now */
                userLinkRecord.setLink(userbean.getWebsite());
                /* Update the Record */
                userLinkFacadeRemote.edit(userLinkRecord);
            }

        } else { // remove the existing record

            if (userLinkRecord != null) {
                userLinkFacadeRemote.remove(userLinkRecord);
            }

        }
    }
}
