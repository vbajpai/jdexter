/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package actions.admin;

import beans.SearchResultBean;
import ejb.URLDetailFacadeRemote;
import ejb.URLFacadeRemote;
import entity.URL;
import entity.URLDetail;
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
public class URLReportAction extends org.apache.struts.action.Action {
    
    
    private final static String SUCCESS = "return";
    private URLFacadeRemote uRLFacadeRemote;
    private List<URL> reportedURLList;
    private HttpSession session;
    private URLDetailFacadeRemote uRLDetailFacadeRemote;
    private ArrayList<SearchResultBean> resultList;
    private String orderby;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        uRLFacadeRemote=(URLFacadeRemote) EJBUtility.lookup("URLFacade");
        uRLDetailFacadeRemote=(URLDetailFacadeRemote) EJBUtility.lookup("URLDetailFacade");
        resultList=new ArrayList<SearchResultBean>();
        /* Get Session */
        session = request.getSession();
        /* Get Request Parameters */
        orderby = request.getParameter("orderby");
        if(orderby.equalsIgnoreCase("submissiondate"))
        {
            reportedURLList=uRLFacadeRemote.findReportedURLOrderBySubmissionDate();
            findReportedURL();
        }
        if(orderby.equalsIgnoreCase("noofreports"))
        {
            reportedURLList=uRLFacadeRemote.findReportedURLOrderByNoOfReports();
            findReportedURL();
        }
        
        session.setAttribute("reportedurllist", resultList);
        return mapping.findForward(SUCCESS);
    }
    void findReportedURL(){
        
        reportedURLList=uRLFacadeRemote.findReportedURLOrderByNoOfReports();
        Iterator<URL> iterator=reportedURLList.iterator();
        SearchResultBean searchResultBean;
        URLDetail uRLDetail;
        /* Iterate ArrayList */
        while (iterator.hasNext()) {
            URL url=iterator.next();
            searchResultBean=new SearchResultBean();
            uRLDetail=uRLDetailFacadeRemote.findByURL(url);
            if(uRLDetail!=null)
            {
                searchResultBean.setCacheUrl(uRLDetail.getCacheURL());
                searchResultBean.setContent(uRLDetail.getContent());
                searchResultBean.setTitle(uRLDetail.getTitle());
                searchResultBean.setTitleNoFormatting(uRLDetail.getTitleNoFormating());
                searchResultBean.setVisibleUrl(uRLDetail.getVisibleURL());
            }
            searchResultBean.setCategory(url.getCategory().getCategoryName());
            if(url.getChannel()!=null)
                searchResultBean.setChannel(url.getChannel().getChannelName());
            searchResultBean.setSubmissiondate(DateUtility.getDaysPassed(url.getSubmissionDate()));
            searchResultBean.setUnescapedUrl(url.getUsescapedURL());
            searchResultBean.setUserFullName(url.getUser().getUserName());
            searchResultBean.setVoteDown(url.getVoteDown());
            searchResultBean.setVoteUp(url.getVoteUp());
            searchResultBean.setID(String.valueOf(url.getId()));
            resultList.add(searchResultBean);


        }
    }


}
