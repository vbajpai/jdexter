package actions;

import beans.CommentFormBean;
import ejb.CommentFacadeRemote;
import ejb.URLDetailFacadeRemote;
import ejb.URLFacadeRemote;
import entity.Comment;
import entity.URL;
import entity.URLDetail;
import entity.User;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import utilities.EJBUtility;
import utilities.UserUtility;

public class UrlAction extends Action {

    private final static String SUCCESS = "success";
    private URLFacadeRemote uRLFacadeRemote;
    private URLDetailFacadeRemote uRLDetailFacadeRemote;
    private CommentFacadeRemote commentFacadeRemote;
    private URL urlRecord;
    private URLDetail uRLDetail;
    private ArrayList<Comment> commentList;
    private CommentFormBean commentBean;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession();

        /* check if url is submitted, comments page would only open once URL is submitted */
        String urlid = request.getParameter("urlid");
        String url = "";

        /*Remote Object*/
        uRLDetailFacadeRemote = (URLDetailFacadeRemote) EJBUtility.lookup("URLDetailFacade");
        uRLFacadeRemote = (URLFacadeRemote) EJBUtility.lookup("URLFacade");
        commentFacadeRemote = (CommentFacadeRemote) EJBUtility.lookup("CommentFacade");

        commentBean = (CommentFormBean) form;
        if(commentBean.getComment()==null)
        {
            commentBean.setError(null);
            commentBean.setSuccess(null);
        }
        if (urlid != null) {    /* url exists in the database */

            try {
                urlRecord = uRLFacadeRemote.find(Long.parseLong(urlid));
            } catch (NumberFormatException e) {
                System.err.println("URL ID IS WRONG");
            }
            /*Fetching of Records*/
            uRLDetail = uRLDetailFacadeRemote.findByURL(urlRecord);

            if (commentBean.getComment() != null) {
                if (commentBean.getComment().length() > 4000) {
                    commentBean.setError("Comment too long!!!!");
                } else {
                    User user = (User) session.getAttribute("userRecord");
                    commentFacadeRemote.create(new Comment(urlRecord, user, commentBean.getComment(), 1, 0, 0, new Date(), true));
                    commentBean.setComment(null);
                    commentBean.setSuccess("Comment Posted");
                }
            }
            commentList = commentFacadeRemote.findByUrl(urlRecord);

            /*filling Form bean*/
            commentBean.setCommentList(commentList);
            commentBean.setUrlDetail(uRLDetail);
            commentBean.setUrlRecord(urlRecord);
            session.setAttribute("commentlist", commentBean);
            if (UserUtility.userExists(session)) {  /* request came from a user */
                url = "/Dexter/web/user/clear/comments.jsp?urlid=" + urlid;
            } else { /* request came from non-user */
                url = "/Dexter/web/guest/clear/comments.jsp?urlid=" + urlid;
            }
        } else {   /* url came directly from Google */
            if (UserUtility.userExists(session)) {  /* request came from a user */
                //TODO VB: notify user to submit the url.

            }
        /*handler for when request came from non-user is handled by LoginAction */
        }

        if (!url.equals("")) {

            response.sendRedirect(url);
        }

        return null;
    }
}
