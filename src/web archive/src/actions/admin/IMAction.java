/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package actions.admin;

import beans.admin.IMFormBean;
import ejb.IMServiceFacadeRemote;
import entity.IMService;
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
public class IMAction extends org.apache.struts.action.Action {
    
    private final static String SUCCESS = "return";
    IMServiceFacadeRemote imFacadeRemote;
    IMFormBean imFormBean;
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
            imFormBean= (IMFormBean) form;
            /*Get Remote Object*/
            imFacadeRemote =  (IMServiceFacadeRemote) EJBUtility.lookup("IMServiceFacade");
            /*Create IM Service*/
            if(imFormBean.getCreateIM()!=null)
                createIM();
            /*Remove IM Service*/
            if(imFormBean.getRemoveIM()!=null)
                removeIM();
            /*Update IM Service*/
            HttpSession session = request.getSession();
            session.setAttribute("IMBean", imFacadeRemote.findAll());

        return mapping.findForward(SUCCESS);
    }

    private void createIM() {
        if(imFormBean.getCreateIM().trim().length()==0){
            imFormBean.setAddError("IM Service Name not Provided");
        }else{
            if(imFacadeRemote.findByServiceName(imFormBean.getCreateIM())!=null)
            {
                imFormBean.setAddError("IM Service exists");
            }
            else
            {
                imFacadeRemote.create(new IMService(true, imFormBean.getCreateIM()));
                imFormBean.setAddSuccess("IM Service Created");
                imFormBean.setCreateIM(null);
            }
        }
    }

    private void removeIM() {
        IMService imService=imFacadeRemote.findByServiceName(imFormBean.getRemoveIM());
        if(imService==null)
            imFormBean.setRemoveError("IM service couldn't be removed");
        else
        {
            imFormBean.setRemoveSuccess("IM Service Removed");
            imFacadeRemote.remove(imService);
        }
    }
}
