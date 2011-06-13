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

public class CheckUser implements Filter {

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
	throws IOException, ServletException {

        HttpServletRequest httprequest = (HttpServletRequest) request;
        HttpSession session = httprequest.getSession();

        if(!UserUtility.userExists(session)){   // User is not logged in!
                HttpServletResponse httpresponse = (HttpServletResponse) response;
                httpresponse.sendRedirect("/Dexter/web/guest/ssl/index.jsp");
        }
    }    

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
	throws IOException, ServletException {

        doBeforeProcessing(request, response);
        // User is Logged in, proceed with the request.
        Throwable problem = null;
        try {
            chain.doFilter(request, response);
        }
        catch(Throwable t) {
            problem = t;
            t.printStackTrace();
        }    
    }    
}
