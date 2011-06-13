package actions;

import beans.PasswordFormBean;
import ejb.UserFacadeRemote;
import entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import utilities.EJBUtility;

public class ChangePasswordAction extends Action {
    
    private final static String RETURN = "return";
    private User userRecord;
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        UserFacadeRemote userFacadeRemote = (UserFacadeRemote) EJBUtility.lookup("UserFacade");
        PasswordFormBean passwordbean = (PasswordFormBean)form;
        HttpSession session = request.getSession();
        userRecord = (User) session.getAttribute("userRecord");

        if(PasswordCorrect(passwordbean.getCurrentpwd())){
            if(NewPasswordMatch(passwordbean.getNewpwd(), passwordbean.getConfirmnewpwd())){
                if(!NewPasswordEmpty(passwordbean.getNewpwd())){

                    userRecord.setPassword(passwordbean.getNewpwd());
                    userFacadeRemote.edit(userRecord);
                    passwordbean.setSuccess("Password Updated!");
                }
                else{   // new password field is empty
                    passwordbean.setError("Enter New Password!");
                }
                
            }
            else{   // passwords do not match
                passwordbean.setError("New Passwords do not match!");
            }
        }
        else{   // password incorrect
            passwordbean.setError("Current Password Incorrect!");
        }     
        passwordbean.setCurrentpwd("");
        passwordbean.setNewpwd("");
        passwordbean.setConfirmnewpwd("");
        
        return mapping.findForward(RETURN);
    }

    private boolean NewPasswordEmpty(String password) {
        if(password.trim().equals("")){
            return(true);
        }
        else{
            return(false);
        }

    }

    private boolean PasswordCorrect(String password) {

        if(userRecord.getPassword().equals(password)){
            return(true);
        }
        else{
            return(false);
        }
    }

    private boolean NewPasswordMatch(String newPassword, String confirmPassword) {
        if(newPassword.trim().equals(confirmPassword.trim())){
            return(true);
        }
        else{
            return(false);
        }
    }
}
