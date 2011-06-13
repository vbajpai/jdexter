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
@Entity(name="USER_PREFERENCE_TABLE")
public class UserPreference implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name="USER_PREFERENCE_ID")
    private long id;
    
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID",nullable=false)
    @ManyToOne
    private User user;
    @Column(name = "DEFAULT_COMMENT_SORT",nullable=false)
    private boolean defaultCommentSort;
    @Column(name = "COMMENT_THRESHOLD",nullable=false)
    private int commentThreshold;
    @Column(name = "COMMENT_PAGE_SIZE",nullable=false)
    private int commentPageSize;
    @Column(name = "COMMENT_REPLY_COLLAPSED",nullable=false)
    private boolean commentReplyCollapsed;
    @Column(name = "IF_VALID",nullable=false)
    private boolean ifValid;

    public UserPreference(User user, boolean defaultCommentSort, int commentThreshold, int commentPageSize, boolean commentReplyCollapsed, boolean ifValid) {
        this.user = user;
        this.defaultCommentSort = defaultCommentSort;
        this.commentThreshold = commentThreshold;
        this.commentPageSize = commentPageSize;
        this.commentReplyCollapsed = commentReplyCollapsed;
        this.ifValid = ifValid;
    }

    protected UserPreference() {
    }
    
    public int getCommentPageSize() {
        return commentPageSize;
    }

    public void setCommentPageSize(int commentPageSize) {
        this.commentPageSize = commentPageSize;
    }

    public boolean isCommentReplyCollapsed() {
        return commentReplyCollapsed;
    }

    public void setCommentReplyCollapsed(boolean commentReplyCollapsed) {
        this.commentReplyCollapsed = commentReplyCollapsed;
    }

    public int getCommentThreshold() {
        return commentThreshold;
    }

    public void setCommentThreshold(int commentThreshold) {
        this.commentThreshold = commentThreshold;
    }

    public boolean isDefaultCommentSort() {
        return defaultCommentSort;
    }

    public void setDefaultCommentSort(boolean defaultCommentSort) {
        this.defaultCommentSort = defaultCommentSort;
    }

    public boolean isIfValid() {
        return ifValid;
    }

    public void setIfValid(boolean ifValid) {
        this.ifValid = ifValid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        final UserPreference other = (UserPreference) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return "entity.UserPreference[id=" + id + "]";
    }

}
