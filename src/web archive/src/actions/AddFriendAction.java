package actions;

import beans.FriendsFormBean;
import ejb.FriendsFacadeRemote;
import ejb.UserFacadeRemote;
import entity.Friends;
import entity.User;
import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import utilities.EJBUtility;

public class AddFriendAction extends Action {

    private final static String YES = "yes";
    private final static String NO = "no";
    private final static String SUCCESS = "return";
    /* remote interfaces */
    private FriendsFacadeRemote friendsFacadeRemote;
    private UserFacadeRemote userFacadeRemote;
    private User user;
    private FriendsFormBean bean;
    private Friends friend;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        friendsFacadeRemote = (FriendsFacadeRemote) EJBUtility.lookup("FriendsFacade");
        userFacadeRemote = (UserFacadeRemote) EJBUtility.lookup("UserFacade");

        HttpSession session = request.getSession();
        user = (User) session.getAttribute("userRecord");

        /* Get Bean */
        bean = (FriendsFormBean) form;

        /* Get Parameters */
        String ifapproved = request.getParameter("approve");
        String requestID = request.getParameter("id");
        String page = request.getParameter("page");

        if (ifapproved != null) {

            friend=friendsFacadeRemote.find(Long.parseLong(requestID));
            
            if (page.equalsIgnoreCase("myfriends")) {

                actionOnRequest(ifapproved);
                response.sendRedirect("/Dexter/actions/myfriends.do");

            } else {

                actionOnRequest(ifapproved);
                response.sendRedirect("/Dexter/actions/friendsrequest.do");
            }

        } else {

            addNewFriend(response);
        }
        return mapping.findForward(SUCCESS);
    }

    private void actionOnRequest(String ifapproved) {

        if (ifapproved.equalsIgnoreCase(YES)) {

            friend.setIfFriend(true);
            friendsFacadeRemote.edit(friend);            
        } else {
                friendsFacadeRemote.remove(friend);            
        }
    }

    private void addNewFriend(HttpServletResponse response) throws IOException {

        User to = userFacadeRemote.findByUserName(bean.getUsername());
        
        if (to == null) {
            bean.setError(bean.getUsername() + " does not exist");
        } else 
        {
            if(user.getUserName().equals(to.getUserName()))
             {
                bean.setError("you can't add yourself as friend");
                return;
            }
            else
            {

                friend = friendsFacadeRemote.findByUsernameFromTo(user, to);
                if (friend != null) {
                    if (!friend.isIfFriend()) {
                        bean.setError("Friend request already sent");
                        return;
                    } else {
                        bean.setError(to.getUserName()+" is already a friend");
                    }
                }
                friend = friendsFacadeRemote.findByUsernameFromTo(to, user);
                if (friend != null) {

                    if (!friend.isIfFriend()) {
                        friend.setIfFriend(true);
                        friendsFacadeRemote.edit(friend);
                        bean.setSuccess(bean.getUsername() + " added as friend");
                        /*VB: is this line necessary*/
                        response.sendRedirect("/Dexter/actions/myfriends.do");
                    } else {
                        bean.setError(to.getUserName()+" is already a friend");
                    }

                } else
                {
                    friend = new Friends(user, to, new Date(), false, true);
                    friendsFacadeRemote.create(friend);
                    bean.setSuccess("Friend Request sent to " + bean.getUsername());
                }
            }

        }

    }
}