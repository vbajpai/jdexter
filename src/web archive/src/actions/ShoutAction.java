package actions;

import beans.ShoutFormBean;
import ejb.BlockedShoutsFacadeRemote;
import ejb.ShoutsFacadeRemote;
import ejb.UserFacadeRemote;
import entity.BlockedShouts;
import entity.Shouts;
import entity.User;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import utilities.DateUtility;
import utilities.EJBUtility;

public class ShoutAction extends Action {

    private final static String SENDSHOUT = "sendshout";
    private final static String SEESHOUTS = "seeshouts";
    private final static String DELETE = "delete";
    private final static String BLOCK = "block";
    private final static String UNBLOCK ="unblock";

    private ShoutFormBean shoutBean;
    private String message;
    private String userName;
    private ShoutsFacadeRemote shoutFacadeRemote;
    private UserFacadeRemote userFacadeRemote;
    private BlockedShoutsFacadeRemote blockedShoutsFacadeRemote;
    private Shouts shout;
    private User from;
    private User to;
    private String id;
    private HttpSession session;
    private String editshout;
    private User user;
    private String urlid;
    private String urlString;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        /* Get Bean */
        shoutBean = (ShoutFormBean) form;

        /* Get Parameters */
        urlid=request.getParameter("urlid");
        id = request.getParameter("id");
        editshout = request.getParameter("editshout");
        userName = shoutBean.getUserName();
        message = shoutBean.getMessage();

        /* Get Remote Objects */
        shoutFacadeRemote = (ShoutsFacadeRemote) EJBUtility.lookup("ShoutsFacade");
        userFacadeRemote = (UserFacadeRemote) EJBUtility.lookup("UserFacade");
        blockedShoutsFacadeRemote = (BlockedShoutsFacadeRemote) EJBUtility.lookup("BlockedShoutsFacade");

        /* Get Session */
        session = request.getSession();

        /* Get User */
        user = (User) session.getAttribute("userRecord");

        if (id != null)
        {
            /* Edit Shout */
            editShout();
            response.sendRedirect("/Dexter/actions/shout.do");
        } 
        else
        {
            if (userName!=null && message!=null)
            {

                /* Create Shout */
                createShout();
                return mapping.findForward(SENDSHOUT);

            }
            else
            {
                shoutBean.setError(null);
                shoutBean.setSuccess(null);
            }
           /* Refresh Shout Bean */
           saveShoutContext();
            return mapping.findForward(SEESHOUTS);
        }
        
        return null;
    }

    private void createShout() {
        
        from = (User) session.getAttribute("userRecord");

        to = userFacadeRemote.findByUserName(userName);
        if (to == null) {
            shoutBean.setError(userName + " doesn't exist");

        } else {
            if (blockedShoutsFacadeRemote.isShoutBlocked(from, to)) {
                shoutBean.setError(userName + " doesn't wish to recieve shouts from you");

            } else {
                if (message.length()>4000) {
                       shoutBean.setError("Please Keep the shout length less than 4000");
                }else{
                    shout = new Shouts(from, to, message, new Date(), true);
                    shoutFacadeRemote.create(shout);
                    shoutBean.setSuccess("Shout Sent!");
                    shoutBean.setMessage(null);
                    shoutBean.setUserName(null);
                }
            }
        }
    }

    private void editShout() {

        if(editshout.equalsIgnoreCase(DELETE)){
            try{
                shoutFacadeRemote.remove(shoutFacadeRemote.find(Long.parseLong(id)));
                shoutBean.setSuccess("Message succesfully deleted");
            }catch(NumberFormatException e)
            {
                System.err.println("ID PROVIDED WAS WRONG. ID PROVIDED WAS "+id);
            }
            
        }else if(editshout.equalsIgnoreCase(BLOCK)){
            Shouts shouts =shoutFacadeRemote.find(Long.parseLong(id));
            
            blockedShoutsFacadeRemote.create(new BlockedShouts(shouts.getUserFrom(), shouts.getUserTo(),true));
            shoutBean.setSuccess("User Blocked");
        }else if(editshout.equalsIgnoreCase(UNBLOCK)){
            Shouts shouts =shoutFacadeRemote.find(Long.parseLong(id));
            BlockedShouts blockedShouts=blockedShoutsFacadeRemote.findBlockedShoutsByFromTo(shouts.getUserFrom(), shouts.getUserTo());
            if(blockedShouts!=null)
                blockedShoutsFacadeRemote.remove(blockedShouts);
        }
    }

    private void saveShoutContext() {

        /* Get ArrayList of Shouts from Database */
        List<Shouts> shoutsList = shoutFacadeRemote.findBySentTo(user);

        /* Set Date String */
        setDateString(shoutsList);

        /* Save ArrayList in Session */
        session.setAttribute("shouts", shoutsList);
    }

    private void setDateString(List<Shouts> shoutsList) {
        /*Edited by rahul now it checks if user who sent the shouts are blocked or not*/
        /* Get Iterator */
        Iterator<Shouts> iterator = shoutsList.iterator();

        /* Iterated ArrayList */
        while(iterator.hasNext()){
            Shouts record = iterator.next();
            record.setShoutTimeString(DateUtility.getDaysPassed(record.getShoutTime()));

            record.setIfBlocked(blockedShoutsFacadeRemote.isShoutBlocked(record.getUserFrom(), record.getUserTo()));
        }
    }


}
