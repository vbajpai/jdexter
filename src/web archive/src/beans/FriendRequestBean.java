package beans;

import entity.User;
import java.io.Serializable;

public class FriendRequestBean implements Serializable {   

    private User requestFrom;
    private User requestTo;
    private boolean ifFriend;

    public boolean isIfFriend() {
        return ifFriend;
    }

    public void setIfFriend(boolean ifFriend) {
        this.ifFriend = ifFriend;
    }

    public User getRequestFrom() {
        return requestFrom;
    }

    public void setRequestFrom(User requestFrom) {
        this.requestFrom = requestFrom;
    }

    public User getRequestTo() {
        return requestTo;
    }

    public void setRequestTo(User requestTo) {
        this.requestTo = requestTo;
    }

}
