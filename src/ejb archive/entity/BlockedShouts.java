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
@Entity(name="BLOCKED_SHOUTS_TABLE")
public class BlockedShouts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional=false)
    @Column(name="BLOCKED_SHOUTS_ID")
    private long id;
    @JoinColumn(name = "USER_ID_FROM", referencedColumnName = "USER_ID",nullable=false)
    @ManyToOne
    private User userFrom;
    @JoinColumn(name = "USER_ID_TO", referencedColumnName = "USER_ID",nullable=false)
    @ManyToOne
    private User userTo;
    @Column(name = "IF_VALID",nullable=false)
    private boolean ifValid;

    protected BlockedShouts() {
    }

    public BlockedShouts(User userFrom, User userTo, boolean ifValid) {
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.ifValid = ifValid;
    }

   
    public boolean isIfValid() {
        return ifValid;
    }

    public void setIfValid(boolean ifValid) {
        this.ifValid = ifValid;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BlockedShouts other = (BlockedShouts) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }



   
    @Override
    public String toString() {
        return "entity.BlockedShouts[id=" + id + "]";
    }

}
