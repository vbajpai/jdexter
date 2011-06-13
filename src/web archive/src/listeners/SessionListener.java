package listeners;

import beans.CategoryBean;
import beans.utilities.CountryBean;
import beans.utilities.ModeratorBean;
import beans.utilities.UserBean;
import ejb.CategoryFacadeRemote;
import ejb.ChannelFacadeRemote;
import entity.Category;
import entity.Channel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import utilities.EJBUtility;

public class SessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent event) {

        System.out.println("Session Created!");
        
        HttpSession session = event.getSession();

        /* add CountryBean to session*/
        CountryBean cb = (CountryBean) session.getAttribute("CountryBean");
        if( cb == null ){
            cb = new CountryBean();
            session.setAttribute("CountryBean", cb );
        }

        /* add CategoryBean to context */
        ServletContext servletContext = session.getServletContext();
        ArrayList<CategoryBean> categoryList = (ArrayList<CategoryBean>) servletContext.getAttribute("category");
        if(categoryList == null){
            try {
                categoryList = fetchCategory();
            } catch (NamingException ex) {}
            servletContext.setAttribute("category", categoryList);
        }

        /* add ChannelBean to context */
        ArrayList<Channel> channelList = (ArrayList<Channel>) servletContext.getAttribute("channel");
        if(channelList == null){
            try {
                channelList = fetchChannels();                
            } catch (NamingException ex) {
                Logger.getLogger(SessionListener.class.getName()).log(Level.SEVERE, null, ex);
            }
            servletContext.setAttribute("channel", channelList);
        }

    }

    public void sessionDestroyed(HttpSessionEvent arg0) {
        System.out.println("Session Destroyed!");
    }

    private ArrayList<CategoryBean> fetchCategory() throws NamingException {

        Iterator<Category> iterator = null;

        /* Create a ArrayList */
        ArrayList<CategoryBean> categoryList = new ArrayList();

        /* Get Remote Object */
        CategoryFacadeRemote categoryRemote = (CategoryFacadeRemote) EJBUtility.lookup("CategoryFacade");

        /* Fetch RecordSet */
        List<Category> recordSet = categoryRemote.findAll();
        iterator = recordSet.iterator();

        /* Iterate RecordSet to get Parent Categories */
        while(iterator.hasNext()){
            Category record = iterator.next();
            if(record.getParentCategory() == null){ // the record is ParentCategory.

                /* Map the Record to Bean */
                CategoryBean bean = new CategoryBean();
                ArrayList<CategoryBean> childlist = new ArrayList();
                bean.setCategoryname(record.getCategoryName());
                bean.setChild(childlist);

                /* Push Bean to ArrayList */
                categoryList.add(bean);
            }
        }

        iterator = recordSet.iterator();

        /* Iterate RecordSet to get Child Categories */
        while(iterator.hasNext()){
            Category record = iterator.next();
            Category parent = record.getParentCategory();
            if(parent !=null){ // the record is ChildCategory

                String parentName = parent.getCategoryName();
                Iterator<CategoryBean> listiterator = categoryList.iterator();
                while(listiterator.hasNext()){
                    CategoryBean parentBean = listiterator.next();
                    if(parentBean.getCategoryname().equals(parentName)){
                        ArrayList<CategoryBean> childList = parentBean.getChild();
                        CategoryBean bean = new CategoryBean();
                        bean.setCategoryname(record.getCategoryName());
                        childList.add(bean);
                    }
                }
            }
        }

        /* Return ArrayList */
        return(categoryList);
    }

    private ArrayList<Channel> fetchChannels() throws NamingException {

        /* Create ArrayList */
        ArrayList<Channel> channelList = new ArrayList<Channel>();

        /* Get Remote Object */
        ChannelFacadeRemote channelRemote = (ChannelFacadeRemote) EJBUtility.lookup("ChannelFacade");

        /* Get Record Set */
        List<Channel> recordSet = channelRemote.findAll();

        /* Get Iterator */
        Iterator<Channel> iterator = recordSet.iterator();

        /* Iterate Record Set */
        while(iterator.hasNext()){
            Channel record = iterator.next();            
            channelList.add(record);
        }

        /* Return ArrayList */
        return (channelList);
    }
}