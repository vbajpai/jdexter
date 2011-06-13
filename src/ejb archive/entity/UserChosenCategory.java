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
@Entity(name="USER_CHOSEN_CATEGORY_TABLE")
public class UserChosenCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional=false)
    @Column(name = "USER_CHOSEN_CATEGORY_ID")
    private long id;
    
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID",nullable=false)
    @ManyToOne
    private User user;
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "CATEGORY_ID",nullable=false)
    @ManyToOne
    private Category category;
    
    @Column(name = "IF_VALID",nullable=false)
    private Boolean ifValid;

    public UserChosenCategory(User user, Category category, Boolean ifValid) {
        this.user = user;
        this.category = category;
        this.ifValid = ifValid;
    }

    protected UserChosenCategory() {
    }
    
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Boolean getIfValid() {
        return ifValid;
    }

    public void setIfValid(Boolean ifValid) {
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
        final UserChosenCategory other = (UserChosenCategory) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }



    @Override
    public String toString() {
        return "entity.UserChosenCategory[id=" + id + "]";
    }

}
