/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author rahul
 */
@Entity(name="COMMENT_REPORT_TABLE")
public class CommentReport implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional=false)
    @Column(name="COMMENT_REPORT_ID")
    private long id;
    @JoinColumn(name = "REPORTED_COMMENT_ID", referencedColumnName = "COMMENT_ID",nullable=false)
    @ManyToOne
    private Comment reportedComment;
    @JoinColumn(name = "REPORTED_BY", referencedColumnName = "USER_ID",nullable=false)
    @ManyToOne
    private User reportedBy;
    @Column(name = "IF_VALID",nullable=false)
    private Boolean ifValid;

    protected CommentReport() {
    }

    public CommentReport(Comment reportedComment, User reportedBy, Boolean ifValid) {
        this.reportedComment = reportedComment;
        this.reportedBy = reportedBy;
        this.ifValid = ifValid;
    }



    public Boolean getIfValid() {
        return ifValid;
    }

    public void setIfValid(Boolean ifValid) {
        this.ifValid = ifValid;
    }

    public User getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(User reportedBy) {
        this.reportedBy = reportedBy;
    }

    public Comment getReportedComment() {
        return reportedComment;
    }

    public void setReportedComment(Comment reportedComment) {
        this.reportedComment = reportedComment;
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
        final CommentReport other = (CommentReport) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    

    @Override
    public String toString() {
        return "entity.CommentReport[id=" + id + "]";
    }

}
