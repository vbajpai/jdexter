/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import beans.MoreInfoFormBean;
import beans.ShoutFormBean;
import ejb.ActivityFacadeRemote;
import ejb.BlockedShoutsFacadeRemote;
import ejb.ShoutsFacadeRemote;
import ejb.URLFacadeRemote;
import ejb.URLReportFacadeRemote;
import ejb.UserFacadeRemote;
import entity.Activity;
import entity.Shouts;
import entity.URL;
import entity.URLReport;
import entity.User;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import utilities.EJBUtility;

/**
 *
 * @author rahul
 */
public class MoreInfoAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private final static String SUCCESS = "return";
    private MoreInfoFormBean moreInfoFormBean;
    private URLReportFacadeRemote uRLReportFacadeRemote;
    private URLFacadeRemote uRLFacadeRemote;
    private ActivityFacadeRemote activityFacadeRemote;
    private HttpSession session;
    private String urlid;
    private URL url;
    private User user;
    private long longurlid;
    private UserFacadeRemote userFacadeRemote;
    private BlockedShoutsFacadeRemote blockedShoutsFacadeRemote;
    private ShoutsFacadeRemote shoutFacadeRemote;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        session = request.getSession();
        moreInfoFormBean = (MoreInfoFormBean) form;
        urlid = request.getParameter("urlid");
        longurlid = Long.parseLong(urlid);
        uRLReportFacadeRemote = (URLReportFacadeRemote) EJBUtility.lookup("URLReportFacade");
        uRLFacadeRemote = (URLFacadeRemote) EJBUtility.lookup("URLFacade");
        userFacadeRemote=(UserFacadeRemote) EJBUtility.lookup("UserFacade");
        blockedShoutsFacadeRemote= (BlockedShoutsFacadeRemote) EJBUtility.lookup("BlockedShoutsFacade");
        activityFacadeRemote= (ActivityFacadeRemote) EJBUtility.lookup("ActivityFacade");
        shoutFacadeRemote=(ShoutsFacadeRemote) EJBUtility.lookup("ShoutsFacade");
        url = uRLFacadeRemote.find(longurlid);
        user = (User) session.getAttribute("userRecord");
        if (moreInfoFormBean.getReport() != null) {
            reportedURLHandler();
            
        }else
        {
            moreInfoFormBean.setError(null);
            moreInfoFormBean.setSuccess(null);
        }
        if(moreInfoFormBean.getMessage()!=null){
            createShout();
        }else{
            moreInfoFormBean.setShoutError(null);
            moreInfoFormBean.setShoutSuccess(null);
        }
        saveWhoVotedItUp();
        response.sendRedirect("/Dexter/web/user/clear/moreinfo.jsp?urlid=" + request.getParameter("urlid"));
        return mapping.findForward(SUCCESS);
    }

    private void createShout() {
        User from = (User) session.getAttribute("userRecord");
        User to = userFacadeRemote.findByUserName(moreInfoFormBean.getUserName());
        if (to == null) {
            moreInfoFormBean.setShoutError(moreInfoFormBean.getUserName() + " doesn't exist");

        } else {
            if (blockedShoutsFacadeRemote.isShoutBlocked(from, to)) {
                moreInfoFormBean.setShoutError(to.getUserName() + " doesn't wish to recieve shouts from you");

            } else {
                if (moreInfoFormBean.getMessage().length()>4000) {
                       moreInfoFormBean.setShoutError("Please Keep the shout length less than 4000");
                }else{
                    
                    shoutFacadeRemote.create(new Shouts(from, to, moreInfoFormBean.getMessage(), new Date(), true));
                    moreInfoFormBean.setShoutSuccess("Shout Sent!");
                    moreInfoFormBean.setMessage(null);
                    moreInfoFormBean.setUserName(null);
                }
            }
        }
    }

    void saveWhoVotedItUp(){
        ArrayList<Activity> activityList=activityFacadeRemote.findWhoVotedUpURL(url);
        System.out.println(activityList.size()+"people voted it up");
        session.setAttribute("whovoteditup", activityList);
    }
    void reportedURLHandler() {
        /*Reported URL handled here*/

        if (moreInfoFormBean.getReport().length() < 4000) {

            if (uRLReportFacadeRemote.ifURLReportedByUser(user, url)==false) {
                moreInfoFormBean.setSuccess("URL Reported");
                uRLReportFacadeRemote.create(new URLReport(url, user, true, moreInfoFormBean.getReport()));
                moreInfoFormBean.setReport(null);

                url.setNoOfReports(url.getNoOfReports() + 1);
                uRLFacadeRemote.edit(url);                
            } else {
                moreInfoFormBean.setError("You have already reported the url");
            }
        } else {
            moreInfoFormBean.setError("Report too long!");
        }
        moreInfoFormBean.setReport(null);

    }
}
