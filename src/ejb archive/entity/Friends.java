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
    @Entity(name="FRIENDS_TABLE")
    public class Friends implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional=false)
    @Column(name="FRIEND_REQUEST_ID")
    private long id;
    @JoinColumn(name = "USER_ID_FROM", referencedColumnName = "USER_ID",nullable=false)
    @ManyToOne
    private User userNameFrom;
    @JoinColumn(name = "USER_ID_TO", referencedColumnName = "USER_ID",nullable=false)
    @ManyToOne
    private User userNameTo;
    @Column(name = "DATE_REQUESTED",nullable=false)
    @Temporal(TemporalType.DATE)
    private Date dateRequested;

    @Column(name = "IF_FRIEND",nullable=false)
    private boolean ifFriend;

    @Column(name = "IF_VALID",nullable=false)
    private boolean ifValid;

    public Date getDateRequested() {
        return dateRequested;
    }

    public void setDateRequested(Date dateRequested) {
        this.dateRequested = dateRequested;
    }

    
    public Friends(User userNameFrom, User userNameTo, Date dateRequested,boolean ifFriend, boolean ifValid) {
        this.dateRequested=dateRequested;
        this.userNameFrom = userNameFrom;
        this.userNameTo = userNameTo;
        this.ifFriend = ifFriend;
        this.ifValid = ifValid;
    }

    protected Friends() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    


    public boolean isIfFriend() {
        return ifFriend;
    }

    public void setIfFriend(boolean ifFriend) {
        this.ifFriend = ifFriend;
    }

    public boolean isIfValid() {
        return ifValid;
    }

    public void setIfValid(boolean ifValid) {
        this.ifValid = ifValid;
    }

    public User getUserNameFrom() {
        return userNameFrom;
    }

    public void setUserNameFrom(User userNameFrom) {
        this.userNameFrom = userNameFrom;
    }

    public User getUserNameTo() {
        return userNameTo;
    }

    public void setUserNameTo(User userNameTo) {
        this.userNameTo = userNameTo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Friends other = (Friends) obj;
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
        return "entity.Friends[id=" + id + "]";
    }

}
