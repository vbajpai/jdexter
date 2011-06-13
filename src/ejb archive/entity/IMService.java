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
@Entity(name = "IM_SERVICE_TABLE")
public class IMService implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IM_SERVICE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long imServiceId;
    @Column(name = "IF_VALID",nullable=false)
    private boolean ifValid;
    @Column(name = "SERVICE_NAME",nullable=false)
    private String serviceName;
    
    protected IMService() {
    }

    public IMService(boolean ifValid, String serviceName) {
        this.ifValid = ifValid;
        this.serviceName = serviceName;
        
    }



    public boolean isIfValid() {
        return ifValid;
    }

    public void setIfValid(boolean ifValid) {
        this.ifValid = ifValid;
    }

    public long getImServiceId() {
        return imServiceId;
    }

    public void setImServiceId(long imServiceId) {
        this.imServiceId = imServiceId;
    }

   
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IMService other = (IMService) obj;
        if (this.imServiceId != other.imServiceId) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (this.imServiceId ^ (this.imServiceId >>> 32));
        return hash;
    }
    
    
   
    @Override
    public String toString() {
        return "entity.IMService[imServiceId=" + imServiceId + "]";
    }

}
