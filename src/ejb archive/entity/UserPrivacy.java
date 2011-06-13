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
@Entity(name="USER_PRIVACY_TABLE")
public class UserPrivacy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional=false)
    @Column(name="USER_PRIVACY_ID")
    private long id;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID",nullable=false)
    @ManyToOne
    private User user;
    @JoinColumn(name = "NAME_VISIBILITY", referencedColumnName = "VISIBILITY_VALIDATION_ID",nullable=false)
    @ManyToOne
    private VisibilityValidation nameVisibility;
    @JoinColumn(name = "AGE_VISIBILITY", referencedColumnName = "VISIBILITY_VALIDATION_ID",nullable=false)
    @ManyToOne
    private VisibilityValidation ageVisibility;
    @JoinColumn(name = "GENDER_VISIBILITY", referencedColumnName = "VISIBILITY_VALIDATION_ID",nullable=false)
    @ManyToOne
    private VisibilityValidation genderVisibility;
    @JoinColumn(name = "LOCATION_VISIBILITY", referencedColumnName = "VISIBILITY_VALIDATION_ID",nullable=false)
    @ManyToOne
    private VisibilityValidation locationVisibilityVisibility;
    @JoinColumn(name = "EMAIL_VISIBILITY", referencedColumnName = "VISIBILITY_VALIDATION_ID",nullable=false)
    @ManyToOne
    private VisibilityValidation emailVisibility;
    @JoinColumn(name = "PROFILE_LINKS_VISIBILITY", referencedColumnName = "VISIBILITY_VALIDATION_ID",nullable=false)
    @ManyToOne
    private VisibilityValidation profileLinksVisibility;
    @JoinColumn(name = "SHOUTS_VISIBILITY", referencedColumnName = "VISIBILITY_VALIDATION_ID",nullable=false)
    @ManyToOne
    private VisibilityValidation shoutsVisibility;

    public UserPrivacy(User user, VisibilityValidation nameVisibility, VisibilityValidation ageVisibility, VisibilityValidation genderVisibility, VisibilityValidation locationVisibilityVisibility, VisibilityValidation emailVisibility, VisibilityValidation profileLinksVisibility, VisibilityValidation shoutsVisibility) {
        this.user = user;
        this.nameVisibility = nameVisibility;
        this.ageVisibility = ageVisibility;
        this.genderVisibility = genderVisibility;
        this.locationVisibilityVisibility = locationVisibilityVisibility;
        this.emailVisibility = emailVisibility;
        this.profileLinksVisibility = profileLinksVisibility;
        this.shoutsVisibility = shoutsVisibility;
    }

    protected UserPrivacy() {
    }

    
    public VisibilityValidation getAgeVisibility() {
        return ageVisibility;
    }

    public void setAgeVisibility(VisibilityValidation ageVisibility) {
        this.ageVisibility = ageVisibility;
    }

    public VisibilityValidation getEmailVisibility() {
        return emailVisibility;
    }

    public void setEmailVisibility(VisibilityValidation emailVisibility) {
        this.emailVisibility = emailVisibility;
    }

    public VisibilityValidation getGenderVisibility() {
        return genderVisibility;
    }

    public void setGenderVisibility(VisibilityValidation genderVisibility) {
        this.genderVisibility = genderVisibility;
    }

    public VisibilityValidation getLocationVisibilityVisibility() {
        return locationVisibilityVisibility;
    }

    public void setLocationVisibilityVisibility(VisibilityValidation locationVisibilityVisibility) {
        this.locationVisibilityVisibility = locationVisibilityVisibility;
    }

    public VisibilityValidation getNameVisibility() {
        return nameVisibility;
    }

    public void setNameVisibility(VisibilityValidation nameVisibility) {
        this.nameVisibility = nameVisibility;
    }

    public VisibilityValidation getProfileLinksVisibility() {
        return profileLinksVisibility;
    }

    public void setProfileLinksVisibility(VisibilityValidation profileLinksVisibility) {
        this.profileLinksVisibility = profileLinksVisibility;
    }

    public VisibilityValidation getShoutsVisibility() {
        return shoutsVisibility;
    }

    public void setShoutsVisibility(VisibilityValidation shoutsVisibility) {
        this.shoutsVisibility = shoutsVisibility;
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
        final UserPrivacy other = (UserPrivacy) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    


    @Override
    public String toString() {
        return "entity.UserPrivacy[id=" + id + "]";
    }

}
