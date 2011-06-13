package beans.utilities;

import ejb.BannedUserFacadeRemote;
import ejb.UserFacadeRemote;
import entity.BannedUser;
import entity.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.naming.NamingException;
import utilities.EJBUtility;

public class BannedUserBean extends ArrayList implements Serializable {

    public BannedUserBean() throws NamingException {

        BannedUserFacadeRemote bannedUserRemote = (BannedUserFacadeRemote) EJBUtility.lookup("BannedUserFacade");

        List<BannedUser> recordSet = bannedUserRemote.findAll();
        Iterator<BannedUser> iterator = recordSet.iterator();
        while (iterator.hasNext()) {

            BannedUser userRecord = iterator.next();
            String userName = userRecord.getUserBanned().getUserName();
            add(new OptionItem(userName, userName));
        }

        Collections.sort(this);
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