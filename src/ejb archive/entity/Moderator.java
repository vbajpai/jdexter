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
@Entity(name="MODERATOR_TABLE")
public class Moderator implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional=false)
    @Column(name="MODERATOR_ID")
    private long id;

    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID",nullable=false)
    @ManyToOne
    private User userId;
    @Column(name = "IF_VALID",nullable=false)
    private Boolean ifValid;

    protected Moderator() {
    }

    public Moderator(User userId, Boolean ifValid) {
        this.userId = userId;
        this.ifValid = ifValid;
    }

 
    public Boolean getIfValid() {
        return ifValid;
    }

    public void setIfValid(Boolean ifValid) {
        this.ifValid = ifValid;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
        final Moderator other = (Moderator) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }
    

   

    @Override
    public String toString() {
        return "entity.Moderator[id=" + id + "]";
    }

}
