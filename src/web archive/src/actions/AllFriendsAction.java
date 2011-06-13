/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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

/**
 *
 * @author rahul
 */
public class AllFriendsAction extends Action {
    
    /* forward name="success" path="" */
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


        /* Save Friend Context */
        saveFriendContext();

        
        return mapping.findForward(SUCCESS);
    }
    private void saveFriendContext() {

        List<Friends> friendList = friendsRemote.findFriends(user);

        /* Save ArrayList in Session */
        session.setAttribute("friends", friendList);
    }
}
