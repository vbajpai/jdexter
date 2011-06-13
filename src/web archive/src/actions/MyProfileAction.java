package actions;

import beans.SearchResultBean;
import ejb.ActivityFacadeRemote;
import ejb.FavoriteFacadeRemote;
import ejb.ShoutsFacadeRemote;
import ejb.URLDetailFacadeRemote;
import entity.Activity;
import entity.Favorite;
import entity.Shouts;
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

public class MyProfileAction extends Action {
        
    private final static String SUCCESS = "return";
    private HttpSession session;
    private User user;
    private ShoutsFacadeRemote shoutsRemote;
    private FavoriteFacadeRemote favoriteFacadeRemote;
    private URLDetailFacadeRemote uRLDetailFacadeRemote;
    private ActivityFacadeRemote activityFacadeRemote;
    
 
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        /* Get Remote Objects */
        shoutsRemote = (ShoutsFacadeRemote) EJBUtility.lookup("ShoutsFacade");
        favoriteFacadeRemote=(FavoriteFacadeRemote) EJBUtility.lookup("FavoriteFacade");
        uRLDetailFacadeRemote=(URLDetailFacadeRemote) EJBUtility.lookup("URLDetailFacade");
        activityFacadeRemote=(ActivityFacadeRemote) EJBUtility.lookup("ActivityFacade");

        /* Get Session */
        session = request.getSession();

        /* Get User */
        user = (User) session.getAttribute("userRecord");

        /* Save Shout Context */
       saveShoutContext();
       /*Save Favorite Context*/
       saveFavoriteContext();
       /*Save Recent Activity Context*/
       saveRecentActivityContext();

        return mapping.findForward(SUCCESS);
    }

    private void saveShoutContext() {
        
        /* Get ArrayList of Shouts from Database */
        List<Shouts> shoutsList = shoutsRemote.findBySentTo(user);

        /* Set Date String */
        setDateString(shoutsList);

        /* Save ArrayList in Session */
        session.setAttribute("shouts", shoutsList);
    }
    private void saveFavoriteContext() {
        ArrayList<Favorite> favoriteList = favoriteFacadeRemote.findByUser(user);
        ArrayList<SearchResultBean> resultList;
        resultList = new ArrayList<SearchResultBean>();
        SearchResultBean searchResultBean;
        URLDetail uRLDetail;
        /* Get Iterator */
        Iterator<Favorite> iterator = favoriteList.iterator();

        /* Iterate ArrayList */
        while (iterator.hasNext()) {
            Favorite favorite=iterator.next();
            searchResultBean=new SearchResultBean();
            uRLDetail=uRLDetailFacadeRemote.findByURL(favorite.getUrl());
            if(uRLDetail!=null)
            {
                searchResultBean.setCacheUrl(uRLDetail.getCacheURL());
                searchResultBean.setContent(uRLDetail.getContent());
                searchResultBean.setTitle(uRLDetail.getTitle());
                searchResultBean.setTitleNoFormatting(uRLDetail.getTitleNoFormating());
                searchResultBean.setVisibleUrl(uRLDetail.getVisibleURL());
            }
            searchResultBean.setCategory(favorite.getUrl().getCategory().getCategoryName());
            if(favorite.getUrl().getChannel()!=null)
                searchResultBean.setChannel(favorite.getUrl().getChannel().getChannelName());
            searchResultBean.setSubmissiondate(DateUtility.getDaysPassed(favorite.getFavoritedAtTime()));
            searchResultBean.setUnescapedUrl(favorite.getUrl().getUsescapedURL());
            searchResultBean.setUserFullName(favorite.getUser().getFirstName()+favorite.getUser().getLastName());
            searchResultBean.setVoteDown(favorite.getUrl().getVoteDown());
            searchResultBean.setVoteUp(favorite.getUrl().getVoteUp());
            searchResultBean.setID(String.valueOf(favorite.getUrl().getId()));
            searchResultBean.setTags(favorite.getLabel());

            resultList.add(searchResultBean);


        }
        session.setAttribute("FavoriteBean", resultList);
    }
    private void saveRecentActivityContext() {

        ArrayList<Activity> activityList = activityFacadeRemote.findActivityVotedUpByUser(user);
        ArrayList<SearchResultBean> resultList = new ArrayList<SearchResultBean>();
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

        session.setAttribute("RecentActivityBean", resultList);
    }

    private void setDateString(List<Shouts> shoutsList) {

        /* Get Iterator */
        Iterator<Shouts> iterator = shoutsList.iterator();

        /* Iterated ArrayList */
        while(iterator.hasNext()){
            Shouts record = iterator.next();
            record.setShoutTimeString(DateUtility.getDaysPassed(record.getShoutTime()));
        }
    }
}
