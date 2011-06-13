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

/* This Filter applies to /web/ssl/* URL Pattern. Checks for the URL scheme(http) and reformulates the URL
   with https and port change to reflect the new HTTP Listener thereby enforcing ssl */

public class ForceSSL implements Filter {

    ServletContext context;
    String sslport;         //SSL port for HTTP listener in Glassfish (Default:8181)

    public ForceSSL() {
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
        
        if(request.getScheme().equalsIgnoreCase("http")){
            String origURL = request.getRequestURL().toString();
            int port = request.getLocalPort();
            String sslURL = httpsURL(origURL);            
            String newURL = portURL(sslURL,port);
            String formData = request.getQueryString();
            if (formData != null) {
                newURL = newURL + "?" + formData;
            }
            response.sendRedirect(newURL);            
        }      
    }
   
    /* Converts http://foo to https://foo */
    private String httpsURL(String origURL) {
        
        int index = origURL.indexOf(":");
        StringBuffer newURL = new StringBuffer(origURL);
        newURL.insert(index, 's');
        return(newURL.toString());
    }

    /* Converts port 8080 to 8181 */
    private String portURL(String sslURL, int port) {
        
        Integer i = Integer.valueOf(port);
        String portused = i.toString();
        
        // save the port in context, later retrieve in ForceClear */
        context.setAttribute("port", portused);
        
        int index = sslURL.indexOf(i.toString());
        StringBuffer newURL = new StringBuffer(sslURL);
        newURL.replace(index, index+4, sslport);
        return(newURL.toString());
    }
}
