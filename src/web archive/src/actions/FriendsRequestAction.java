package actions;

import ejb.FriendsFacadeRemote;
import entity.Friends;
import entity.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import utilities.EJBUtility;

public class FriendsRequestAction extends Action {    
    
    private final static String SUCCESS = "return";
    private FriendsFacadeRemote friendsRemote;
    private HttpSession session;
    private User user;
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        /* Get Remote Objects */
        friendsRemote = (FriendsFacadeRemote) EJBUtility.lookup("FriendsFacade");

        /* Get Session */
        session = request.getSession();

        /* Get User */
        user = (User) session.getAttribute("userRecord");

        /* Save Friend Request Context */
        saveFriendRequestContext();

        return mapping.findForward(SUCCESS);
    }

    private void saveFriendRequestContext() {

        /* Get ArrayList of FriendRequests from Database */
        List<Friends> friendList = friendsRemote.findFriendRequest(user);

        /* Save ArrayList in Session */
        session.setAttribute("friendRequest", friendList);
    }
    
}
