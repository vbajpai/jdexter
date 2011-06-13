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
@Entity(name="MODERATOR_ACTION_COMMENT_TABLE")
public class ModeratorActionComment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="MOD_ACTION_COMMENT_ID")
    @Basic(optional=false)

    private long id;
    @JoinColumn(name = "COMMENT_ID", referencedColumnName = "COMMENT_ID",nullable=false)
    @ManyToOne
    private Comment comment;

    @Column(name="ACTION_TAKEN",nullable=false,length=4000)
    private String actionTaken;
    @JoinColumn(name = "MODERATOR_ID", referencedColumnName = "MODERATOR_ID",nullable=false)
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Moderator  moderator;
    @Column(name = "IF_VALID",nullable=false)
    private Boolean ifValid;

    protected ModeratorActionComment() {
    }

    public ModeratorActionComment(Comment comment, String actionTaken, Moderator moderator, Boolean ifValid) {
        this.comment = comment;
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

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
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
        final ModeratorActionComment other = (ModeratorActionComment) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }


    
    @Override
    public String toString() {
        return "entity.ModeratorActionComment[id=" + id + "]";
    }

}
