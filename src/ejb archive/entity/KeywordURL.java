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
@Entity(name="KEYWORD_URL_TABLE")
public class KeywordURL implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional=false)
    @Column(name="KEYWORD_URL_ID")
    private long id;
    @JoinColumn(name = "URL_ID", referencedColumnName = "URL_ID",nullable=false)
    @ManyToOne
    private URL url;
    @Column(name="KEYWORD",nullable=false)
    private String keyword;
    @Column(name="VOTE_UP",nullable=false)
    private long voteUp;
    @Column(name="VOTE_DOWN",nullable=false)
    private long voteDown;
    @Column(name = "IF_VALID",nullable=false)
    private boolean ifValid;

    protected KeywordURL() {
    }

    public KeywordURL(URL url, String keyword, long voteUp, long voteDown, boolean ifValid) {
        this.url = url;
        this.keyword = keyword;
        this.voteUp = voteUp;
        this.voteDown = voteDown;
        this.ifValid = ifValid;
    }


    public boolean isIfValid() {
        return ifValid;
    }

    public void setIfValid(boolean ifValid) {
        this.ifValid = ifValid;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
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
        final KeywordURL other = (KeywordURL) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return "entity.KeywordURLTable[id=" + id + "]";
    }

}
