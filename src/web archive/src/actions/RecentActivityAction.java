/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package actions;

import beans.SearchResultBean;
import ejb.ActivityFacadeRemote;
import ejb.URLDetailFacadeRemote;
import entity.Activity;
import entity.URLDetail;
import entity.User;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import utilities.DateUtility;
import utilities.EJBUtility;

/**
 *
 * @author rahul
 */
public class RecentActivityAction extends org.apache.struts.action.Action {

    
    private final static String SUCCESS = "return";
    private HttpSession session;
    private User user;
    private ActivityFacadeRemote activityFacadeRemote;
    private URLDetailFacadeRemote uRLDetailFacadeRemote;
    private ArrayList<SearchResultBean> resultList;
    private ServletContext servletContext;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        /* Get Session */
        session = request.getSession();
        /* Get Servlet Context */
        servletContext = servlet.getServletContext();
        /* Get User */
        user = (User) session.getAttribute("userRecord");
        /*Get Remote Object*/
        activityFacadeRemote=(ActivityFacadeRemote) EJBUtility.lookup("ActivityFacade");
        uRLDetailFacadeRemote=(URLDetailFacadeRemote) EJBUtility.lookup("URLDetailFacade");
        saveRecentActivityContext();
        servletContext.setAttribute("RecentActivityBean", resultList);
        return mapping.findForward(SUCCESS);
    }

    private void saveRecentActivityContext() {
        ArrayList<Activity> activityList = activityFacadeRemote.findActivityVotedUpByUser(user);
        resultList = new ArrayList<SearchResultBean>();
        SearchResultBean searchResultBean;
        URLDetail uRLDetail;
        /* Get Iterator */
        Iterator<Activity> iterator = activityList.iterator();

        /* Iterate ArrayList */
        while (iterator.hasNext()) {
            Activity activity=iterator.next();
            searchResultBean=new SearchResultBean();
            uRLDetail=uRLDetailFacadeRemote.findByURL(activity.getUrl());
            if(uRLDetail!=null)
            {
                searchResultBean.setCacheUrl(uRLDetail.getCacheURL());
                searchResultBean.setContent(uRLDetail.getContent());
                searchResultBean.setTitle(uRLDetail.getTitle());
                searchResultBean.setTitleNoFormatting(uRLDetail.getTitleNoFormating());
                searchResultBean.setVisibleUrl(uRLDetail.getVisibleURL());
            }
            searchResultBean.setCategory(activity.getUrl().getCategory().getCategoryName());
            if(activity.getUrl().getChannel()!=null)
                searchResultBean.setChannel(activity.getUrl().getChannel().getChannelName());
            searchResultBean.setSubmissiondate(DateUtility.getDaysPassed(activity.getActivityTime()));
            searchResultBean.setUnescapedUrl(activity.getUrl().getUsescapedURL());
            searchResultBean.setUserFullName(activity.getUser().getFirstName()+activity.getUser().getLastName());
            searchResultBean.setVoteDown(activity.getUrl().getVoteDown());
            searchResultBean.setVoteUp(activity.getUrl().getVoteUp());
            searchResultBean.setID(String.valueOf(activity.getUrl().getId()));
            resultList.add(searchResultBean);
            

        }


    }
    
}
