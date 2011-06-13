/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package actions;

import beans.SearchResultBean;
import ejb.FavoriteFacadeRemote;
import ejb.URLDetailFacadeRemote;
import entity.Favorite;
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
public class DisplayFavoriteAction extends org.apache.struts.action.Action {
    
    private final static String SUCCESS = "return";
    private HttpSession session;
    private User user;
    private FavoriteFacadeRemote favoriteFacadeRemote;
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
        favoriteFacadeRemote=(FavoriteFacadeRemote) EJBUtility.lookup("FavoriteFacade");
        uRLDetailFacadeRemote=(URLDetailFacadeRemote) EJBUtility.lookup("URLDetailFacade");
        saveFavoriteContext();
        
        return mapping.findForward(SUCCESS);
        
    }
     private void saveFavoriteContext() {
        ArrayList<Favorite> favoriteList = favoriteFacadeRemote.findByUser(user);
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
            resultList.add(searchResultBean);


        }
        servletContext.setAttribute("FavoriteBean", resultList);


    }

}
