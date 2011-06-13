package listeners;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class ContextAttributeListener implements ServletContextAttributeListener {

    public void attributeAdded(ServletContextAttributeEvent scab) {
        String attribute = scab.getName();
        Object values = scab.getValue();
        System.out.println("ContextAttribute:"+attribute+" Value:"+values+" just added!");
    }

    public void attributeRemoved(ServletContextAttributeEvent scab) {
        String attribute = scab.getName();
        Object values = scab.getValue();
        System.out.println("ContextAttribute:"+attribute+" Value:"+values+" just removed!");
    }

    public void attributeReplaced(ServletContextAttributeEvent scab) {
        String attribute = scab.getName();
        Object values = scab.getValue();
        System.out.println("ContextAttribute:"+attribute+" Value:"+values+" just replaced!");
    }
}