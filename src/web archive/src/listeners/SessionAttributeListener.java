package listeners;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class SessionAttributeListener implements HttpSessionAttributeListener {

    public void attributeAdded(HttpSessionBindingEvent se) {
        String attribute = se.getName();
        Object values = se.getValue();
        System.out.println("SessionAttribute:"+attribute+" Value:"+values+" just added!");
    }

    public void attributeRemoved(HttpSessionBindingEvent se) {
        String attribute = se.getName();
        Object values = se.getValue();
        System.out.println("SessionAttribute:"+attribute+" Value:"+values+" just removed!");
        
    }

    public void attributeReplaced(HttpSessionBindingEvent se) {
        String attribute = se.getName();
        Object values = se.getValue();
        System.out.println("SessionAttribute:"+attribute+" Value:"+values+" just replaced!");
    }
}