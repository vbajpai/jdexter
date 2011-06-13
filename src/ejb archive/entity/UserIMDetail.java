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
@Entity(name="USER_IM_DETAIL_TABLE")
public class UserIMDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional=false)
    @Column(name="USER_IM_ID")
    private long id;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID",nullable=false)
    @ManyToOne
    private User user;
    @JoinColumn(name = "IM_SERVICE_ID", referencedColumnName = "IM_SERVICE_ID",nullable=false)
    @ManyToOne
    private IMService iMService;
    @Column(name = "IM_ACCOUNT_NAME",nullable=false)
    private String iMAccountName;
    @Column(name = "IF_VALID",nullable=false)
    private boolean ifValid;

    public UserIMDetail(User user, IMService iMService, String iMAccountName, boolean ifValid) {
        this.user = user;
        this.iMService = iMService;
        this.iMAccountName = iMAccountName;
        this.ifValid = ifValid;
    }

    protected UserIMDetail() {
    }

    public String getIMAccountName() {
        return iMAccountName;
    }

    public void setIMAccountName(String iMAccountName) {
        this.iMAccountName = iMAccountName;
    }

    public IMService getIMService() {
        return iMService;
    }

    public void setIMService(IMService iMService) {
        this.iMService = iMService;
    }

    public boolean getIfValid() {
        return ifValid;
    }

    public void setIfValid(boolean ifValid) {
        this.ifValid = ifValid;
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
        final UserIMDetail other = (UserIMDetail) obj;
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
        return "entity.UserIMDetail[id=" + id + "]";
    }

}
