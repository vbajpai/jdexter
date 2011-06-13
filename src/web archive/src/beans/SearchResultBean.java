package beans;

import java.io.Serializable;

public class SearchResultBean implements Serializable, Comparable {

    private String cacheUrl;
    private String content;
    private String title;
    private String titleNoFormatting;
    private String unescapedUrl;
    private String url;
    protected String visibleUrl;
    private String ID;

    /* iDexter specific Parameters */
    private String category;
    private String channel;
    private long numberofreports;
    private String submissiondate;
    private String userFullName;
    private long voteUp;
    private long voteDown;

    /* Activity Parameters */
    private boolean ifmodded;
    private boolean ifvotedup;

    /* Keyword Parameters */
    private long keywordurlid;

    /* Favorite Parameters */
    private String tags;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    

    public long getKeywordurlid() {
        return keywordurlid;
    }

    public void setKeywordurlid(long keywordurlid) {
        this.keywordurlid = keywordurlid;
    }

    public boolean isIfvotedup() {
        return ifvotedup;
    }

    public void setIfvotedup(boolean ifvotedup) {
        this.ifvotedup = ifvotedup;
    }

    public boolean isIfmodded() {
        return ifmodded;
    }

    public void setIfmodded(boolean ifmodded) {
        this.ifmodded = ifmodded;
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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getSubmissiondate() {
        return submissiondate;
    }

    public void setSubmissiondate(String submissiondate) {
        this.submissiondate = submissiondate;
    }

    public long getNumberofreports() {
        return numberofreports;
    }

    public void setNumberofreports(long numberofreports) {
        this.numberofreports = numberofreports;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getVisibleUrl() {
        return visibleUrl;
    }

    public void setVisibleUrl(String visibleUrl) {
        this.visibleUrl = visibleUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUnescapedUrl() {
        return unescapedUrl;
    }

    public void setUnescapedUrl(String unescapedUrl) {
        this.unescapedUrl = unescapedUrl;
    }

    public String getTitleNoFormatting() {
        return titleNoFormatting;
    }

    public void setTitleNoFormatting(String titleNoFormatting) {
        this.titleNoFormatting = titleNoFormatting;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCacheUrl() {
        return cacheUrl;
    }

    public void setCacheUrl(String cacheurl) {
        this.cacheUrl = cacheurl;
    }

    public int compareTo(Object o) {

        SearchResultBean bean = (SearchResultBean) o;
        long beanEffectiveVote = bean.getVoteUp() - bean.getVoteDown();
        long thisEffectiveVote = this.voteUp - this.voteDown;

        if(thisEffectiveVote > beanEffectiveVote){
            return (-1);
        }else{
            if(thisEffectiveVote < beanEffectiveVote){
                return (1);
            }else{
                return 0;
            }
        }
    }

}
