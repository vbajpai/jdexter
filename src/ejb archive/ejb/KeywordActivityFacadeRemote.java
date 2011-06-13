/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import javax.ejb.Remote;

/**
 *
 * @author rahul
 */
@Remote
public interface KeywordActivityFacadeRemote {

    public void create(entity.KeywordActivity keywordActivity);

    public void edit(entity.KeywordActivity keywordActivity);

    public void remove(entity.KeywordActivity keywordActivity);

    public entity.KeywordActivity find(java.lang.Object id);

    public entity.KeywordActivity findbyUserKeywordURL(entity.User user, entity.KeywordURL keywordURL);

    public entity.KeywordActivity findbyUserKeywordURL(entity.User user, long keywordURLID);

}
