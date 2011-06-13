/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author rahul
 */
@Entity(name="KEYWORD_ACTIVITY_TABLE")
public class KeywordActivity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="KEYWORD_ENTITY_ID")
    @Basic(optional=false)
    private Long id;
    @JoinColumn(name = "KEYWORD_URL_ID", referencedColumnName = "KEYWORD_URL_ID",nullable=false)
    @ManyToOne
    private KeywordURL keywordURL;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID",nullable=false)
    @ManyToOne
    private User user;
    @Column(name="ACTIVITY_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actitvityTime;
    @Column(name="IF_VOTED_UP",nullable=false)
    private boolean ifVotedUp;

    public boolean isIfVotedUp() {
        return ifVotedUp;
    }

    public void setIfVotedUp(boolean ifVotedUp) {
        this.ifVotedUp = ifVotedUp;
    }
    

  
    public KeywordActivity() {
    }

    public KeywordActivity(KeywordURL keywordURL, User user, Date actitvityTime) {        
        this.keywordURL = keywordURL;
        this.user = user;
        this.actitvityTime = actitvityTime;
    }

    public Date getActitvityTime() {
        return actitvityTime;
    }

    public void setActitvityTime(Date actitvityTime) {
        this.actitvityTime = actitvityTime;
    }

    public KeywordURL getKeywordURL() {
        return keywordURL;
    }

    public void setKeywordURL(KeywordURL keywordURL) {
        this.keywordURL = keywordURL;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KeywordActivity)) {
            return false;
        }
        KeywordActivity other = (KeywordActivity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.KeywordEntity[id=" + id + "]";
    }

}
