<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">

<%@taglib uri="http://struts.apache.org/tags-html"
          prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>
            Dexter | Friends' Activity
        </title>
        <link href="/Dexter/_stylesheets/global.css" rel="stylesheet" type="text/css">
        <link href="/Dexter/_stylesheets/power.css" rel="stylesheet" type="text/css">
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
                                <a class="header toplinks multiwordlinks" href="/Dexter/actions/myprofile.do">My Profile</a>
                                <span class="toplinks multiwordlinks">My Friends</span>
                                <a class="header toplinks" href="/Dexter/web/user/clear/submit.jsp">Submit</a>
                                <a class="header toplinks" href="/Dexter/web/user/ssl/settings.jsp">Settings</a>
                                <a class="header toplinks" href="/Dexter/web/user/clear/about.jsp">About</a>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="sublinks">
                    <div class="categories">
                        <div class="block">
                            <a class="block header multiwordlinks" href="/Dexter/actions/myfriends.do">My Friends</a>
                            <span class="multiwordlinks">Friend's Activity</span>
                            <a class="block header multiwordlinks" href="/Dexter/actions/friendsrequest.do">Friend's Requests</a>
                            <a class="block header multiwordlinks" href="/Dexter/actions/allfriends.do">All Friends</a>

                        </div>
                    </div>
                </div>
            </div>
            <div class="content" id="content">
                <div class="main">
                    <div class="rightcolumn" id="rightcolumn">
                        <div class="contentheading">
                            <h3>Friends</h3>
                        </div>
                        <div class="friends dynamicdivs" id="friends">
                            <br>
                            <blockquote>
                                <table summary="FriendsTable">
                                    <c:forEach var="friendbean" items="${friends}" varStatus="status">
                                        <c:choose>
                                            <c:when test="${status.count % 2!=0}">
                                                <tr>
                                                    <td>
                                                        <img src="/Dexter/_images/Vaibhav.jpg" alt="Vaibhav">
                                                    </td>
                                                    <td>
                                                        <c:if test="${userRecord.userName==friendbean.userNameFrom.userName}">
                                                        ${friendbean.userNameTo.userName}

                                                        </c:if>
                                                        <c:if test="${userRecord.userName==friendbean.userNameTo.userName}">

                                                            ${friendbean.userNameFrom.userName}

                                                        </c:if>
                                                    </td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td align="right" width="150">
                                                        <img src="/Dexter/_images/Garima.jpg" alt="Garima">
                                                    </td>
                                                    <td>
                                                        <c:if test="${userRecord.userName==friendbean.userNameFrom.userName}">
                                                        ${friendbean.userNameTo.userName}

                                                        </c:if>
                                                        <c:if test="${userRecord.userName==friendbean.userNameTo.userName}">

                                                            ${friendbean.userNameFrom.userName}

                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:otherwise>
                                        </c:choose>

                                    </c:forEach>
                                </table><br>
                                <a href="/Dexter/web/user/clear/allfriends.jsp">more...</a><br>
                                &nbsp;
                            </blockquote>

                        </div><br>
                        <br>
                        <br>
                        <div class="contentheading">
                            <h3>Shouts</h3>
                        </div>
                        <div class="shouts dynamicdivs" id="shouts"><br><br>
                            <blockquote>
                                <html:form action="/actions/shout">
                                    <span class="center">
                                        <h4 class="success">${ShoutFormBean.success}</h4>
                                        <h4 class="error">${ShoutFormBean.error}</h4><br>
                                    </span>
                                    <b>Friend:</b><br>
                                    <html:text styleClass="input" property="userName"></html:text><br><br>
                                    <b>Message:</b><br>
                                    <html:textarea rows="6" cols="50" styleClass="input" property="message"></html:textarea><br><br>
                                    <html:submit styleClass="input" value="Send"></html:submit>
                                    <html:reset styleClass="input" value="Clear"></html:reset><br><br>
                                </html:form>
                            </blockquote>
                        </div><br><br><br>
                    </div>
                    <div class="leftcolumn" id="leftcolumn">
                        <div class="contentheading fleft multiwordlinks">
                            <h3>Friend's Activity</h3>
                        </div>
                        <br><br><br>
                        <div class="dynamicdivs"><br>
                            <div class="center">
                                <ul class="demarcate nobullet">
                                    <c:forEach var="resultbean" items="${friendsactivity}" end="10">
                                        <li>
                                            <div class="fright">
                                                <a href="">
                                                <div class="fleft"><img height="26" weight="26" src="/Dexter/_images/Vaibhav.jpg"></div></a>
                                            </div>
                                            <div class="fleft">
                                                <b><a href="${resultbean.unescapedUrl}">${resultbean.title}</a></b><br><br>
                                                <div class="padleftsmall">
                                                    Voted by <h4 class="headinginline">${resultbean.userFullName}</h4>
                                                    <h6 class="error headinginline">
                                                        <c:choose>
                                                            <c:when test='${resultbean.submissiondate == "0"}'>Today...</c:when>
                                                            <c:otherwise>${resultbean.submissiondate} Days ago...</c:otherwise>
                                                        </c:choose>
                                                    </h6>
                                                </div>
                                            </div><br><br><br>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="footer" id="footer">
                © Dexter 2008 - Final Year Project for Galgotias College of Engineering and Technology<br>
                Designed By - Vaibhav Bajpai, Rahul Burman, Nupur Dixit, Yadavendra
            </div>
        </div>
    </body>
</html>
