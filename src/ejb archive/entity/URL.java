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
@Entity(name="URL_TABLE")
public class URL implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional=false)
    @Column(name="URL_ID")
    private long id;
    @Column(name="UNESCAPEDURL",nullable=false,length=4000)
    private String usescapedURL;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID",nullable=false)
    @ManyToOne
    private User user;
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "CATEGORY_ID",nullable=false)
    @ManyToOne
    private Category category;
    @JoinColumn(name = "CHANNEL_ID", referencedColumnName = "CHANNEL_ID")
    @ManyToOne
    private Channel channel;
    @Column(name="VOTE_UP",nullable=false)
    private long voteUp;
    @Column(name="VOTE_DOWN",nullable=false)
    private long voteDown;
    @Lob
    @Column(name = "URL_ICON")
    private Serializable urlIcon;

    @Column(name="NO_OF_REPORTS",nullable=false)
    private long noOfReports;
    @Column(name = "SUBMISSION_DATE",nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date submissionDate;

    @Column(name = "IF_VALID",nullable=false)
    private boolean ifValid;
    
    protected URL() {
    }

    public URL(String usescapedURL, User user, Category Category, long voteUp, long voteDown, long noOfReports, Date submissionDate, boolean ifValid) {
        this.usescapedURL = usescapedURL;
        this.user = user;
        this.category = Category;
        this.voteUp = voteUp;
        this.voteDown = voteDown;
        this.noOfReports = noOfReports;
        this.submissionDate = submissionDate;
        this.ifValid = ifValid;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category Category) {
        this.category = Category;
    }

    public boolean isIfValid() {
        return ifValid;
    }

    public void setIfValid(boolean ifValid) {
        this.ifValid = ifValid;
    }

    public long getNoOfReports() {
        return noOfReports;
    }

    public void setNoOfReports(long noOfReports) {
        this.noOfReports = noOfReports;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public Serializable getUrlIcon() {
        return urlIcon;
    }

    public void setUrlIcon(Serializable urlIcon) {
        this.urlIcon = urlIcon;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsescapedURL() {
        return usescapedURL;
    }

    public void setUsescapedURL(String usescapedURL) {
        this.usescapedURL = usescapedURL;
    }

    public long getVoteDown() {
        return voteDown;
    }

    public void setVoteDown(long voteDown) {
        this.voteDown = voteDown;
    }

    public long getVoteUp() {
        return voteUp;
    }

    public void setVoteUp(long voteUp) {
        this.voteUp = voteUp;
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
        final URL other = (URL) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    


    @Override
    public String toString() {
        return "entity.URL[id=" + id + "]";
    }

}
