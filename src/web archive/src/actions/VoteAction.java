package actions;

import beans.SearchResultBean;
import ejb.ActivityFacadeRemote;
import ejb.CategoryFacadeRemote;
import ejb.KeywordActivityFacadeRemote;
import ejb.KeywordURLFacadeRemote;
import ejb.URLDetailFacadeRemote;
import ejb.URLFacadeRemote;
import entity.Activity;
import entity.Category;
import entity.Channel;
import entity.KeywordActivity;
import entity.KeywordURL;
import entity.URL;
import entity.URLDetail;
import entity.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import utilities.EJBUtility;

public class VoteAction extends Action {

    private final static String KEY = "search";
    private String page;
    private String search;
    private String mod;
    private String category;
    private String visibleURL;
    private String timeframe;
    private String resultID;
    private Category categoryObject;
    private String redirectURL;
    private HttpSession session;
    private ServletContext servletContext;
    private URL url;
    private URLFacadeRemote UrlRemote;
    private ActivityFacadeRemote activityFacadeRemote;
    private User user;
    private String keywords;
    private KeywordURLFacadeRemote keywordURLRemote;
    private KeywordActivityFacadeRemote keywordActivityRemote;
    private String keywordID;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        /* Get Remote Object */
        UrlRemote = (URLFacadeRemote) EJBUtility.lookup("URLFacade");
        keywordURLRemote = (KeywordURLFacadeRemote) EJBUtility.lookup("KeywordURLFacade");
        activityFacadeRemote = (ActivityFacadeRemote) EJBUtility.lookup("ActivityFacade");
        keywordActivityRemote = (KeywordActivityFacadeRemote) EJBUtility.lookup("KeywordActivityFacade");

        /* Get Servlet Context */
        servletContext = servlet.getServletContext();

        /* Get Session */
        session = request.getSession();

        /* Get Current User */
        user = (User) session.getAttribute("userRecord");

        /* Get Parameters */
        page = request.getParameter("page");
        search = request.getParameter("search");
        mod = request.getParameter("mod");
        category = request.getParameter("category");
        timeframe = request.getParameter("timeframe");
        visibleURL = request.getParameter("channel");
        resultID = request.getParameter("ID");
        keywords = request.getParameter("keywords");
        keywordID = request.getParameter("keywordID");

        /* Check request: iDexter or Search */
        if (isiDexter()) {
            editiDexterResult();
        } else {

            /* Get Search ResultBean */
            SearchResultBean resultBean = getResultBean();

            /* Check result bean: Google or Dexter */
            if (resultBean != null) {
                createGoogleSearchResult(resultBean);
            } else {
                editDexterSearchResult();
            }
        }

        /* Restructure the URL */
        redirectURL = restructureURL();

        /* Redirect to URL */
        response.sendRedirect(redirectURL);

        return null;
    }

    private void createGoogleSearchResult(SearchResultBean resultBean) throws NamingException {

        /* Push Search ResultBean to Database */
        pushToDatabase(resultBean);

    }

    private void createUserVote(URL urlRecord, Activity activityRecord) {

        System.out.println("ACTIVITY RECORD IS NULL");
        if (mod.equals("up")) {
            urlRecord.setVoteUp(urlRecord.getVoteUp() + 1);
            activityFacadeRemote.create(new Activity(user, urlRecord, true, new Date(), true));
        } else {
            urlRecord.setVoteDown(urlRecord.getVoteDown() + 1);
            activityFacadeRemote.create(new Activity(user, urlRecord, false, new Date(), true));
        }
        /* Update Changes to Database */
        UrlRemote.edit(urlRecord);
    }

    private void editDexterSearchResult() throws NamingException {

        /* Get modded URL EJB */
        URL urlRecord = getURLEJB();

        /* Find ActivityRecord by User on Modded URL */
        Activity activityRecord = activityFacadeRemote.findByUserUrl(user, urlRecord);

        if (keywordID != null) {

            long id = Long.parseLong(keywordID);

            /* Get KeywordActivity Record */
            KeywordActivity keywordActivity = keywordActivityRemote.findbyUserKeywordURL(user, id);

            /* Get KeywordURL Record */
            KeywordURL keywordURL = keywordURLRemote.findbyURLandKeywordID(urlRecord, id);

            /* Modify Keyword Activity and Keyowrd Tables */
            modifyUserKeywordVote(keywordURL, keywordActivity);

            /* Modify Activity and URL Tables */
            if (activityRecord != null) {
                modifyUserVote(urlRecord, activityRecord);
            }
        } else {

            /* Create KeywordURL Object */
            KeywordURL keywordURL = new KeywordURL(urlRecord, keywords, getVoteUp(), getVoteDown(), true);

            /* Persist Keyword Records */
            persistKeywordURL(keywordURL);

            /* Modify URL EJB */
            if (activityRecord == null) {
                createUserVote(urlRecord, activityRecord);
            } else {
                modifyUserVoteWithKeyword(urlRecord, activityRecord);
            }

            /* Create Record in Keyword Activity Table */
            KeywordActivity keywordActivity = new KeywordActivity(findKeywordURL(keywordURL), user, new Date());
            if (mod.equalsIgnoreCase("up")) {
                keywordActivity.setIfVotedUp(true);
            } else {
                keywordActivity.setIfVotedUp(false);
            }
            keywordActivityRemote.create(keywordActivity);

        }
    }

    private void editiDexterResult() throws NamingException {

        /* Get modded URL EJB */
        URL urlRecord = getURLEJB();

        /* Find ActivityRecord by User on Modded URL */
        Activity activityRecord = activityFacadeRemote.findByUserUrl(user, urlRecord);

        /* Modify URL EJB */
        if (activityRecord == null) {
            createUserVote(urlRecord, activityRecord);
        } else {
            modifyUserVote(urlRecord, activityRecord);
        }
    }

    private KeywordURL findKeywordURL(KeywordURL keywordurl) {
        return keywordURLRemote.findbyURLandKeyword(keywordurl);
    }

    private Category getCategory() throws NamingException {

        /* Get Remote Object */
        CategoryFacadeRemote categoryRemote = (CategoryFacadeRemote) EJBUtility.lookup("CategoryFacade");

        /* Fetch RecordSet */
        List<Category> recordSet = categoryRemote.findAll();
        Iterator<Category> iterator = recordSet.iterator();

        /* Iterate RecordSet */
        while (iterator.hasNext()) {
            Category record = iterator.next();
            if (record.getCategoryName().equalsIgnoreCase(category)) {
                return (record);
            }
        }

        return null;
    }

    private Channel getChannelObject(String visibleUrl) {

        /* Get ArrayList of Channel Objects */
        ArrayList<Channel> channelList = (ArrayList<Channel>) servletContext.getAttribute("channel");

        /* Get Iterator */
        Iterator<Channel> iterator = channelList.iterator();

        /* Iterate ArrayList */
        while (iterator.hasNext()) {
            Channel channel = iterator.next();
            if (channel.getVisibleURL().equalsIgnoreCase(visibleUrl)) {
                return channel;
            }
        }

        /* The Voted URL does not have equivalent Channel */
        return null;
    }

    private SearchResultBean getResultBean() {

        /* Get ArrayList of ResultBeans */
        ArrayList<SearchResultBean> searchresults = (ArrayList<SearchResultBean>) servletContext.getAttribute(KEY);

        /* Iterate the ArrayList */
        Iterator<SearchResultBean> iterator = searchresults.iterator();
        while (iterator.hasNext()) {
            SearchResultBean bean = iterator.next();
            if (bean.getID().equals(resultID)) {
                return (bean);
            }
        }

        return null;
    }

    private URL getURLEJB() {

        try {
            return UrlRemote.find(Long.parseLong(resultID));
        } catch (NumberFormatException e) {
            System.out.println("Exception:" + e);
        }

        return null;
    }

    private long getVoteDown() {
        if (mod.equals("down")) {
            return 1;
        } else {
            return 0;
        }
    }

    private long getVoteUp() {
        if (mod.equals("up")) {
            return 1;
        } else {
            return 0;
        }
    }

    private boolean isiDexter() {
        if (search != null) {
            return false;
        } else {
            return true;
        }
    }

    private void modifyUserKeywordVote(KeywordURL keywordurlRecord, KeywordActivity activityRecord) {

        if (activityRecord.isIfVotedUp()) {
            if (mod.equals("down")) {
                keywordurlRecord.setVoteDown(keywordurlRecord.getVoteDown() + 1);
                keywordurlRecord.setVoteUp(keywordurlRecord.getVoteUp() - 1);
                activityRecord.setIfVotedUp(false);
                keywordActivityRemote.edit(activityRecord);
                keywordURLRemote.edit(keywordurlRecord);
            }

            if (mod.equals("up")) {
                keywordurlRecord.setVoteUp(keywordurlRecord.getVoteUp() - 1);
                keywordActivityRemote.remove(activityRecord);
                keywordURLRemote.edit(keywordurlRecord);
            }

        } else {
            if (mod.equals("up")) {
                keywordurlRecord.setVoteUp(keywordurlRecord.getVoteUp() + 1);
                keywordurlRecord.setVoteDown(keywordurlRecord.getVoteDown() - 1);
                activityRecord.setIfVotedUp(true);
                keywordActivityRemote.edit(activityRecord);
                keywordURLRemote.edit(keywordurlRecord);
            }

            if (mod.equals("down")) {
                keywordurlRecord.setVoteDown(keywordurlRecord.getVoteDown() - 1);
                keywordActivityRemote.remove(activityRecord);
                keywordURLRemote.edit(keywordurlRecord);
            }
        }

    }

    private void modifyUserVote(URL urlRecord, Activity activityRecord) {

        System.out.println("ACTIVITY RECORD IS NOT NULL");
        if (activityRecord.isIfVotedUp()) {
            if (mod.equals("down")) {
                urlRecord.setVoteDown(urlRecord.getVoteDown() + 1);
                urlRecord.setVoteUp(urlRecord.getVoteUp() - 1);
                activityRecord.setIfVotedUp(false);
                activityFacadeRemote.edit(activityRecord);
                UrlRemote.edit(urlRecord);
            }

            if (mod.equals("up")) {
                urlRecord.setVoteUp(urlRecord.getVoteUp() - 1);
                activityFacadeRemote.remove(activityRecord);
                UrlRemote.edit(urlRecord);
            }

        } else {
            if (mod.equals("up")) {
                urlRecord.setVoteUp(urlRecord.getVoteUp() + 1);
                urlRecord.setVoteDown(urlRecord.getVoteDown() - 1);
                activityRecord.setIfVotedUp(true);
                activityFacadeRemote.edit(activityRecord);
                UrlRemote.edit(urlRecord);
            }

            if (mod.equals("down")) {
                urlRecord.setVoteDown(urlRecord.getVoteDown() - 1);
                activityFacadeRemote.remove(activityRecord);
                UrlRemote.edit(urlRecord);
            }
        }
    }

    private void modifyUserVoteWithKeyword(URL urlRecord, Activity activityRecord) {

        if (activityRecord.isIfVotedUp()) {
            if (mod.equals("down")) {
                urlRecord.setVoteDown(urlRecord.getVoteDown() + 1);
                urlRecord.setVoteUp(urlRecord.getVoteUp() - 1);
                activityRecord.setIfVotedUp(false);
                activityFacadeRemote.edit(activityRecord);
                UrlRemote.edit(urlRecord);
            }
        } else {
            if (mod.equals("up")) {
                urlRecord.setVoteUp(urlRecord.getVoteUp() + 1);
                urlRecord.setVoteDown(urlRecord.getVoteDown() - 1);
                activityRecord.setIfVotedUp(true);
                activityFacadeRemote.edit(activityRecord);
                UrlRemote.edit(urlRecord);
            }
        }
    }

    private void populateEJB(SearchResultBean resultBean) throws NamingException {

        /* Get Current Date */
        Date sqlDate = new Date(System.currentTimeMillis());

        /* Get Vote */
        long voteUp = getVoteUp();
        long voteDown = getVoteDown();

        /* Get Category */
        categoryObject = getCategory();

        /* Create EJB */
        url = new URL(resultBean.getUnescapedUrl(), user, categoryObject, voteUp, voteDown, 0, sqlDate, true);

        /* Set Channel */
        url.setChannel(getChannelObject(resultBean.getVisibleUrl()));

        persistURL(url);
        url = findURL(url);

        /* Persist EJB in Keyword Table */
        KeywordURL keywordurl = new KeywordURL(url, keywords, voteUp, voteDown, true);
        persistKeywordURL(keywordurl);
        keywordurl = findKeywordURL(keywordurl);

        /* Create KeywordActivity Object */
        KeywordActivity keywordActivity = new KeywordActivity(keywordurl, user, sqlDate);

        /* Persist EJB in Activity Table */
        if (voteUp == 1) {
            persistActivity(user, url, true);
            keywordActivity.setIfVotedUp(true);
        } else {
            persistActivity(user, url, false);
            keywordActivity.setIfVotedUp(false);
        }

        /* Persist EJB in Keyword Activity Table */
        keywordActivityRemote.create(keywordActivity);
    }

    private void persistActivity(User user, URL url, boolean ifVotedUp) throws NamingException {

        activityFacadeRemote.create(new Activity(user, url, ifVotedUp, new Date(), true));
    }

    private void persistKeywordURL(KeywordURL keywordurl) throws NamingException {

        /* Check if record exists for Keyword */
        if (keywordURLRemote.ifExistKeywordURL(keywordurl)) {

            /* Get the Record */
            KeywordURL keywordURLRecord = keywordURLRemote.findbyURLandKeyword(keywordurl);

            /* Change the Votes */
            keywordURLRecord.setVoteDown(keywordURLRecord.getVoteDown() + keywordurl.getVoteDown());
            keywordURLRecord.setVoteUp(keywordURLRecord.getVoteUp() + keywordurl.getVoteUp());

            /* Update Database */
            keywordURLRemote.edit(keywordURLRecord);
        } else {

            /* Create New Record */
            keywordURLRemote.create(keywordurl);
        }



    }

    private URL findURL(URL url) throws NamingException {
        return UrlRemote.findByURL(url);
    }

    private void persistURL(URL url) throws NamingException {
        UrlRemote.create(url);
    }

    private void populateURLTables(SearchResultBean resultBean) throws NamingException {

        /* Get Remote Objects */
        URLDetailFacadeRemote urlDetailRemote = (URLDetailFacadeRemote) EJBUtility.lookup("URLDetailFacade");

        /* Create EJB */
        url = findURL(url);
        if (url == null) {
            System.err.println("NULL OBJECT");
        } else {
            System.err.println("id =" + url.getId());
        }
        URLDetail urlDetail = new URLDetail(url, true);

        /* Populate Optional Entries */
        urlDetail.setCacheURL(resultBean.getCacheUrl());
        urlDetail.setContent(resultBean.getContent());
        urlDetail.setTitle(resultBean.getTitle());
        urlDetail.setTitleNoFormating(resultBean.getTitleNoFormatting());
        urlDetail.setVisibleURL(resultBean.getVisibleUrl());

        /* Persist EJB to Database */
        urlDetailRemote.create(urlDetail);
    }

    private void pushToDatabase(SearchResultBean resultBean) throws NamingException {

        /* Populate URL and KeywordURL EJB */
        populateEJB(resultBean);

        /* Populate URL, URLDetail and KeywordURL Table */
        populateURLTables(resultBean);

    }
    /*copied it to Favorite Action by Rahul*/
    private String restructureURL() {

        String redirectUrl = "";

        /* iDexter or Search */
        if (search != null) {
            redirectUrl = "/Dexter/actions/search.do?search=" + search + "&page=" + page + "&category=" + category + "&channel=" + visibleURL + "&timeframe=" + timeframe;
        } else {
            redirectUrl = "/Dexter/actions/idexter.do?page=" + page + "&category=" + category + "&channel=" + visibleURL + "&timeframe=" + timeframe;
        }

        return redirectUrl;
    }
}
