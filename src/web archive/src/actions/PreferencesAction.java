package actions;

import beans.PreferenceFormBean;
import ejb.UserPreferenceFacadeRemote;
import entity.User;
import entity.UserPreference;
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

public class PreferencesAction extends Action {

    private final static String SUCCESS = "return";
    List<UserPreference> recordSet;
    User user;
    private UserPreference userPreferenceRecord;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        PreferenceFormBean bean = (PreferenceFormBean) form;
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("userRecord");

        boolean commentSort = getCommentSort(bean.getCommentSort());
        boolean commentReplyCollapsed = getCommentReplyCollapsed(bean.getCommentReplyCollapsed());


        /* Get Remote Object*/
        UserPreferenceFacadeRemote userPreferenceRemote = (UserPreferenceFacadeRemote) EJBUtility.lookup("UserPreferenceFacade");
        /* Get Recordset */
        recordSet = userPreferenceRemote.findAll();
        if (recordSet.isEmpty() || !recordExists()) {    // Record does not exists

            /* Create UserPreference Object */
            UserPreference userPreference = new UserPreference(user, commentSort,
                    bean.getCommentThreshold(), bean.getCommentPageSize(),
                    commentReplyCollapsed, true);
            
            userPreferenceRemote.create(userPreference);
        } else {

            /* Update current Record */
            userPreferenceRecord.setDefaultCommentSort(commentSort);
            userPreferenceRecord.setCommentThreshold(bean.getCommentThreshold());
            userPreferenceRecord.setCommentPageSize(bean.getCommentPageSize());
            userPreferenceRecord.setCommentReplyCollapsed(commentReplyCollapsed);
            
            userPreferenceRemote.edit(userPreferenceRecord);
        }
        
        bean.setSuccess("Preferences Saved!");
        return mapping.findForward(SUCCESS);
    }

    private boolean recordExists() {

        Iterator<UserPreference> iterator = recordSet.iterator();
        while (iterator.hasNext()) {
            UserPreference record = iterator.next();
            if (record.getUser().equals(user)) {
                userPreferenceRecord = record;
                return (true);
            }
        }
        return (false);
    }

    private boolean getCommentReplyCollapsed(int commentReplyCollapsed) {
        if (commentReplyCollapsed == 0) {
            return (false);
        } else {
            return (true);
        }
    }

    private boolean getCommentSort(int commentSort) {
        if (commentSort == 0) {
            return (false);
        } else {
            return (true);
        }
    }
}
