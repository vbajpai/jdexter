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
@Entity(name="MODERATOR_ACTION_URL_TABLE")
public class ModeratorActionURL implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional=false)
    @Column(name="MOD_ACTION_URL_ID")
    private long id;
    @JoinColumn(name = "URL_ID", referencedColumnName = "URL_ID",nullable=false)
    @ManyToOne
    private URL url;
    @Column(name="ACTION_TAKEN",nullable=false,length=4000)
    private String actionTaken;
    @JoinColumn(name = "MODERATOR_ID", referencedColumnName = "MODERATOR_ID",nullable=false)
    @ManyToOne
    private Moderator  moderator;
    @Column(name = "IF_VALID",nullable=false)
    private Boolean ifValid;

    protected ModeratorActionURL() {
    }

    public ModeratorActionURL(URL url, String actionTaken, Moderator moderator, Boolean ifValid) {
        this.url = url;
        this.actionTaken = actionTaken;
        this.moderator = moderator;
        this.ifValid = ifValid;
    }



    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public Boolean getIfValid() {
        return ifValid;
    }

    public void setIfValid(Boolean ifValid) {
        this.ifValid = ifValid;
    }

    public Moderator getModerator() {
        return moderator;
    }

    public void setModerator(Moderator moderator) {
        this.moderator = moderator;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
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
        final ModeratorActionURL other = (ModeratorActionURL) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }




    @Override
    public String toString() {
        return "entity.ModeratorActionURL[id=" + id + "]";
    }

}
