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
@Entity(name="SHOUTS_TABLE")
public class Shouts implements Serializable {
private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Basic(optional=false)
@Column(name="SHOUT_ID")
private long id;
@JoinColumn(name = "USER_ID_FROM", referencedColumnName = "USER_ID",nullable=false)
@ManyToOne
private User userFrom;
@JoinColumn(name = "USER_ID_TO", referencedColumnName = "USER_ID",nullable=false)
@ManyToOne
private User userTo;
@Column(name = "SHOUT",nullable=false,length=4000)
private String shout;
@Column(name="SHOUT_TIME")
@Temporal(TemporalType.TIMESTAMP)
private Date shoutTime;
@Column(name = "IF_VALID",nullable=false)
private boolean ifValid;
@Transient
private String shoutTimeString;
@Transient
private boolean ifBlocked;

    public boolean isIfBlocked() {
        return ifBlocked;
    }

    public void setIfBlocked(boolean ifBlocked) {
        this.ifBlocked = ifBlocked;
    }


    public String getShoutTimeString() {
        return shoutTimeString;
    }

    public void setShoutTimeString(String shoutTimeString) {
        this.shoutTimeString = shoutTimeString;
    }

    protected Shouts() {
    }

    public Shouts(User userFrom, User userTo, String shout, Date shoutTime, boolean ifValid) {
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.shout = shout;
        this.shoutTime = shoutTime;
        this.ifValid = ifValid;
    }


    public boolean isIfValid() {
        return ifValid;
    }

    public void setIfValid(boolean ifValid) {
        this.ifValid = ifValid;
    }

    public String getShout() {
        return shout;
    }

    public void setShout(String shout) {
        this.shout = shout;
    }

    public Date getTime() {
        return shoutTime;
    }

    public void setTime(Date shoutTime) {
        this.shoutTime = shoutTime;
    }

    public User getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    public User getUserTo() {
        return userTo;
    }

    public void setUserTo(User userTo) {
        this.userTo = userTo;
    }

   
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getShoutTime() {
        return shoutTime;
    }

    public void setShoutTime(Date shoutTime) {
        this.shoutTime = shoutTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Shouts other = (Shouts) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }



@Override
public String toString() {
    return "entity.Shouts[id=" + id + "]";
}

}
