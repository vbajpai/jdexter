package beans.utilities;

import ejb.BannedUserFacadeRemote;
import ejb.ModeratorFacadeRemote;
import entity.Moderator;
import entity.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.naming.NamingException;
import utilities.EJBUtility;

public class ModeratorBean extends ArrayList implements Serializable {

    public ModeratorBean() throws NamingException {

        ModeratorFacadeRemote moderatorFacadeRemote = (ModeratorFacadeRemote) EJBUtility.lookup("ModeratorFacade");
        List<Moderator> recordSet = moderatorFacadeRemote.findAll();
        Iterator<Moderator> iterator = recordSet.iterator();
        while (iterator.hasNext()) {
            Moderator record = iterator.next();
            User user = record.getUserId();
            /* Check if Banned */
            if (!ifBanned(user)) {
                String userName = user.getUserName();
                add(new OptionItem(userName, userName));
            }
        }

        Collections.sort(this);
    }

    private boolean ifBanned(User user) throws NamingException {
        
        /* Get Remote Object */
        BannedUserFacadeRemote bannedUserRemote = (BannedUserFacadeRemote) EJBUtility.lookup("BannedUserFacade");

        return(bannedUserRemote.ifBanned(user));
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