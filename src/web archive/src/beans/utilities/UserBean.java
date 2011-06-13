package beans.utilities;

import ejb.BannedUserFacadeRemote;
import ejb.ModeratorFacadeRemote;
import ejb.UserFacadeRemote;
import entity.Moderator;
import entity.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.naming.NamingException;
import utilities.EJBUtility;

public class UserBean extends ArrayList implements Serializable {

    private static final String ADMINISTRATOR = "admin";

    public UserBean() throws NamingException {

        UserFacadeRemote userFacadeRemote = (UserFacadeRemote) EJBUtility.lookup("UserFacade");

        List<User> recordSet = userFacadeRemote.findAll();
        Iterator<User> iterator = recordSet.iterator();
        while (iterator.hasNext()) {
            User record = iterator.next();

            /*Check if user is admin */
            if (!record.getUserName().equals(ADMINISTRATOR)) {

                /*Check if user is already Moderator*/
                if (!ifModerator(record)) {

                    /* Check if user is Banned */
                    if (!ifBanned(record)) {

                        String userName = record.getUserName();
                        add(new OptionItem(userName, userName));
                    }

                }
            }
        }

        Collections.sort(this);
    }

    private boolean ifBanned(User record) throws NamingException {

        /* Get Remote Object */
        BannedUserFacadeRemote bannedUserRemote = (BannedUserFacadeRemote) EJBUtility.lookup("BannedUserFacade");

        return(bannedUserRemote.ifBanned(record));
    }

    private boolean ifModerator(User record) throws NamingException {

        ModeratorFacadeRemote moderatorFacadeRemote = (ModeratorFacadeRemote) EJBUtility.lookup("ModeratorFacade");
        List<Moderator> recordset = moderatorFacadeRemote.findAll();
        Iterator<Moderator> iterator = recordset.iterator();
        while (iterator.hasNext()) {
            Moderator modrecord = iterator.next();
            User userId = modrecord.getUserId();
            if (userId.getUserId() == record.getUserId()) {
                return (true);
            }
        }
        return (false);
    }

    public class OptionItem implements Comparable {

        private String id;
        private String label;

        public String getId() {
            return this.id;
        }

        public String getLabel() {
            return this.label;
        }

        public OptionItem(String id, String label) {
            this.id = id;
            this.label = label;
        }

        public int compareTo(Object o) {
            OptionItem oi = (OptionItem) o;
            return id.compareTo((oi.getId()));
        }
    }
}