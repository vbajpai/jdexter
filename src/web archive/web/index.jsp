<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Redirects to /web/user/clear/classic.jsp. if user is not logged in, it redirects back to /web/guest/ssl/index.jsp -->

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:forward page="/web/user/clear/classic.jsp"></jsp:forward>
    </body>
</html>
