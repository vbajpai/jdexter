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
@Entity(name="URL_DETAIL_TABLE")
public class URLDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="URL_DETAIL_ID")
    @Basic(optional=false)

    private long id;
    @JoinColumn(name = "URL_ID", referencedColumnName = "URL_ID",nullable=false)
    @ManyToOne
    private URL url;
    @Column(name="CACHEURL",length=4000)
    private String cacheURL;
    @Column(name="CONTENT",length=4000)
    private String content;
    @Column(name="TITLE",length=4000)
    private String title;
    @Column(name="TITLE_NO_FORMATING",length=4000)
    private String titleNoFormating;
    @Column(name="VISIBLE_URL",length=4000)
    private String visibleURL;
    @Column(name = "IF_VALID",nullable=false)
    private boolean ifValid;

    protected URLDetail() {
    }

    public URLDetail(URL url, boolean ifValid) {
        this.url = url;
        this.ifValid = ifValid;
    }


    public String getCacheURL() {
        return cacheURL;
    }

    public void setCacheURL(String cacheURL) {
        this.cacheURL = cacheURL;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isIfValid() {
        return ifValid;
    }

    public void setIfValid(boolean ifValid) {
        this.ifValid = ifValid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleNoFormating() {
        return titleNoFormating;
    }

    public void setTitleNoFormating(String titleNoFormating) {
        this.titleNoFormating = titleNoFormating;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getVisibleURL() {
        return visibleURL;
    }

    public void setVisibleURL(String visibleURL) {
        this.visibleURL = visibleURL;
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
        final URLDetail other = (URLDetail) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }



    @Override
    public String toString() {
        return "entity.URLDetail[id=" + id + "]";
    }

}
