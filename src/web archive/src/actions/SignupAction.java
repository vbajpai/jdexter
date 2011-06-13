package actions;

import beans.PreferenceFormBean;
import beans.PrivacyFormBean;
import beans.SignupFormBean;
import beans.utilities.SignupFormBeanUtility;

import ejb.UserFacadeRemote;
import ejb.UserPreferenceFacadeRemote;
import ejb.UserPrivacyFacadeRemote;
import ejb.VisibilityValidationFacadeRemote;
import entity.User;
import entity.UserPreference;
import entity.UserPrivacy;
import entity.VisibilityValidation;
import java.sql.Date;
import java.util.Calendar;
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

public class SignupAction extends Action {

    private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";
    private final static String USER = "user";
    private List<VisibilityValidation> visibilityrecordSet;
    private List<UserPrivacy> recordSet;
    private VisibilityValidation everyoneOBJ;
    private VisibilityValidation friendsOBJ;
    private VisibilityValidation meOBJ;
    private HttpSession session;
    private Date sqlDate;
    private UserPreference userPreference;
    private UserPrivacy userPrivacy;
    private User userRecord;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        boolean validate = false;

        SignupFormBeanUtility beanutil = new SignupFormBeanUtility();
        SignupFormBean formbean = (SignupFormBean) form;

        if (beanutil.ifNotMisformed(formbean) && beanutil.ifMandatoryNotEmpty(formbean)) {
            if (!beanutil.userexists(formbean.getUsername())) {
                if (!beanutil.emailexists(formbean.getEmail())) {
                    validate = true;
                } else {
                    formbean.setError("Email already Registered!");
                }
            } else {
                formbean.setError("User already exists!");
            }
        }

        if (validate == true) {      //Validation Succeeds

            session = request.getSession();
            sqlDate = new Date(System.currentTimeMillis());
            userRecord = new User(null, sqlDate, "", false, formbean.getPassword(), formbean.getUsername());

            if (beanutil.commitToDB(formbean, userRecord)) {       // Database Transaction Successful.

                /* Populate Default Preferences in Database */
                createDefaultPreferences();

                /* Populate Default Privacy in Database */
                createDefaultPrivacy();

                /* Save User Context in Session */
                saveUserContext(formbean);

                return mapping.findForward(SUCCESS);
            } else {
                throw new Exception("Cannot Write FormBean to Database!");
            }
        } else {   //Validation Fails

            /* Reset Password Field */
            formbean.setPassword("");
            formbean.setConfirmpassword("");
            return mapping.findForward(FAILURE);
        }
    }

    private void createDefaultPreferences() throws NamingException {

        /* Get Remote Object*/
        UserPreferenceFacadeRemote userPreferenceRemote = (UserPreferenceFacadeRemote) EJBUtility.lookup("UserPreferenceFacade");
        /*edited by rahul establishing remote connection to user*/
        UserFacadeRemote userRemote = (UserFacadeRemote) EJBUtility.lookup("UserFacade");

        /* Create New Preference Record */
        /*edited by rahul on 28 feb fetching user from database*/
        userRecord = userRemote.findByUserName(userRecord.getUserName());

        userPreference = new UserPreference(userRecord, false, 0, 25, false, true);
        userPreferenceRemote.create(userPreference);
    }

    private void createDefaultPrivacy() throws NamingException {

        /* Get Remote Object */
        UserPrivacyFacadeRemote privacyRemote = (UserPrivacyFacadeRemote) EJBUtility.lookup("UserPrivacyFacade");
        /*edited by rahul establishing remote connection to user*/
        UserFacadeRemote userRemote = (UserFacadeRemote) EJBUtility.lookup("UserFacade");

        VisibilityValidationFacadeRemote visibilityRemote = (VisibilityValidationFacadeRemote) EJBUtility.lookup("VisibilityValidationFacade");

        /* Get Recordset */
        recordSet = privacyRemote.findAll();
        visibilityrecordSet = visibilityRemote.findAll();

        /* Get VisibilityValidaiton Objects */
        getvisibilityObjects();

        /* Create UserPrivacy Object */
        /*edited by rahul on 28 feb fetching user from database*/
        userRecord = userRemote.findByUserName(userRecord.getUserName());

        userPrivacy = new UserPrivacy(userRecord, everyoneOBJ, everyoneOBJ, everyoneOBJ, everyoneOBJ, everyoneOBJ, everyoneOBJ, everyoneOBJ);

        privacyRemote.create(userPrivacy);

    }

    private void getvisibilityObjects() {

        /* Iterate the Visibility Recordset */
        Iterator<VisibilityValidation> iterator = visibilityrecordSet.iterator();
        while (iterator.hasNext()) {
            VisibilityValidation record = iterator.next();
            if (record.getVisibilityName().equals("everyone")) {
                everyoneOBJ = record;
            } else {
                if (record.getVisibilityName().equals("friends")) {
                    friendsOBJ = record;
                } else {
                    if (record.getVisibilityName().equals("me")) {
                        meOBJ = record;
                    }
                }
            }
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

    private void populatePrivacyBean(PrivacyFormBean bean, UserPrivacy record) {

        VisibilityValidation ageVisibility = record.getAgeVisibility();
        int ageValue = (int) ageVisibility.getVisibilityValidationId();
        bean.setAge(ageValue);

        VisibilityValidation emailVisibility = record.getEmailVisibility();
        int emailValue = (int) emailVisibility.getVisibilityValidationId();
        bean.setEmail(emailValue);

        VisibilityValidation genderVisibility = record.getGenderVisibility();
        int genderValue = (int) genderVisibility.getVisibilityValidationId();
        bean.setGender(genderValue);

        VisibilityValidation locationVisibility = record.getLocationVisibilityVisibility();
        int locationValue = (int) locationVisibility.getVisibilityValidationId();
        bean.setLocation(locationValue);

        VisibilityValidation nameVisibility = record.getNameVisibility();
        int nameValue = (int) nameVisibility.getVisibilityValidationId();
        bean.setName(nameValue);

        VisibilityValidation profilelinksVisibility = record.getProfileLinksVisibility();
        int profilelinksValue = (int) profilelinksVisibility.getVisibilityValidationId();
        bean.setProfilelinks(profilelinksValue);

        VisibilityValidation shoutsVisibility = record.getShoutsVisibility();
        int shoutsValue = (int) shoutsVisibility.getVisibilityValidationId();
        bean.setShouts(shoutsValue);
    }

    private void saveUserContext(SignupFormBean formbean) {

        /* Set Joining Date */
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sqlDate);
        Integer day = new Integer(calendar.get(Calendar.DAY_OF_MONTH));
        Integer month = new Integer(calendar.get(Calendar.MONTH) + 1);    // month starts with 0
        Integer year = new Integer(calendar.get(Calendar.YEAR));

        formbean.setDayjoined(day.toString());
        formbean.setMonthjoined(UserUtility.getMonth(month));
        formbean.setYearjoined(year.toString());

        /* Populate PreferenceBean */
        PreferenceFormBean preferencebean = new PreferenceFormBean();
        if (userPreference != null) {
            populatePreferenceBean(preferencebean, userPreference);
        }

        /* Populate PrivacyBean */
        PrivacyFormBean privacybean = new PrivacyFormBean();
        if (userPrivacy != null) {
            populatePrivacyBean(privacybean, userPrivacy);
        }

        /* Set Session Attributes */
        session.setAttribute("username", formbean.getUsername());
        session.setAttribute("role", USER);
        session.setAttribute("userRecord", userRecord);
        session.setAttribute("SignupFormBean", formbean);
        session.setAttribute("PreferenceFormBean", preferencebean);
        session.setAttribute("PrivacyFormBean", privacybean);        
    }
}
