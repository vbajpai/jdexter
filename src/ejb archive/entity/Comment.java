/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author rahul
 */
@Entity(name="COMMENT_TABLE")
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="COMMENT_ID")    
    @Basic(optional=false)
    private long id;

    @JoinColumn(name = "URL_ID", referencedColumnName = "URL_ID",nullable=false)
    @ManyToOne
    private URL url;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID",nullable=false)
    @ManyToOne
    private User user;
    @JoinColumn(name = "PARENT_COMMENT_ID", referencedColumnName = "COMMENT_ID")
    @ManyToOne
    private Comment parentComment;

    @Column(name="COMMENT_MADE",nullable=false,length=4000)
    private String comment;
     @Column(name="VOTE_UP",nullable=false)
    private long voteUp;
    @Column(name="VOTE_DOWN",nullable=false)
    private long voteDown;
       @Column(name="NO_OF_REPORTS",nullable=false)
    private long noOfReports;
    @Column(name = "SUBMISSION_DATE",nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date submissionDate;

     @Column(name = "IF_VALID",nullable=false)
    private boolean ifValid;

    protected Comment() {
    }

    public Comment(URL url, User user, String comment, long voteUp, long voteDown, long noOfReports, Date submissionDate, boolean ifValid) {
        this.url = url;
        this.user = user;
        this.comment = comment;
        this.voteUp = voteUp;
        this.voteDown = voteDown;
        this.noOfReports = noOfReports;
        this.submissionDate = submissionDate;
        this.ifValid = ifValid;
    }



    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isIfValid() {
        return ifValid;
    }

    public void setIfValid(boolean ifValid) {
        this.ifValid = ifValid;
    }

    public long getNoOfReports() {
        return noOfReports;
    }

    public void setNoOfReports(long noOfReports) {
        this.noOfReports = noOfReports;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getVoteDown() {
        return voteDown;
    }

    public void setVoteDown(long voteDown) {
        this.voteDown = voteDown;
    }

    public long getVoteUp() {
        return voteUp;
    }

    public void setVoteUp(long voteUp) {
        this.voteUp = voteUp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Comment other = (Comment) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }
    


    @Override
    public String toString() {
        return "entity.Comment[id=" + id + "]";
    }

}
