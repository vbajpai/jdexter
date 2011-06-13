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
@Entity(name="CATEGORY_TABLE")

public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "CATEGORY_ID")
    private long id;
    @JoinColumn(name = "PARENT_CATEGORY_ID", referencedColumnName = "CATEGORY_ID")
    @ManyToOne
    private Category parentCategory;
    @Column(name = "CATEGORY_NAME",nullable=false)
    private String categoryName;
    @Column(name = "DATE_ADDED",nullable=false)
    @Temporal(TemporalType.DATE)
    private Date dateAdded;
    @Column(name = "IF_VALID",nullable=false)
    private Boolean ifValid;

    protected Category() {
    }

    public Category(String categoryName, Date dateAdded,  Boolean ifValid) {
        
        this.categoryName = categoryName;      
        this.dateAdded = dateAdded;        
        this.ifValid = ifValid;
    }
  
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Boolean getIfValid() {
        return ifValid;
    }

    public void setIfValid(Boolean ifValid) {
        this.ifValid = ifValid;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
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
        final Category other = (Category) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }
   
    @Override
    public String toString() {
        return "entity.Category[id=" + id + "]";
    }

}
