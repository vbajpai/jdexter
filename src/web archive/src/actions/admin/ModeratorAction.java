package actions.admin;

import beans.admin.ModeratorFormBean;
import beans.utilities.ModeratorBean;
import beans.utilities.UserBean;
import ejb.ModeratorFacadeRemote;
import ejb.UserFacadeRemote;
import entity.Moderator;
import entity.User;
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

public class ModeratorAction extends Action {

    private final static String SUCCESS = "return";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ModeratorFormBean modbean = (ModeratorFormBean) form;
        String moderatorUsername = modbean.getModerator();
        String userUsername = modbean.getUser();

        /* Grant Moderator Priveledge */
        if (userUsername != null) {
            grantModerator(userUsername);
        }

        /* Revoke Moderator Priveledge */
        if (moderatorUsername != null) {
            revokeModerator(moderatorUsername);
        }

        /* Update Userbean */
        UserBean userbean = new UserBean();
        HttpSession session = request.getSession();
        session.setAttribute("UserBean", userbean);

        /* Update ModeratorBean */
        ModeratorBean moderatorbean = new ModeratorBean();
        session.setAttribute("ModeratorBean", moderatorbean);

        return mapping.findForward(SUCCESS);
    }

    private void grantModerator(String username) throws NamingException {

        ModeratorFacadeRemote moderatorFacadeRemote = (ModeratorFacadeRemote) EJBUtility.lookup("ModeratorFacade");
        UserFacadeRemote userFacadeRemote = (UserFacadeRemote) EJBUtility.lookup("UserFacade");
        Moderator moderator = null;

        List<User> recordSet = userFacadeRemote.findAll();
        Iterator<User> iterator = recordSet.iterator();
        while (iterator.hasNext()) {
            User record = iterator.next();
            if (record.getUserName().equals(username)) {
                moderator = new Moderator(record, true);
            }
        }

        moderatorFacadeRemote.create(moderator);
    }

    private void revokeModerator(String username) throws NamingException {
        ModeratorFacadeRemote moderatorFacadeRemote = (ModeratorFacadeRemote) EJBUtility.lookup("ModeratorFacade");

        List<Moderator> recordSet = moderatorFacadeRemote.findAll();
        Iterator<Moderator> iterator = recordSet.iterator();
        while (iterator.hasNext()) {
            Moderator record = iterator.next();
            User user = record.getUserId();
            if (user.getUserName().equals(username)) {
                moderatorFacadeRemote.remove(record);
            }
        }
    }
}
