package actions;

import beans.CategoryBean;
import beans.SearchResultBean;
import ejb.ActivityFacadeRemote;
import ejb.KeywordActivityFacadeRemote;
import ejb.KeywordURLFacadeRemote;
import ejb.URLDetailFacadeRemote;
import entity.Activity;
import entity.Category;
import entity.Channel;
import entity.KeywordActivity;
import entity.KeywordURL;
import entity.URL;
import entity.URLDetail;
import entity.User;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import org.json.JSONException;
import utilities.DateUtility;
import utilities.EJBUtility;
import utilities.SearchGoogle;
import utilities.UserUtility;

public class SearchAction extends Action {

    private final static String USERFAILURE = "userfailure";
    private final static String USERSUCCESS = "usersuccess";
    private final static String NOUSERFAILURE = "nouserfailure";
    private final static String NOUSERSUCCESS = "nousersuccess";
    private final static String GOOGLEKEY = "search";
    private final static String DEXTERKEY = "searchdexter";
    private HttpSession session;
    private int page;
    private String escapedstring;
    private ServletContext context;
    private ArrayList<SearchResultBean> resultList;
    private User user;
    private String category;
    private String timeframe;
    private String visibleURL;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        /* Get Session */
        session = request.getSession();

        /* Get Current User */
        user = (User) session.getAttribute("userRecord");

        /* Get ServletContext */
        context = servlet.getServletContext();

        /* Get Parameters */
        escapedstring = request.getParameter("search");
        page = Integer.parseInt(request.getParameter("page"));
        timeframe = request.getParameter("timeframe");
        visibleURL = request.getParameter("channel");
        category = request.getParameter("category");


        if (!isSearchStringEmpty()) {

            /* Fetch Dexter Results based on Keyword */
            fetchDexterResults();

            /* Fetch Google Results */
            fetchGoogleResults(request);

        }

        String forwardFlag = getForwardFlag();

        return mapping.findForward(forwardFlag);
    }

    private void fetchDexterResults() throws NamingException {

        /* Create ArrayList */
        resultList = new ArrayList<SearchResultBean>();

        /* Get Remote Object */
        KeywordURLFacadeRemote keywordRemote = (KeywordURLFacadeRemote) EJBUtility.lookup("KeywordURLFacade");
        URLDetailFacadeRemote urldetaileRemote = (URLDetailFacadeRemote) EJBUtility.lookup("URLDetailFacade");

        /* Get Submitted Results with Matching Keywords*/
        ArrayList<KeywordURL> keywordresultList = keywordRemote.getListwithKeyword(escapedstring);

        /* Iterator ArrayList */
        Iterator<KeywordURL> iterator = keywordresultList.iterator();
        while (iterator.hasNext()) {
            KeywordURL keywordRecord = iterator.next();
            URL url = keywordRecord.getUrl();
            URLDetail urlDetailRecord = urldetaileRemote.findByURL(url);

            /* Check Filters */
            if (isFilterON()) {
                if (isFilterConditionSatisfied(urlDetailRecord)) {
                    addRecord(urlDetailRecord, keywordRecord);
                }
            } else {
                addRecord(urlDetailRecord, keywordRecord);
            }
        }

        /* Sort ArrayList wrt. Votes */
        sortSubmittedResults();

        /* Save ArrayList in Servlet Context */
        context.setAttribute(DEXTERKEY, resultList);

    }

    private void sortSubmittedResults() {
        Collections.sort(resultList);
    }

    private void addRecord(URLDetail urlDetailRecord, KeywordURL keywordRecord) throws NamingException {

        /* Create a Result Bean */
        SearchResultBean resultBean = new SearchResultBean();

        /* Populate Result Bean with Record */
        populateResultBean(urlDetailRecord, keywordRecord, resultBean);

        /* Add Result Bean to ArrayList */
        resultList.add(resultBean);

    }

    private void populateResultBean(URLDetail record, KeywordURL keywordRecord, SearchResultBean bean) throws NamingException {

        /* Get Remote Objects */
        KeywordActivityFacadeRemote keywordActivityRemote = (KeywordActivityFacadeRemote) EJBUtility.lookup("KeywordActivityFacade");

        /* Get URL Record */
        URL url = record.getUrl();

        /* Check if Current User has Modded the URL */
        if (user != null) {
            
            KeywordActivity activityRecord = keywordActivityRemote.findbyUserKeywordURL(user,keywordRecord);

            /* Populate Activity Parameters */
            if (activityRecord != null) {

                bean.setIfmodded(true);

                /* Check Vote */
                if (activityRecord.isIfVotedUp()) {
                    bean.setIfvotedup(true);
                }
            }
        }

        /* Populate Bean */
        bean.setCacheUrl(record.getCacheURL());
        bean.setContent(record.getContent());
        bean.setID(getURLID(url.getId()));
        bean.setTitle(record.getTitle());
        bean.setTitleNoFormatting(record.getTitleNoFormating());
        bean.setVisibleUrl(record.getVisibleURL());
        bean.setUnescapedUrl(url.getUsescapedURL());

        /* Populate iDexter Specific Parameters */
        bean.setCategory(url.getCategory().getCategoryName());
        bean.setChannel(getChannelName(url));
        bean.setNumberofreports(url.getNoOfReports());
        bean.setSubmissiondate(DateUtility.getDaysPassed(url.getSubmissionDate()));
        bean.setUserFullName(getUserName(url.getUser()));
        bean.setVoteUp(keywordRecord.getVoteUp());
        bean.setVoteDown(keywordRecord.getVoteDown());

        /* Keyword Parameters */
        bean.setKeywordurlid(keywordRecord.getId());
        
    }

    private String getChannelName(URL url) {

        String channelName;

        Channel channel = url.getChannel();
        if (channel != null) {
            channelName = channel.getChannelName();
        } else {
            channelName = null;
        }

        return channelName;
    }

    private String getURLID(long id) {
        Long iD = new Long(id);
        return (iD.toString());
    }

    private String getUserName(User user) {

        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String fullName = firstName + " " + lastName;
        return (fullName);
    }

    private void fetchGoogleResults(HttpServletRequest request) throws UnsupportedEncodingException, IOException, JSONException {

        /* URLEncode the search Parameter */
        String searchstring = URLEncoder.encode(escapedstring, "UTF-8");

        /* Get Current Referrer */
        StringBuffer referrer = request.getRequestURL();

        /* Fetch Results from Google */
        SearchGoogle searchgoogle = new SearchGoogle();
        ArrayList<SearchResultBean> searchresults = null;

        if (visibleURL != null) {
            if (!visibleURL.equalsIgnoreCase("")) {
                searchresults = searchgoogle.getArraylist(searchstring, page, visibleURL, referrer);
            }else{
                searchresults = searchgoogle.getArraylist(searchstring, page, referrer);
            }
        } else {
            searchresults = searchgoogle.getArraylist(searchstring, page, referrer);
        }

        /* Save the results in servletcontext */
        context.setAttribute(GOOGLEKEY, searchresults);
    }

    private boolean isFilterConditionSatisfied(URLDetail record) {

        boolean flag = false;

        if (!timeframe.equals("")) {
            flag = checkTimeFrame(record);
            if (!flag) {
                return flag;
            }

        }
        if (!visibleURL.equals("")) {
            flag = checkChannel(record);
            if (!flag) {
                return flag;
            }

        }
        if (!category.equals("")) {
            flag = checkCategory(record);
        }

        return (flag);
    }

    private boolean checkTimeFrame(URLDetail record) {

        /* Get Days Passed Since Submission */
        String daysPassed = DateUtility.getDaysPassed(record.getUrl().getSubmissionDate());
        int days = Integer.parseInt(daysPassed);

        /* Check Timeframe */
        if (timeframe.equalsIgnoreCase("today")) {
            if (daysPassed.equalsIgnoreCase("0")) {
                return (true);
            } else {
                return (false);
            }

        }

        if (timeframe.equalsIgnoreCase("week")) {
            if (days <= 7) {
                return (true);
            } else {
                return (false);
            }

        }

        if (timeframe.equalsIgnoreCase("month")) {
            if (days <= 30) {
                return (true);
            } else {
                return (false);
            }

        }

        if (timeframe.equalsIgnoreCase("year")) {
            if (days <= 365) {
                return (true);
            } else {
                return (false);
            }

        }
        return false;
    }

    private boolean checkCategory(URLDetail record) {

        /* Get Record Category Name */
        Category recordCategory = record.getUrl().getCategory();

        /* Check for Parent Category Filter */
        ArrayList<CategoryBean> childCategoryList = checkParentCategory();

        if (childCategoryList != null) {

            if (recordCategory.getParentCategory().getCategoryName().equalsIgnoreCase(category)) {
                return true;
            } else {
                return false;
            }

        } else {

            if (recordCategory.getCategoryName().equalsIgnoreCase(category)) {
                return true;
            } else {
                return false;
            }

        }
    }

    private ArrayList<CategoryBean> checkParentCategory() {

        /* Get Category Bean */
        ArrayList<CategoryBean> categoryList = (ArrayList<CategoryBean>) context.getAttribute("category");

        /* Get Iterator */
        Iterator<CategoryBean> iterator = categoryList.iterator();

        /* Iterate ArrayList */
        while (iterator.hasNext()) {
            CategoryBean parentCategoryBean = iterator.next();
            if (parentCategoryBean.getCategoryname().equalsIgnoreCase(category)) {
                return (parentCategoryBean.getChild());
            }

        }

        /* Category Filter Selected is Not Parent Category */
        return (null);
    }

    private boolean checkChannel(URLDetail record) {
        if (record.getVisibleURL().equalsIgnoreCase(visibleURL)) {
            return true;
        } else {
            return false;
        }

    }

    private boolean isFilterON() {
        if (timeframe == null && visibleURL == null && category == null) {
            return false;
        } else {
            if (timeframe.equals("") && visibleURL.equals("") && category.equals("")) {
                return false;
            }
            return true;
        }

    }

    private String getForwardFlag() {

        if (!UserUtility.userExists(session)) {
            return NOUSERSUCCESS;
        } else {
            return USERSUCCESS;
        }

    }

    private boolean isSearchStringEmpty() {
        return (escapedstring.trim().equals(""));
    }
}
