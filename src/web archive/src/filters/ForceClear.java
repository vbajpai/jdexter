package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* This Filter applies to /web/clear/* URL Pattern. Checks for the URL scheme(https) and reformulates the URL
   with http and port change to reflect the new HTTP Listener thereby removing SSL */

public class ForceClear implements Filter {

    ServletContext context;
    String sslport;                 //SSL port for HTTP listener in Glassfish (Default:8181)s

    public ForceClear() {
    }

    public void init(FilterConfig filterConfig) {
        context = filterConfig.getServletContext();
        sslport = filterConfig.getInitParameter("sslport");
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        doBeforeProcessing(req, res);

        Throwable problem = null;

        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
            problem = t;
            t.printStackTrace();
        }
    }

    private void doBeforeProcessing(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        
        if(request.getScheme().equalsIgnoreCase("https")){
            
            String origURL = request.getRequestURL().toString();            
            String sslURL = httpsURL(origURL);            
            String newURL = portURL(sslURL);
            String formData = request.getQueryString();
            if (formData != null) {
               newURL = newURL + "?" + formData;
            }            
            response.sendRedirect(newURL);
        }
    }    

    /* Converts https://foo to http://foo */
    private String httpsURL(String origURL) {

        int index = origURL.indexOf("s");
        StringBuffer newURL = new StringBuffer(origURL);
        newURL.replace(index, index+1, "");
        return(newURL.toString());
    }

    /* Converts port 8181 to 8080 */
    private String portURL(String sslURL) {

        int index = sslURL.indexOf(sslport);
        StringBuffer newURL = new StringBuffer(sslURL);
        String portused = (String) context.getAttribute("port");
        newURL.replace(index, index+4, portused);
        return(newURL.toString());
    }
}
