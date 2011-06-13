package actions;

import beans.PrivacyFormBean;
import ejb.UserPrivacyFacadeRemote;
import ejb.VisibilityValidationFacadeRemote;
import entity.User;
import entity.UserPrivacy;
import entity.VisibilityValidation;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import utilities.EJBUtility;

public class PrivacyAction extends Action {
    
    private final static String SUCCESS = "return";
    private List<UserPrivacy> recordSet;
    private User user;
    private UserPrivacy userPrivacyRecord;
    private List<VisibilityValidation> visibilityrecordSet;

    private VisibilityValidation everyoneOBJ;
    private VisibilityValidation friendsOBJ;
    private VisibilityValidation meOBJ;
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        PrivacyFormBean bean = (PrivacyFormBean)form;
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("userRecord");        

        /* Get Remote Object */
        UserPrivacyFacadeRemote privacyRemote =  (UserPrivacyFacadeRemote) EJBUtility.lookup("UserPrivacyFacade");
        VisibilityValidationFacadeRemote visibilityRemote =  (VisibilityValidationFacadeRemote) EJBUtility.lookup("VisibilityValidationFacade");
        
        /* Get Recordset */
        recordSet = privacyRemote.findAll();
        visibilityrecordSet = visibilityRemote.findAll();

        /* Get VisibilityValidaiton Objects */
        getvisibilityObjects();        

        if (recordSet.isEmpty() || !recordExists()) {    // Record does not exists

            /* Create UserPrivacy Object */
            UserPrivacy userPrivacy = new UserPrivacy(user, getVisibility(bean.getName()),getVisibility(bean.getAge()),
                                                      getVisibility(bean.getGender()), getVisibility(bean.getLocation()),
                                                      getVisibility(bean.getEmail()), getVisibility(bean.getProfilelinks()),
                                                      getVisibility(bean.getShouts()));

            privacyRemote.create(userPrivacy);
        } else {

            /* Update current Record */
            userPrivacyRecord.setAgeVisibility(getVisibility(bean.getAge()));
            userPrivacyRecord.setEmailVisibility(getVisibility(bean.getEmail()));
            userPrivacyRecord.setGenderVisibility(getVisibility(bean.getGender()));
            userPrivacyRecord.setLocationVisibilityVisibility(getVisibility(bean.getLocation()));
            userPrivacyRecord.setNameVisibility(getVisibility(bean.getName()));
            userPrivacyRecord.setProfileLinksVisibility(getVisibility(bean.getProfilelinks()));
            userPrivacyRecord.setShoutsVisibility(getVisibility(bean.getShouts()));

            privacyRemote.edit(userPrivacyRecord);
        }

        bean.setSuccess("Privacy Settings Saved!");
        return mapping.findForward(SUCCESS);
    }

    private VisibilityValidation getVisibility(int privacy){
        if(privacy==1){
            return(everyoneOBJ);
        }else{
            if(privacy==2){
                return(friendsOBJ);
            }else{
                if(privacy==3){
                    return(meOBJ);
                }
            }
        }
        return null;
    }

    private void getvisibilityObjects() {
        /* Iterate the Visibility Recordset */
        Iterator<VisibilityValidation> iterator = visibilityrecordSet.iterator();
        while(iterator.hasNext()){
            VisibilityValidation record = iterator.next();
            if(record.getVisibilityName().equals("everyone")){
                everyoneOBJ = record;
            }else{
                if(record.getVisibilityName().equals("friends")){
                    friendsOBJ = record;
                }
                else{
                    if(record.getVisibilityName().equals("me")){
                        meOBJ = record;
                    }
                }
            }
        }
    }

    private boolean recordExists() {

        Iterator<UserPrivacy> iterator = recordSet.iterator();
        while (iterator.hasNext()) {
            UserPrivacy record = iterator.next();
            if (record.getUser().equals(user)) {
                userPrivacyRecord = record;
                return (true);
            }
        }
        return (false);
    }
}
