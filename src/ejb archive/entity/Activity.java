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
@Entity(name="ACTIVITY_TABLE")
public class Activity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ACTIVITY_ID")
    @Basic(optional=false)
    private long id;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID",nullable=false)
    @ManyToOne
    private User user;
    @JoinColumn(name = "URL_ID", referencedColumnName = "URL_ID",nullable=false)
    @ManyToOne
    private URL url;
    @Column(name="IF_VOTED_UP",nullable=false)
    private boolean ifVotedUp;
    @Column(name = "ACTIVITY_TIME",nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date activityTime;

    @Column(name = "IF_VALID",nullable=false)
    private boolean ifValid;

    protected  Activity() {
    }

    public Activity(User user, URL url, boolean ifVotedUp, Date activityTime, boolean ifValid) {
        this.user = user;
        this.url = url;
        this.ifVotedUp = ifVotedUp;
        this.activityTime = activityTime;
        this.ifValid = ifValid;
    }



    public Date getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Date activityTime) {
        this.activityTime = activityTime;
    }

    public boolean isIfValid() {
        return ifValid;
    }

    public void setIfValid(boolean ifValid) {
        this.ifValid = ifValid;
    }

    public boolean isIfVotedUp() {
        return ifVotedUp;
    }

    public void setIfVotedUp(boolean ifVotedUp) {
        this.ifVotedUp = ifVotedUp;
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
        final Activity other = (Activity) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    
    
    
    @Override
    public String toString() {
        return "entity.Activity[id=" + id + "]";
    }

}
