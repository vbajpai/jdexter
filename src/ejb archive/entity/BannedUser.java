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
@Entity(name = "BANNED_USER_TABLE")
public class BannedUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional=false)
    @Column(name="BANNED_USER_ID")
    private long id;
    @JoinColumn(name = "USER_ID_BANNED", referencedColumnName = "USER_ID",nullable=false)
    @ManyToOne
    private User userBanned;

    @Column(name = "DATE_BANNED",nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateBanned;
    @Column(name = "IF_VALID",nullable=false)
    private boolean ifValid;

    protected BannedUser() {
    }

    public BannedUser(User userBanned,  Date dateBanned, boolean ifValid) {
        this.userBanned = userBanned;
        this.dateBanned = dateBanned;
        this.ifValid = ifValid;
    }


   
    public Date getDateBanned() {
        return dateBanned;
    }

    public void setDateBanned(Date dateBanned) {
        this.dateBanned = dateBanned;
    }

    public boolean isIfValid() {
        return ifValid;
    }

    public void setIfValid(boolean ifValid) {
        this.ifValid = ifValid;
    }

    public User getUserBanned() {
        return userBanned;
    }

    public void setUserBanned(User userBanned) {
        this.userBanned = userBanned;
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
        final BannedUser other = (BannedUser) obj;
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
        return "entity.BannedUser[id=" + id + "]";
    }

}
