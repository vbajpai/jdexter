/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import ejb.FavoriteFacadeRemote;
import ejb.URLFacadeRemote;
import entity.Favorite;
import entity.URL;
import entity.User;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import utilities.EJBUtility;
import utilities.UserUtility;

/**
 *
 * @author rahul
 */
public class FavoriteAction extends org.apache.struts.action.Action {

    private String page;
    private String search;
    private String category;
    private String visibleURL;
    private String timeframe;
    private String urlid;
    private String redirectURL;
    private HttpSession session;
    private URL url;
    private URLFacadeRemote UrlRemote;
    private User user;
    private String tags;
    private FavoriteFacadeRemote favoriteFacadeRemote;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        /* Get Remote Object */
        UrlRemote = (URLFacadeRemote) EJBUtility.lookup("URLFacade");
        favoriteFacadeRemote = (FavoriteFacadeRemote) EJBUtility.lookup("FavoriteFacade");

        /* Get Session */
        session = request.getSession();

        /* Get Current User */
        user = (User) session.getAttribute("userRecord");

        /* Get Parameters */
        page = request.getParameter("page");
        search = request.getParameter("search");
        category = request.getParameter("category");
        timeframe = request.getParameter("timeframe");
        visibleURL = request.getParameter("channel");
        urlid = request.getParameter("ID");
        tags = request.getParameter("tags");

        /* Restructure the URL */
        redirectURL = restructureURL();

        if (urlid != null) {    /* url exists in the database */

            try {
                /*Get URL Object*/
                url = UrlRemote.find(Long.parseLong(urlid));
            } catch (NumberFormatException e) {
                System.err.println("URL ID IS WRONG");
            }
            /*Create Favorite*/
            if(favoriteFacadeRemote.findByUserUrl(user, url)==null)
                favoriteFacadeRemote.create(new Favorite(url, user, tags, true, new Date()));
            else
            {
                /*url already favorited by user */
                /*TODO: notify it to user */
            }

            redirectURL = restructureURL();


        } else {   /* url came directly from Google */
            if (UserUtility.userExists(session)) {  /* request came from a user */
                //TODO VB: notify user to submit the url.

            }
        /*handler for when request came from non-user is handled by LoginAction */
        }

        if (!redirectURL.equals("")) {
            response.sendRedirect(redirectURL);
        }


        

        return null;

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
