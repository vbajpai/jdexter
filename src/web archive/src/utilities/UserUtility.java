package utilities;

import ejb.UserPreferenceFacadeRemote;
import ejb.UserPrivacyFacadeRemote;
import entity.User;
import entity.UserPreference;
import entity.UserPrivacy;
import java.util.Iterator;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

public class UserUtility {

    public static UserPreference userPreferenceExists(User userRecord) throws NamingException {

        /* Get Remote Object*/
        UserPreferenceFacadeRemote userPreferenceRemote = (UserPreferenceFacadeRemote) EJBUtility.lookup("UserPreferenceFacade");

        /* Get Recordset */
        List<UserPreference> recordSet = userPreferenceRemote.findAll();
        Iterator<UserPreference> iterator = recordSet.iterator();
        while (iterator.hasNext()) {
            UserPreference record = iterator.next();
            if (record.getUser().equals(userRecord)) {
                return (record);
            }
        }

        return (null);
    }

    public static boolean userExists(HttpSession session) {

        String username = (String) session.getAttribute("username");

        if (username == null || username.trim().equals("")) {  // no username key in session
            return (false);
        } else //username key exists in session
        {
            return (true);
        }
    }

    public static String getUserRole(HttpSession session) throws Exception {

        String role = (String) session.getAttribute("role");
        if (role != null) {
            return (role);
        } else {
            throw new Exception("role key does not exist!");
        }

    }

    public static String getMonth(Integer month) {

        String monthName = null;

        switch (month) {
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;
            case 8:
                monthName = "August";
                break;
            case 9:
                monthName = "September";
                break;
            case 10:
                monthName = "October";
                break;
            case 11:
                monthName = "November";
                break;
            case 12:
                monthName = "December";
                break;
        }

        return monthName;
    }

    public static UserPrivacy userPrivacyExists(User userRecord) throws NamingException {

        /* Get Remote Object*/
        UserPrivacyFacadeRemote privacyRemote = (UserPrivacyFacadeRemote) EJBUtility.lookup("UserPrivacyFacade");

        /* Get Recordset */
        List<UserPrivacy> recordSet = privacyRemote.findAll();
        Iterator<UserPrivacy> iterator = recordSet.iterator();
        while (iterator.hasNext()) {
            UserPrivacy record = iterator.next();
            if (record.getUser().equals(userRecord)) {
                return (record);
            }
        }

        return (null);


    }
}
