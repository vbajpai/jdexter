package actions;

import beans.LoginFormBean;
import beans.PreferenceFormBean;
import beans.PrivacyFormBean;
import beans.SignupFormBean;
import ejb.BannedUserFacadeRemote;
import ejb.ModeratorFacadeRemote;
import ejb.UserFacadeRemote;
import ejb.UserLinkFacadeRemote;
import entity.User;

import entity.UserLink;
import entity.UserPreference;
import entity.UserPrivacy;
import entity.VisibilityValidation;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import utilities.EJBUtility;
import utilities.UserUtility;

public class LoginAction extends Action {

    private final static String CLASSICSUCCESS = "classic";
    private final static String IDEXTERSUCCESS = "idexter";
    private final static String FAILURE = "failure";
    private final static String ADMINISTRATOR = "administrator";
    private final static String MODERATOR = "moderator";
    private final static String USER = "user";

    /* beans */
    private User userRecord;
    private UserLink userLinkRecord;
    private LoginFormBean formbean;

    /* remote interfaces */
    private UserFacadeRemote userFacadeRemote;
    private ModeratorFacadeRemote moderatorFacadeRemote;
    private UserLinkFacadeRemote userLinkFacadeRemote;
    /*Added By Rahul on 11 march */
    private BannedUserFacadeRemote bannedUserFacadeRemote;
    /* url redirecting to either search/idexter page */
    String redirectionURL;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        formbean = (LoginFormBean) form;
        userFacadeRemote = (UserFacadeRemote) EJBUtility.lookup("UserFacade");

        if (usernameValid(formbean.getUsername())) {
            if (passwordValid(formbean.getPassword())) {
                if (accountEnabled()) { // user authenticated!
                    if (!userBanned()) {

                        /* Save User Context in Session */
                        saveUserContext(request.getSession());

                        /* Check if User got Redirected */
                        if (checkRedirectionSuccess()) {
                            response.sendRedirect(redirectionURL);
                        }

                        /* user is signing in directly rather from redirection */
                        if (userRecord.getLandingPage().equalsIgnoreCase("classic")) {
                            return mapping.findForward(CLASSICSUCCESS);
                        } else {
                            return mapping.findForward(IDEXTERSUCCESS);
                        }


                    } else {//account is banned

                        formbean.setError("Your Banned, Contact Administrator!");

                        if (checkRedirectionFailure()) {
                            response.sendRedirect(redirectionURL);
                        }
                        return mapping.findForward(FAILURE);
                    }

                } else {   // account is disabled!
                    if (formbean.isEnable()) {

                        /* Enable Account */
                        userRecord.setIfValid(true);
                        /* Update Database */
                        userFacadeRemote.edit(userRecord);

                        /* Reset Enable Parameter in FormBean */
                        formbean.setEnable(false);

                        /* Save User Context in Session */
                        saveUserContext(request.getSession());

                        /* Check if User got Redirected */
                        if (checkRedirectionSuccess()) {
                            response.sendRedirect(redirectionURL);
                        }

                        if (userRecord.getLandingPage().equalsIgnoreCase("classic")) {
                            return mapping.findForward(CLASSICSUCCESS);
                        } else {
                            return mapping.findForward(IDEXTERSUCCESS);
                        }

                    } else {
                        formbean.setError("Your Account is Disabled!");
                        if (checkRedirectionFailure()) {
                            response.sendRedirect(redirectionURL);
                        }
                        return mapping.findForward(FAILURE);
                    }
                }
            } else {   // password does not match!
                formbean.setError("Password Incorrect!");
                if (checkRedirectionFailure()) {
                    response.sendRedirect(redirectionURL);
                }
                return mapping.findForward(FAILURE);
            }
        } else {   // username does not exist!
            formbean.setError("Username Does Not Exist!");
            if (checkRedirectionFailure()) {
                response.sendRedirect(redirectionURL);
            }
            return mapping.findForward(FAILURE);
        }
    }

    /* @return
     *      ADMINISTRATOR: if user is administrator
     *      MODERATOR: if user is moderator
     *      USER: if user is neither. */
    public String getUserRole(String username) throws NamingException {

        if (username.equals("admin")) {   // admin
            return (ADMINISTRATOR);
        } else {
            /* check if moderator */
            moderatorFacadeRemote = (ModeratorFacadeRemote) EJBUtility.lookup("ModeratorFacade");
            if (moderatorFacadeRemote.ifModerator(userRecord)) {
                return (MODERATOR);
            } else {
                return (USER);
            }



        }
    }

    /* @return
     *         TRUE: if map successful
     *         FALSE: if map unsuccessful */
    public boolean mapBeanUserDB(SignupFormBean userbean) throws NamingException {

        /* Get UserLink corressponding to User */
        userLinkFacadeRemote = (UserLinkFacadeRemote) EJBUtility.lookup("UserLinkFacade");
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

        /* Push EJB Properties to POJO */
        userbean.setUsername(userRecord.getUserName());
        userbean.setPassword(userRecord.getPassword());
        userbean.setConfirmpassword(userRecord.getPassword());
        userbean.setFirstname(userRecord.getFirstName());
        userbean.setLastname(userRecord.getLastName());
        userbean.setEmail(userRecord.getEmail());
        userbean.setCountry(userRecord.getCountry());
        userbean.setCity(userRecord.getLocation());

        /* get the joining date */
        Date dateJoined = userRecord.getDateJoined();
        if (dateJoined != null) {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateJoined);
            Integer day = new Integer(calendar.get(Calendar.DAY_OF_MONTH));
            Integer month = new Integer(calendar.get(Calendar.MONTH) + 1);    // month starts with 0
            Integer year = new Integer(calendar.get(Calendar.YEAR));

            userbean.setDayjoined(day.toString());
            userbean.setMonthjoined(UserUtility.getMonth(month));
            userbean.setYearjoined(year.toString());
        }

        /* getting the first link for now */
        if (userLinkRecord != null) {
            userbean.setWebsite(userLinkRecord.getLink());
        }

        /*  0 - male
        1 - female
         */
        if (userRecord.isGender()) {
            userbean.setGender("female");
        } else {
            userbean.setGender("male");
        }

        Date dateOfBirth = userRecord.getDateOfBirth();
        if (dateOfBirth != null) {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateOfBirth);
            Integer day = new Integer(calendar.get(Calendar.DAY_OF_MONTH));
            Integer month = new Integer(calendar.get(Calendar.MONTH) + 1);    // month starts with 0
            Integer year = new Integer(calendar.get(Calendar.YEAR));
            userbean.setDay(day.toString());
            userbean.setMonth(month.toString());
            userbean.setYear(year.toString());
        }


        // TODO:VB get profilepic

        userbean.setLandingpage(userRecord.getLandingPage());
        VisibilityValidation visibilityPrivacy = userRecord.getActivityVisibleTo();
        userbean.setActivityprivacy(visibilityPrivacy.getVisibilityName());


        return (true);
    }

    /* true: password matches against username in DB
    false: password does not match */
    public boolean passwordValid(String password) {

        if (userRecord.getPassword().equals(password)) {
            return (true);   // password valid.
        }

        return (false);      // password invalid.
    }

    /* true: username exists in DB
    false: username does not exist in DB */
    public boolean usernameValid(String username) {

        /* Get User Table Recordset */
        /*Edited By Rahul on 11 March*/
        userRecord = userFacadeRemote.findByUserName(username);
        if (userRecord != null) {
            return true;
        } else {
            return false;
        }    
    }

    private boolean accountEnabled() {
        if (userRecord.isIfValid()) {
            return (true);
        } else {
            return (false);
        }
    }

    private boolean checkRedirectionFailure() {

        redirectionURL = "";

        //check if user got redirected from search page
        String searchquery = formbean.getSearchstring();
        String searchpage = formbean.getSearchpage();

        if (!searchquery.equals("") && !searchpage.equals("")) {
            redirectionURL = "/Dexter/web/guest/ssl/signin.jsp?forwardsearch=" + searchquery + "&forwardpage=" + searchpage;
        }

        //check if user got redirected from idexter page
        String idexterformdata = formbean.getIdexterpage();
        if (!idexterformdata.equals("")) {
            redirectionURL = "/Dexter/web/guest/ssl/signin.jsp?forwardidexter=" + idexterformdata;
        }

        if (!redirectionURL.equals("")) {
            return (true);
        } else {
            return (false);
        }

    }

    private boolean checkRedirectionSuccess() {

        redirectionURL = "";

        //check if user got redirected from search page
        String searchquery = formbean.getSearchstring();
        String searchpage = formbean.getSearchpage();

        if (!searchquery.equals("") && !searchpage.equals("")) {
            redirectionURL = "/Dexter/web/user/clear/search.jsp?search=" + searchquery + "&page=" + searchpage;
        }

        //check if user got redirected from idexter page
        String idexterformdata = formbean.getIdexterpage();
        if (!idexterformdata.equals("")) {
            redirectionURL = "/Dexter/actions/idexter.do?page=" + idexterformdata;
        }

        if (!redirectionURL.equals("")) {    /* user signing in via redirection */
            return (true);
        } else {
            return (false);
        }
    }

    private void populatePreferenceBean(PreferenceFormBean bean, UserPreference record) {

        int intreplyCollapsed;
        int intcommentSort;

        bean.setCommentPageSize(record.getCommentPageSize());
        bean.setCommentThreshold(record.getCommentThreshold());

        boolean replyCollapsed = record.isCommentReplyCollapsed();
        if (replyCollapsed == true) {
            intreplyCollapsed = 1;
        } else {
            intreplyCollapsed = 0;
        }

        boolean commentSort = record.isDefaultCommentSort();
        if (commentSort == true) {
            intcommentSort = 1;
        } else {
            intcommentSort = 0;
        }

        bean.setCommentReplyCollapsed(intreplyCollapsed);
        bean.setCommentSort(intcommentSort);
    }

    private void populatePrivacyBean(PrivacyFormBean privacybean, UserPrivacy privacyRecord) {


        VisibilityValidation ageVisibility = privacyRecord.getAgeVisibility();
        int ageValue = (int) ageVisibility.getVisibilityValidationId();
        privacybean.setAge(ageValue);

        VisibilityValidation emailVisibility = privacyRecord.getEmailVisibility();
        int emailValue = (int) emailVisibility.getVisibilityValidationId();
        privacybean.setEmail(emailValue);

        VisibilityValidation genderVisibility = privacyRecord.getGenderVisibility();
        int genderValue = (int) genderVisibility.getVisibilityValidationId();
        privacybean.setGender(genderValue);

        VisibilityValidation locationVisibility = privacyRecord.getLocationVisibilityVisibility();
        int locationValue = (int) locationVisibility.getVisibilityValidationId();
        privacybean.setLocation(locationValue);

        VisibilityValidation nameVisibility = privacyRecord.getNameVisibility();
        int nameValue = (int) nameVisibility.getVisibilityValidationId();
        privacybean.setName(nameValue);

        VisibilityValidation profilelinksVisibility = privacyRecord.getProfileLinksVisibility();
        int profilelinksValue = (int) profilelinksVisibility.getVisibilityValidationId();
        privacybean.setProfilelinks(profilelinksValue);

        VisibilityValidation shoutsVisibility = privacyRecord.getShoutsVisibility();
        int shoutsValue = (int) shoutsVisibility.getVisibilityValidationId();
        privacybean.setShouts(shoutsValue);
    }

    private void saveUserContext(HttpSession session) throws NamingException, Exception {

        // check if user is admin, moderator, or casual user.
        String role = getUserRole(formbean.getUsername());

        // save username and role in session object.
        session.setAttribute("username", formbean.getUsername());
        session.setAttribute("role", role);
        session.setAttribute("userRecord", userRecord);

        // instantiate the userbean
        SignupFormBean userbean = new SignupFormBean();

        //fetch userdetails from DB and map in SignupFormBean;push Bean to Session Object
        if (mapBeanUserDB(userbean)) {
            session.setAttribute("SignupFormBean", userbean);
        } else {
            throw new Exception("Error while mapping user details from DB to bean");
        }

        /* Populate PreferenceBean */
        PreferenceFormBean preferencebean = new PreferenceFormBean();
        UserPreference preferenceRecord = UserUtility.userPreferenceExists(userRecord);
        if (preferenceRecord != null) {
            populatePreferenceBean(preferencebean, preferenceRecord);
            session.setAttribute("PreferenceFormBean", preferencebean);
        }

        /* Populate PrivacyBean */
        PrivacyFormBean privacybean = new PrivacyFormBean();
        UserPrivacy privacyRecord = UserUtility.userPrivacyExists(userRecord);
        if (privacyRecord != null) {
            populatePrivacyBean(privacybean, privacyRecord);
            session.setAttribute("PrivacyFormBean", privacybean);
        }
    }

    private boolean userBanned() throws NamingException {
        bannedUserFacadeRemote = (BannedUserFacadeRemote) EJBUtility.lookup("BannedUserFacade");
        if(bannedUserFacadeRemote.ifBanned(userRecord))
            return true;
        else
            return false;

    }
}
