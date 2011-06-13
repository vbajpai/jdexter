/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.KeywordURL;
import entity.URL;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rahul
 */
@Remote
public interface KeywordURLFacadeRemote {

    void create(KeywordURL keywordURL);

    void edit(KeywordURL keywordURL);

    void remove(KeywordURL keywordURL);

    KeywordURL find(Object id);

    List<KeywordURL> findAll();

    public boolean ifExistKeywordURL(entity.KeywordURL keywordURL);

    public KeywordURL findbyURLandKeyword(KeywordURL keywordURL);

    public KeywordURL findbyURLandKeywordID(URL url, long id);

    public java.util.ArrayList<entity.KeywordURL> getListwithKeyword(java.lang.String keyword);

}
