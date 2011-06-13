package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utilities.UserUtility;

public class CheckMod implements Filter {

    private static final String USER = "user";

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException, Exception {

        HttpServletRequest httprequest = (HttpServletRequest) request;
        HttpServletResponse httpresponse = (HttpServletResponse) response;
        HttpSession session = httprequest.getSession();

        if (!UserUtility.userExists(session)) {   // User is not logged in!
            httpresponse.sendRedirect("/Dexter/web/guest/ssl/signin.jsp");
        }
        else{   // User is logged in

            String role = UserUtility.getUserRole(session);

            // user is not administrator or moderator
            if(role.equals(USER)){
                httpresponse.sendRedirect("/Dexter/index.jsp");
            }
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        try {
            doBeforeProcessing(request, response);
        } catch (Exception ex) {
            System.out.println("Exception:"+ex);
        }
        // User is Logged in, proceed with the request.
        Throwable problem = null;
        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
            problem = t;
            t.printStackTrace();
        }
    }
}
