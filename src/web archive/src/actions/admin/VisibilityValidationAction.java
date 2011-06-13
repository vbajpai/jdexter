/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package actions.admin;

import beans.admin.VisibilityValidationFormBean;
import ejb.VisibilityValidationFacadeRemote;
import entity.VisibilityValidation;
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
public class VisibilityValidationAction extends org.apache.struts.action.Action {
    
    private final static String SUCCESS = "return";
    VisibilityValidationFacadeRemote visiblityValidationFacadeRemote;
    VisibilityValidationFormBean visibilityValidationFormBean;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
            visibilityValidationFormBean= (VisibilityValidationFormBean) form;
            /*Get Remote Object*/
            visiblityValidationFacadeRemote =  (VisibilityValidationFacadeRemote) EJBUtility.lookup("VisibilityValidationFacade");
            /*Create Visibility*/
            if(visibilityValidationFormBean.getCreateVisibility()!=null)
                createVisibility();
            /*Remove Visibility*/
            if(visibilityValidationFormBean.getRemoveVisibility()!=null)
                removeVisibility();
            /*Update Visibility */
            HttpSession session = request.getSession();
            session.setAttribute("VisibilityBean", visiblityValidationFacadeRemote.findAll());
        return mapping.findForward(SUCCESS);
    }

    private void createVisibility() {
        if(visibilityValidationFormBean.getCreateVisibility().trim().length()==0){
            visibilityValidationFormBean.setAddError("VC Name not Provided");
        }else{
            if(visiblityValidationFacadeRemote.findByVisibilityControlName(visibilityValidationFormBean.getCreateVisibility())!=null)
            {
                visibilityValidationFormBean.setAddError("Visibility control exists");
            }
            else
            {
                visiblityValidationFacadeRemote.create(new VisibilityValidation(new Date(), true, visibilityValidationFormBean.getCreateVisibility()));
                visibilityValidationFormBean.setAddSuccess("visibility control Created");
                visibilityValidationFormBean.setCreateVisibility(null);
            }
        }
    }

    private void removeVisibility() {
        VisibilityValidation visibilityValidation=visiblityValidationFacadeRemote.findByVisibilityControlName(visibilityValidationFormBean.getRemoveVisibility());
        if(visibilityValidation==null)
            visibilityValidationFormBean.setRemoveError("VC couldn't be removed");
        else
        {
            visibilityValidationFormBean.setRemoveSuccess("VC successfully removed");
            visiblityValidationFacadeRemote.remove(visibilityValidation);
        }
    }
}
