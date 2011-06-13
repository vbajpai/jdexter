<%-- 
    Document   : commentreports
    Created on : Mar 10, 2009, 10:08:19 PM
    Author     : rahul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Dexter | Comment Reports</title>
        <link href="/Dexter/_stylesheets/global.css" rel="stylesheet" type="text/css">
        <link href="/Dexter/_stylesheets/power.css" rel="stylesheet" type="text/css">
        <link href="/Dexter/_stylesheets/admin.css" rel="stylesheet" type="text/css">
        <link rel="icon" type="image/png" href="/Dexter/_images/FavIcon.png">
    </head>
    <body>
        <div class="container" id="container">
            <div class="header" id="header">
                <div class="toplinks" id="toplinks">
                    <div class="topleft">
                        <a href="/Dexter/web/user/clear/classic.jsp">
                            <img src="/Dexter/_images/home.png" width="20" height="20" alt="Home">
                        </a>
                    </div>
                    <table class="toptable" summary="this table contains links">
                        <tr>
                            <td>
                                <img src="/Dexter/_images/ProfilePic.jpg" alt="ProfilePic" width="16" height="16">
                            </td>
                            <td>
                                <a class="header toplinks" href="/Dexter/actions/logout.do">Signout</a>
                                <a class="header toplinks" href="/Dexter/actions/idexter.do?page=1">iDexter</a>
                                <a class="header toplinks multiwordlinks" href="/Dexter/web/user/clear/myprofile.jsp">My Profile</a>
                                <a class="header toplinks multiwordlinks" href="/Dexter/web/user/clear/friends.jsp">My Friends</a>
                                <a class="header toplinks" href="/Dexter/web/user/clear/submit.jsp">Submit</a>
                                <span class="toplinks">Settings</span>
                                <a class="header toplinks" href="/Dexter/web/user/clear/about.jsp">About</a>
                            </td>
                        </tr>
                    </table>
                </div>
                <div id="sublinks">
                    <div class="categories">
                        <div class="block">
                            <a class="block header multiwordlinks" href="/Dexter/web/user/ssl/settings.jsp">My Settings</a>
                            <a class="block header multiwordlinks" href="/Dexter/web/user/ssl/editprofile.jsp">Edit Profile</a>
                            <c:if test="${sessionScope.role=='administrator'}">
                                <a class="block header multiwordlinks" href="/Dexter/web/admin/index.jsp">Admin Actions</a>                                
                            </c:if>
                            <span class="multiwordlinks">Moderator Actions</span>
                            <a id="disabledialoginvoker" class="block header multiwordlinks" href="#">Disable Account</a>
                            <a id="deletedialoginvoker" class="block header multiwordlinks" href="#">Delete Account</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="content" id="content">
                <div class="main">

                    <div class="rightpanel" id="rightpanel">
                        <div class="contentheading">
                            <h3>Dashboard</h3>
                        </div>
                        <div class="dynamicdivs" id="dashboard">
                            <ul class="side-menu" id="modlinks">
                                <li>
                                    <a href="/Dexter/web/mod/index.jsp" >Moderator Home</a>
                                </li>
                                <li>
                                    <a href="/Dexter/actions/admin/urlreport.do?orderby=noofreports" >URL Reports</a>
                                </li>
                                <li>
                                    <a href="/Dexter/actions/admin/commentreport.do?orderby=noofreports" class="current">Comment Reports</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="leftpanel" id="leftcolumn">
                        <div class="contentheading idexter itimeframe fright">
                            <div class="timeframe sublinks" id="timeframe">
                                <div class="block">
                                    <c:choose>
                                        <c:when test='${param.orderby == "submissiondate"}'>
                                            <span class="block header">SubmissionDate</span>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="blueblock block header" href="/Dexter/admin/actions/commentreport.do=?orderby=numberofreports">No.ofReports</a>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test='${param.orderby == "noofreports"}'>
                                            <span class="block header">No.ofReports</span>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="blueblock block header" href="/Dexter/admin/actions/commentreport.do=?orderby=submissiondate">SubmissionDate</a>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                        <div class="contentheading">
                            <h3>
                                Moderator Home
                            </h3>
                        </div>
                        <div class="dynamicdivs" id="modhome">
                            <br>
                            <br>
                            <ul>
                                <li>P Comments got reported</li>
                                <li>Q URLs got reported</li>
                            </ul>
                        </div><br>
                        <br>
                        <br>
                    </div>
                </div>
            </div>
            <div class="footer" id="footer">
                Dexter 2008 - Final Year Project for Galgotias College of Engineering and Technology<br>
                Designed By - Vaibhav Bajpai, Rahul Burman, Nupur Dixit, Yadavendra
            </div>
        </div>
    </body>
</html>
