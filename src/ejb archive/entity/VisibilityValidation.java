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
@Entity(name = "VISIBILITY_VALIDATION_TABLE")
public class VisibilityValidation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "VISIBILITY_VALIDATION_ID")
    private long visibilityValidationId;
    @Column(name = "DATE_ADDED",nullable=false)
    @Temporal(TemporalType.DATE)
    private Date dateAdded;
    @Column(name = "IF_VALID",nullable=false)
    private boolean ifValid;
    @Column(name = "VISIBILITY_NAME",nullable=false)
    private String visibilityName;

    public VisibilityValidation(Date dateAdded, boolean ifValid, String visibilityName) {
        this.dateAdded = dateAdded;
        this.ifValid = ifValid;
        this.visibilityName = visibilityName;
    }

    protected VisibilityValidation() {
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public boolean isIfValid() {
        return ifValid;
    }

    public void setIfValid(boolean ifValid) {
        this.ifValid = ifValid;
    }

    public String getVisibilityName() {
        return visibilityName;
    }

    public void setVisibilityName(String visibilityName) {
        this.visibilityName = visibilityName;
    }

    public long getVisibilityValidationId() {
        return visibilityValidationId;
    }

    public void setVisibilityValidationId(long visibilityValidationId) {
        this.visibilityValidationId = visibilityValidationId;
    }

    @Override
    public String toString() {
        return "entity.VisibilityValidation[visibilityValidationId=" + visibilityValidationId + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VisibilityValidation other = (VisibilityValidation) obj;
        if (this.visibilityValidationId != other.visibilityValidationId) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (int) (this.visibilityValidationId ^ (this.visibilityValidationId >>> 32));
        return hash;
    }
}
