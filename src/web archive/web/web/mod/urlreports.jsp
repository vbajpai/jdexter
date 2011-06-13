<%-- 
    Document   : urlreports
    Created on : Mar 10, 2009, 9:50:43 PM
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
        <title>Dexter | URL Reports</title>
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
                                    <a href="/Dexter/actions/admin/urlreport.do?orderby=noofreports" class="current">URL Reports</a>
                                </li>
                                <li>
                                    <a href="/Dexter/actions/admin/commentreport.do?orderby=noofreports">Comment Reports</a>
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
                                            <a class="blueblock block header" href="/Dexter/actions/admin/urlreport.do?orderby=submissiondate">SubmissionDate</a>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test='${param.orderby == "noofreports"}'>
                                            <span class="block header">No.ofReports</span>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="blueblock block header" href="/Dexter/actions/admin/urlreport.do?orderby=noofreports">No.ofReports</a>
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
                             <ul class="nobullet demarcate">
                                <c:forEach var="resultbean" items="${reportedurllist}">
                                    <li>
                                        <div class="fright">
                                            <blockquote>
                                                <a href="">
                                                <div class="fleft"><img height="44" weight="44" src="/Dexter/_images/Vaibhav.jpg"></div></a>
                                                <div class="fright padleftsmall">
                                                    <h5>submitted by - <br></h5>
                                                    <div class="padleftsmall">
                                                        <h4>${resultbean.userFullName}</h4>
                                                        <h6 class="error">
                                                            <c:choose>
                                                                <c:when test='${resultbean.submissiondate == "0"}'>Today...</c:when>
                                                                <c:otherwise>${resultbean.submissiondate} Days ago...</c:otherwise>
                                                            </c:choose>
                                                        </h6>
                                                    </div>
                                                </div>
                                            </blockquote>
                                        </div>
                                        <div class="fleft">
                                            <div>
                                                <c:choose>
                                                    <c:when test="${resultbean.ifmodded == true}">
                                                        <c:choose>
                                                            <c:when test="${resultbean.ifvotedup == true}">
                                                                <a href="/Dexter/actions/vote.do?page=${param.page}&mod=up&ID=${resultbean.ID}&category=${param.category}&channel=${param.channel}&timeframe=${param.timeframe}">
                                                                    <img src="/Dexter/_images/standardup.png" alt="UP" width="29" height="27">
                                                                </a>
                                                                <a href="/Dexter/actions/vote.do?page=${param.page}&mod=down&ID=${resultbean.ID}&category=${param.category}&channel=${param.channel}&timeframe=${param.timeframe}">
                                                                    <img src="/Dexter/_images/greydown.png" alt="DOWN" width="29" height="27">
                                                                </a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <a href="/Dexter/actions/vote.do?page=${param.page}&mod=up&ID=${resultbean.ID}&category=${param.category}&channel=${param.channel}&timeframe=${param.timeframe}">
                                                                    <img src="/Dexter/_images/greyup.png" alt="UP" width="29" height="27">
                                                                </a>
                                                                <a href="/Dexter/actions/vote.do?page=${param.page}&mod=down&ID=${resultbean.ID}&category=${param.category}&channel=${param.channel}&timeframe=${param.timeframe}">
                                                                    <img src="/Dexter/_images/standarddown.png" alt="DOWN" width="29" height="27">
                                                                </a>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a href="/Dexter/actions/vote.do?page=${param.page}&mod=up&ID=${resultbean.ID}&category=${param.category}&channel=${param.channel}&timeframe=${param.timeframe}">
                                                            <img src="/Dexter/_images/greyup.png" alt="UP" width="29" height="27">
                                                        </a>
                                                        <a href="/Dexter/actions/vote.do?page=${param.page}&mod=down&ID=${resultbean.ID}&category=${param.category}&channel=${param.channel}&timeframe=${param.timeframe}">
                                                            <img src="/Dexter/_images/greydown.png" alt="DOWN" width="29" height="27">
                                                        </a>
                                                    </c:otherwise>
                                                </c:choose>
                                                <span class="padleft urltitle">
                                                    <b><a href="${resultbean.unescapedUrl}">
                                                            ${resultbean.title}
                                                        </a>
                                                    </b>
                                                </span></<br><br>
                                            </div>
                                            <div class="fleft padleft">
                                                <h3>${resultbean.voteUp - resultbean.voteDown}</h3>
                                            </div>
                                            <div class="fright nostyle">
                                                <blockquote class="notop">
                                                    <blockquote class="notop fw urlcontent">
                                                        <b>${resultbean.visibleUrl} - </b>
                                                        ${resultbean.content}<br><br>
                                                        <span class="header">
                                                            <a class="multiwordlinks" href="${resultbean.cacheUrl}"><b>Cached Webpage</b></a> |
                                                            
                                                        </span>
                                                    </blockquote>
                                                </blockquote>
                                            </div>
                                        </div>
                                        <div class="clear"></div>
                                    </li>
                                </c:forEach>
                                <li>
                                    <div class="fleft">
                                        <c:if test="${param.page!=1}">
                                            <a href="/Dexter/actions/idexter.do?page=${param.page-1}">
                                            </c:if>
                                            <button class="input">Previous</button>
                                        </a>
                                    </div>
                                    <div class="fright">
                                        <a href="/Dexter/actions/idexter.do?page=${param.page+1}">
                                            <button class="input">Next</button>
                                        </a>
                                    </div>
                                    <div class="clear"></div>
                                </li>
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
