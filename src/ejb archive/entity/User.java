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
@Entity(name = "USER_TABLE")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long userId;
    @JoinColumn(name = "ACTIVITY_VISIBLE_TO", referencedColumnName = "VISIBILITY_VALIDATION_ID",nullable=false)
    @ManyToOne
    private VisibilityValidation activityVisibleTo;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "DATE_JOINED",nullable=false)
    @Temporal(TemporalType.DATE)
    private Date dateJoined;
    @Column(name = "DATE_OF_BIRTH")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    
    @Column(name = "EMAIL",nullable=false,unique=true)
    private String email;
    @Column(name="LANDING_PAGE")
    private String landingPage;

    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "GENDER")
    private boolean gender;
    @Column(name = "IF_VALID",nullable=false)
    private boolean ifValid;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "LOCATION")
    private String location;
    @Column(name = "PASSWORD",nullable=false)
    private String password;
    @Lob
    @Column(name = "PROFILE_PICTURE")
    private Serializable profilePicture;
    @Column(name = "USER_NAME",nullable=false,unique=true)
    private String userName;

       
    protected User() {
    }


    public User(VisibilityValidation activityVisibleTo, Date dateJoined, String email, boolean ifValid, String password, String userName) {
        this.activityVisibleTo = activityVisibleTo;
        this.dateJoined = dateJoined;
        this.email = email;
        this.ifValid = ifValid;
        this.password = password;
        this.userName = userName;
    }

    public String getLandingPage() {
        return landingPage;
    }

    public void setLandingPage(String landingPage) {
        this.landingPage = landingPage;
    }

    public VisibilityValidation getActivityVisibleTo() {
        return activityVisibleTo;
    }

    public void setActivityVisibleTo(VisibilityValidation activityVisibleTo) {
        this.activityVisibleTo = activityVisibleTo;
    }


   
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

   
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isIfValid() {
        return ifValid;
    }

    public void setIfValid(boolean ifValid) {
        this.ifValid = ifValid;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Serializable getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Serializable profilePicture) {
        this.profilePicture = profilePicture;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (int) (this.userId ^ (this.userId >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return "entity.User[userId=" + userId + "]";
    }

}
