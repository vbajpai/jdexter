/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.persistence.*;


/**
 *
 * @author rahul
 */
@Entity(name="CHANNEL_TABLE")
public class Channel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional=false)
    @Column(name = "CHANNEL_ID")
    
    private long id;
    @Column(name = "CHANNEL_NAME",nullable=false)
    private String channelName;
    @Column(name = "VISIBLE_URL",nullable=false,length=4000)
    private String visibleURL;

    @Column(name = "DATE_ADDED",nullable=false)
    @Temporal(TemporalType.DATE)
    private Date dateAdded;
    @Column(name = "IF_VALID",nullable=false)
    private Boolean ifValid;
    

    protected Channel() {        
    }

    public Channel(String channelName,String visibleURL, Date dateAdded,  Boolean ifValid) {
       
        this.channelName = channelName;
        this.dateAdded = dateAdded;
        this.visibleURL=visibleURL;
        this.ifValid = ifValid;
    }

    public String getVisibleURL() {
        return visibleURL;
    }

    public void setVisibleURL(String visibleURL) {
        this.visibleURL = visibleURL;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Boolean getIfValid() {
        return ifValid;
    }

    public void setIfValid(Boolean ifValid) {
        this.ifValid = ifValid;
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
        final Channel other = (Channel) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }


    
    @Override
    public String toString() {
        return "entity.Channel[id=" + id + "]";
    }

}
