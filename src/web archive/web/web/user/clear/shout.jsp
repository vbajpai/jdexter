<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c"%>
<%@taglib uri="http://struts.apache.org/tags-html"
          prefix="html" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Dexter | Shouts</title>
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
                                <span class="multiwordlinks toplinks">My Profile</span>
                                <a class="header toplinks multiwordlinks" href="/Dexter/actions/myfriends.do">My Friends</a>
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
                            <a class="block header multiwordlinks" href="/Dexter/actions/myprofile.do">My Profile</a>
                            <a class="block header" href="/Dexter/actions/displayfavorite.do">Favorites</a>
                            <a class="block header multiwordlinks" href="/Dexter/actions/recentactivity.do">Recent Activity</a>
                            <span class="multiwordlinks">Shouts</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="content" id="content">
                <div class="main">
                    <div class="contentheading">
                        <h3>All Shouts</h3>
                    </div>
                    <div class="dynamicdivs" id="friendsrequest"><br><br>
                        <div class="center">
                            <span class="center">
                                <h4 class="success">${ShoutFormBean.success}</h4>
                                <h4 class="error">${ShoutFormBean.error}</h4><br>
                            </span>
                            <div class="rightcolumn">
                                <ul class="demarcate nobullet">
                                    <c:forEach begin="1" step="2" var="shoutBean" items="${shouts}" >
                                        <li>
                                            <div class="fright">
                                                <a href="">
                                                <div class="fleft"><img height="26" weight="26" src="/Dexter/_images/Vaibhav.jpg"></div></a>
                                                <div class="fright padleftsmall">
                                                    <h5>sent by - <br></h5>
                                                    <div class="padleftsmall">
                                                        <h4 class="headinginline">${shoutBean.userFrom.firstName} ${shoutBean.userFrom.lastName}</h4>
                                                        <h5 class="headinginline">(${shoutBean.userFrom.userName})</h5>
                                                        <h6 class="error">
                                                            <c:choose>
                                                                <c:when test='${shoutBean.shoutTimeString == "0"}'>Today...</c:when>
                                                                <c:otherwise>${shoutBean.shoutTimeString} Days ago...</c:otherwise>
                                                            </c:choose>
                                                        </h6>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="fleft">
                                                <b>"${shoutBean.shout}"</b><br><br>
                                                <form action="/Dexter/actions/shout.do" method="post">
                                                    <input type="hidden" name="id" value="${shoutBean.id}">
                                                    <input type="submit" class="input" value="delete" name="editshout">
                                                    <c:choose>
                                                        <c:when test='${shoutBean.ifBlocked == false}'>
                                                            <input type="submit" class="input" value="block" name="editshout">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input type="submit" class="input" value="unblock" name="editshout">
                                                        </c:otherwise>
                                                    </c:choose>
                                                </form>
                                            </div><br>
                                        </li><br><br>
                                    </c:forEach>
                                    <li></li><br><br>
                                </ul>
                            </div>
                            <div class="leftcolumn">
                                <ul class="demarcate nobullet">
                                    <c:forEach begin="0" step="2" var="shoutBean" items="${shouts}">
                                        <li>
                                            <div class="fright">
                                                <a href="">
                                                <div class="fleft"><img height="26" weight="26" src="/Dexter/_images/Vaibhav.jpg"></div></a>
                                                <div class="fright padleftsmall">
                                                    <h5>sent by - <br></h5>
                                                    <div class="padleftsmall">
                                                        <h4 class="headinginline">${shoutBean.userFrom.firstName} ${shoutBean.userFrom.lastName}</h4>
                                                        <h5 class="headinginline">(${shoutBean.userFrom.userName})</h5>

                                                        <h6 class="error">
                                                            <c:choose>
                                                                <c:when test='${shoutBean.shoutTimeString == "0"}'>Today...</c:when>
                                                                <c:otherwise>${shoutBean.shoutTimeString} Days ago...</c:otherwise>
                                                            </c:choose>
                                                        </h6>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="fleft">
                                                <b>"${shoutBean.shout}"</b><br><br>
                                                <form action="/Dexter/actions/shout.do" method="post">
                                                    <input type="hidden" name="id" value="${shoutBean.id}">
                                                    <input type="submit" class="input" value="delete" name="editshout">
                                                    <c:choose>
                                                        <c:when test='${shoutBean.ifBlocked == false}'>
                                                            <input type="submit" class="input" value="block" name="editshout">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input type="submit" class="input" value="unblock" name="editshout">
                                                        </c:otherwise>
                                                    </c:choose>

                                                </form>
                                            </div><br>
                                        </li><br><br>
                                    </c:forEach>
                                    <li></li><br><br>
                                </ul>
                            </div>
                            <div class="clear"></div>
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
