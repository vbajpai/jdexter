package utilities;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EJBUtility {

    public static Object lookup(String EJBReference) throws NamingException {

        Context c = new InitialContext();
        return c.lookup("java:comp/env/ejb/" + EJBReference);
    }
}
