/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package actions;

import beans.SearchResultBean;
import ejb.ActivityFacadeRemote;
import ejb.FriendsFacadeRemote;
import ejb.URLDetailFacadeRemote;
import entity.Activity;
import entity.Friends;
import entity.URLDetail;
import entity.User;
import java.util.ArrayList;
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

/**
 *
 * @author rahul
 */
public class FriendsActivityAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "return";
    private HttpSession session;
    private User user;
    private FriendsFacadeRemote friendsRemote;
    private ActivityFacadeRemote activityFacadeRemote;
    private List<Friends> friendList;
    private ArrayList<Activity> friendActivityList;
    private Friends friendRecord;
    private URLDetailFacadeRemote uRLDetailFacadeRemote;
    private ArrayList<SearchResultBean> resultList;
    private String timeframe;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        friendActivityList=new ArrayList<Activity>();
        /* Get Session */
        session = request.getSession();
        /*Get Parameter*/
        timeframe = request.getParameter("timeframe");
        /* Get Current User */
        user = (User) session.getAttribute("userRecord");
        /*Get Remote Object*/
        friendsRemote =(FriendsFacadeRemote) EJBUtility.lookup("FriendsFacade");
        activityFacadeRemote= (ActivityFacadeRemote) EJBUtility.lookup("ActivityFacade");
        uRLDetailFacadeRemote=(URLDetailFacadeRemote) EJBUtility.lookup("URLDetailFacade");
        saveFriendsRecentActivityContext();
        session.setAttribute("friendsactivity", resultList);
        
        return mapping.findForward(SUCCESS);
    }
    private void saveFriendsRecentActivityContext() {
        URLDetail uRLDetail;
        /*Get Friend List*/
        friendList = friendsRemote.findFriends(user);
        Iterator<Friends> friendIterator=friendList.iterator();
        while(friendIterator.hasNext()){
            friendRecord=friendIterator.next();
            if(friendRecord.getUserNameFrom().getUserId()==user.getUserId())
                
                friendActivityList.addAll(activityFacadeRemote.findActivityVotedUpByUser(friendRecord.getUserNameTo()));
            else
                friendActivityList.addAll(activityFacadeRemote.findActivityVotedUpByUser(friendRecord.getUserNameFrom()));
       }

        resultList = new ArrayList<SearchResultBean>();
        SearchResultBean searchResultBean;

        /* Get Iterator */
        Iterator<Activity> iterator = friendActivityList.iterator();

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
