package beans;

import org.apache.struts.action.ActionForm;

public class PreferenceFormBean extends ActionForm {

    private int commentPageSize;
    private int commentReplyCollapsed;
    private int commentThreshold;
    private int commentSort;
    private String success;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }


    public int getCommentSort() {
        return commentSort;
    }

    public void setCommentSort(int commentSort) {
            this.commentSort = commentSort;
    }

    public int getCommentThreshold() {
        return commentThreshold;
    }

    public void setCommentThreshold(int commentThreshold) {
        this.commentThreshold = commentThreshold;
    }

    public int getCommentReplyCollapsed() {
        return commentReplyCollapsed;
    }

    public void setCommentReplyCollapsed(int commentReplyCollapsed) {
        this.commentReplyCollapsed = commentReplyCollapsed;
    }

    public int getCommentPageSize() {
        return commentPageSize;
    }

    public void setCommentPageSize(int commentPageSize) {
        this.commentPageSize = commentPageSize;
    }

  
}
