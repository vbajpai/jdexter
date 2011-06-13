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
@Entity(name="FAVORITE_TABLE")
public class Favorite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="FAVORITE_ID")
    @Basic(optional=false)
    private long id;
    @JoinColumn(name = "URL_ID", referencedColumnName = "URL_ID",nullable=false)
    @ManyToOne
    private URL url;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID",nullable=false)
    @ManyToOne
    private User user;
    @Column(name = "FAVORITED_AT_TIME",nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date favoritedAtTime;
    @Column(name="LABEL")
    private String label;
     @Column(name = "IF_VALID",nullable=false)
    private boolean ifValid;

    protected Favorite() {
    }

    public Favorite(URL url, User user, String label,boolean ifValid, Date favoritedAtTime) {
        this.url = url;
        this.user = user;
        this.ifValid = ifValid;
        this.label=label;
        this.favoritedAtTime=favoritedAtTime;
    }

    public Date getFavoritedAtTime() {
        return favoritedAtTime;
    }

    public void setFavoritedAtTime(Date favoritedAtTime) {
        this.favoritedAtTime = favoritedAtTime;
    }


    
    public boolean isIfValid() {
        return ifValid;
    }

    public void setIfValid(boolean ifValid) {
        this.ifValid = ifValid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
        final Favorite other = (Favorite) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }
    

    
    @Override
    public String toString() {
        return "entity.Favorite[id=" + id + "]";
    }

}
