/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package actions.admin;

import beans.admin.ChannelFormBean;
import ejb.ChannelFacadeRemote;
import entity.Channel;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import utilities.EJBUtility;

/**
 *
 * @author rahul
 */
public class ChannelAction extends org.apache.struts.action.Action {
    
    
    private final static String SUCCESS = "return";
    ChannelFacadeRemote channelFacadeRemote;
    ChannelFormBean channelFormBean;
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
            channelFormBean= (ChannelFormBean) form;
            /*Get Remote Object*/
            channelFacadeRemote = (ChannelFacadeRemote) EJBUtility.lookup("ChannelFacade");
            /*Create Channel*/
            if(channelFormBean.getCreateChannel()!=null)
                createChannel(channelFormBean);
            /*Remove Channel*/
            if(channelFormBean.getRemoveChannel()!=null)
                removeChannel(channelFormBean);
            /*Update Channel*/
            HttpSession session = request.getSession();
            session.setAttribute("ChannelBean", channelFacadeRemote.findAll());
        return mapping.findForward(SUCCESS);
    }

    private void createChannel(ChannelFormBean channelFormBean) {
        if(channelFormBean.getVisibleUrl().trim().length()==0)
            channelFormBean.setAddError("Visible URL not provided");
        else
        {
            if (channelFormBean.getCreateChannel().trim().length()==0) {
                channelFormBean.setAddError("Channel Name not provided");
            } else {
                if(channelFacadeRemote.findByChannelName(channelFormBean.getCreateChannel())!=null)
                {
                    channelFormBean.setAddError("Channel Already Exists");
                }
                else
                {
                        channelFacadeRemote.create(new Channel(channelFormBean.getCreateChannel(), channelFormBean.getVisibleUrl(), new Date(), true));
                        channelFormBean.setAddSuccess("Channel created");
                        channelFormBean.setCreateChannel(null);
                        channelFormBean.setVisibleUrl(null);
                }
            }

        }
    }

    private void removeChannel(ChannelFormBean removeChannel) {
        Channel channel=channelFacadeRemote.findByChannelName(removeChannel.getRemoveChannel());
        if(channel!=null)
        {

                channelFacadeRemote.remove(channel);
                channelFormBean.setRemoveSuccess("Channel Deleted");
        }
        else
        {
            channelFormBean.setRemoveError("Channel Couldn't be deleted");
        }
    }
}
