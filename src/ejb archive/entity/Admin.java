package entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "ADMIN_TABLE")
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ADMIN_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long adminId;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID",nullable=false,unique=true)
    @ManyToOne(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
    private User user;

    protected Admin() {
    }

    public Admin(User user) {
        this.user = user;
    }

    public long getAdminId() {
        return adminId;
    }   
   
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Admin other = (Admin) obj;
        if (this.adminId != other.adminId) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (int) (this.adminId ^ (this.adminId >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return "entity.Admin[adminId=" + adminId + "]";
    }

}
