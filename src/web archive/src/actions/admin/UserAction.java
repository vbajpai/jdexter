package actions.admin;

import beans.admin.UserFormBean;
import beans.utilities.BannedUserBean;
import beans.utilities.ModeratorBean;
import beans.utilities.UserBean;
import ejb.BannedUserFacadeRemote;
import ejb.UserFacadeRemote;
import entity.BannedUser;
import entity.User;
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import utilities.EJBUtility;

public class UserAction extends Action {
    
    private final static String SUCCESS = "return";
    private UserFormBean bean;
    private HttpSession session;
    private UserFacadeRemote userRemote;
    private BannedUserFacadeRemote bannedUserRemote;
        
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        /* Get Remote Object */
        userRemote = (UserFacadeRemote) EJBUtility.lookup("UserFacade");
        bannedUserRemote = (BannedUserFacadeRemote) EJBUtility.lookup("BannedUserFacade");

        /* Get Bean */
        bean = (UserFormBean)form;        

        /* Get Session */
        session = request.getSession();

        /* Ban User */
        if (bean.getUser() != null) {
            banUser();
        }

        /* Revoke Ban */
        if (bean.getBanneduser() != null) {
            revokeBan();
        }

        /* Update Userbean */
        UserBean userbean = new UserBean();        
        session.setAttribute("UserBean", userbean);

        /* Update ModeratorBean */
        ModeratorBean moderatorbean = new ModeratorBean();
        session.setAttribute("ModeratorBean", moderatorbean);

        /* Update BannedUserBean */
        BannedUserBean bannedUserBean = new BannedUserBean();
        session.setAttribute("BannedUserBean", bannedUserBean);
        
        return mapping.findForward(SUCCESS);
    }

    private void banUser() throws NamingException {

        /* Get User EJB */
        User user = userRemote.findByUserName(bean.getUser());

        /* Create Banned User Object */
        BannedUser bannedUser = new BannedUser(user, new Date(), true);
        
        /* Persist to Database */
        bannedUserRemote.create(bannedUser);        
    }

    private void revokeBan() throws NamingException {

        /* Get User EJB */
        User user = userRemote.findByUserName(bean.getBanneduser());

        /* Get BannedUser Object */
        BannedUser bannedUser = bannedUserRemote.findByUserId(user.getUserId());

        /* Remove Record from Database */
        bannedUserRemote.remove(bannedUser);
    }
}
