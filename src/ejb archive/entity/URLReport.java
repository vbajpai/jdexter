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
@Entity(name="URL_REPORT_TABLE")
public class URLReport implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="URL_REPORT_ID")
    @Basic(optional=false)
    private long id;
     @JoinColumn(name = "REPORTED_URL_ID", referencedColumnName = "URL_ID",nullable=false)
    @ManyToOne
    private URL reportedURL;
    @JoinColumn(name = "REPORTED_BY", referencedColumnName = "USER_ID",nullable=false)
    @ManyToOne
    private User reportedBy;
     @Column(name = "IF_VALID",nullable=false)
    private boolean ifValid;
     @Column(name="REASON",length=4000)
     private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
     
    public URLReport(URL reportedURL, User reportedBy, boolean ifValid, String reason) {
        this.reportedURL = reportedURL;
        this.reportedBy = reportedBy;
        this.ifValid = ifValid;
        this.reason=reason;
    }

    protected URLReport() {
    }

    public boolean isIfValid() {
        return ifValid;
    }

    public void setIfValid(boolean ifValid) {
        this.ifValid = ifValid;
    }

    public User getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(User reportedBy) {
        this.reportedBy = reportedBy;
    }

    public URL getReportedURL() {
        return reportedURL;
    }

    public void setReportedURL(URL reportedURL) {
        this.reportedURL = reportedURL;
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
        final URLReport other = (URLReport) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    
    @Override
    public String toString() {
        return "entity.URLReport[id=" + id + "]";
    }

}
