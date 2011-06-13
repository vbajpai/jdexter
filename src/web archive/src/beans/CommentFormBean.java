/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entity.Comment;
import entity.URL;
import entity.URLDetail;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author rahul
 */
public class CommentFormBean extends org.apache.struts.action.ActionForm {
    private URL urlRecord;
    private URLDetail urlDetail;
    private ArrayList<Comment> commentList;
    private String comment;
    private String success;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public ArrayList<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(ArrayList<Comment> commentList) {
        this.commentList = commentList;
    }

    public URLDetail getUrlDetail() {
        return urlDetail;
    }

    public void setUrlDetail(URLDetail uRLDetail) {
        this.urlDetail = uRLDetail;
    }

    public URL getUrlRecord() {
        return urlRecord;
    }

    public void setUrlRecord(URL urlRecord) {
        this.urlRecord = urlRecord;
    }
    
}
