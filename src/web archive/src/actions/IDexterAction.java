package actions;

import beans.CategoryBean;
import beans.SearchResultBean;
import ejb.ActivityFacadeRemote;
import ejb.URLDetailFacadeRemote;
import entity.Activity;
import entity.Category;
import entity.Channel;
import entity.URL;
import entity.URLDetail;
import entity.User;
import java.util.ArrayList;
import java.util.Collections;
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
import utilities.DateUtility;
import utilities.EJBUtility;
import utilities.UserUtility;

public class IDexterAction extends Action {

    private final static String KEY = "idexter";
    private String page;
    private String timeframe;
    private String category;
    private String visibleURL;
    private HttpSession session;
    private ServletContext servletContext;
    ArrayList<SearchResultBean> resultList;
    private User user;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        /* Get Servlet Context */
        servletContext = servlet.getServletContext();

        /* Get Request Parameters */
        page = request.getParameter("page");
        timeframe = request.getParameter("timeframe");
        visibleURL = request.getParameter("channel");
        category = request.getParameter("category");

        /* Get Current Session */
        session = request.getSession();

        /* Get Current User */
        user = (User) session.getAttribute("userRecord");

        /* Get ArrayList of Submitted ResultBeans */
        resultList = getSubmittedResults();

        /* Sort ArrayList wrt. Votes */
        sortSubmittedResults();

        /* Save ArrayList in Servlet Context */
        servletContext.setAttribute(KEY, resultList);

        /* Get Redirect Flag */
        String redirectFlag = getredirectFlag();

        /* Return */
        return mapping.findForward(redirectFlag);
    }

    private void addRecord(URLDetail urlDetailRecord) throws NamingException {

        /* Create a Result Bean */
        SearchResultBean resultBean = new SearchResultBean();

        /* Populate Result Bean with Record */
        populateResultBean(urlDetailRecord, resultBean);

        /* Add Result Bean to ArrayList */
        resultList.add(resultBean);

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

    private boolean checkChannel(URLDetail record) {
        if (record.getVisibleURL().equalsIgnoreCase(visibleURL)) {
            return true;
        } else {
            return false;
        }
    }

    private ArrayList<CategoryBean> checkParentCategory() {

        /* Get Category Bean */
        ArrayList<CategoryBean> categoryList = (ArrayList<CategoryBean>) servletContext.getAttribute("category");

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

    private ArrayList<SearchResultBean> getSubmittedResults() throws NamingException {

        /* Create ArrayList */
        resultList = new ArrayList<SearchResultBean>();

        /* Get Remote Object */
        URLDetailFacadeRemote urlDetailRemote = (URLDetailFacadeRemote) EJBUtility.lookup("URLDetailFacade");

        /* Get Recordset */
        List<URLDetail> urlDetailRecordset = urlDetailRemote.findAll();

        /* Iterate Recordset */
        Iterator<URLDetail> iterator = urlDetailRecordset.iterator();
        while (iterator.hasNext()) {

            URLDetail urlDetailRecord = iterator.next();

            /* Check Filters */
            if (isFilterON()) {
                if (isFilterConditionSatisfied(urlDetailRecord)) {
                    addRecord(urlDetailRecord);
                }
            } else {
                addRecord(urlDetailRecord);
            }
        }

        /* Return ArrayList */
        return resultList;

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

    private void populateResultBean(URLDetail record, SearchResultBean bean) throws NamingException {

        /* Get Remote Objects */
        ActivityFacadeRemote activityRemote = (ActivityFacadeRemote) EJBUtility.lookup("ActivityFacade");

        /* Get URL Record */
        URL url = record.getUrl();

        /* Check if Current User has Modded the URL */
        if (user != null) {
            Activity activityRecord = activityRemote.findByUserUrl(user, url);

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
        bean.setVoteUp(url.getVoteUp());
        bean.setVoteDown(url.getVoteDown());
    }

    private void sortSubmittedResults() {
        Collections.sort(resultList);
    }

    private String getredirectFlag() {

        if (UserUtility.userExists(session)) {
            return ("usersuccess");
        } else {
            return ("nousersuccess");
        }
    }
}
