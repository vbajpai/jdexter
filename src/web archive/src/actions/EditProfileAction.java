package actions;

import beans.SignupFormBean;
import beans.utilities.SignupFormBeanUtility;

import entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

public class EditProfileAction extends Action {

    private final static String RETURN = "return";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        /* Create SignupFormBeanUtility Object */
        SignupFormBeanUtility beanutil = new SignupFormBeanUtility();

        /* Get current UserRecord */
        HttpSession session = request.getSession();
        User userRecord = (User) session.getAttribute("userRecord");

        SignupFormBean formbean = (SignupFormBean) form;
        formbean.setSuccess("");  // clear success field

        if (beanutil.ifMandatoryNotEmpty(formbean) &&
                beanutil.ifNotMisformed(formbean)) {      //Validation Succeeds

            if (beanutil.commitToDB(formbean, userRecord)) {       // Database Transaction Successful.
                formbean.setSuccess("Profile Updated!");
                return mapping.findForward(RETURN);
            } else {
                throw new Exception("Cannot Write FormBean to Database!");
            }            
        } else {   //Validation Fails
            return mapping.findForward(RETURN);
        }
    }
}
