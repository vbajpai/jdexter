package web.user.clear;

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>
            Dexter | All Friends
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
                            <a class="block header multiwordlinks" href="/Dexter/actions/friendsactivity.do">Friend's Activity</a>
                            <a class="block header multiwordlinks" href="/Dexter/actions/friendsrequest.do">Friend's Request</a>
                            <span class="multiwordlinks">All Friends</span>
                            
                        </div>
                    </div>
                </div>
            </div>
            <div class="content" id="content">
                <div class="main">
                    <div class="contentheading">
                        <h3>
                            All Friends
                        </h3>
                    </div>
                    <div class="dynamicdivs" id="allfriends">
                        <br>
                        <br>
                        <div align="center">
                            <form action="">
                                Search Friends <input type="text" class="input"> <input type="submit" class="input" value="Search">
                            </form>
                        </div><br>
                        <br>
                         <div class="friends" id="friends"><br><br>
                            <blockquote>
                                <table width="100%" border="0" summary="FriendsTable">
                                    <c:forEach var="friendbean" items="${friends}" varStatus="status">
                                        <c:choose>
                                            <c:when test="${status.count % 4==1}">
                                                <tr>
                                                    <td align="right" >
                                                        <img src="/Dexter/_images/Vaibhav.jpg" alt="Vaibhav">
                                                    </td>
                                                    <td>
                                                        <c:if test="${userRecord.userName==friendbean.userNameFrom.userName}">

                                                                <h3 class="headinginline">${friendbean.userNameTo.firstName} ${friendbean.userNameTo.lastName}</h3>
                                                                <h5 class="headinginline">(${friendbean.userNameTo.userName})</h5>

                                                        </c:if>
                                                        <c:if test="${userRecord.userName==friendbean.userNameTo.userName}">
                                                           <h3 class="headinginline"> ${friendbean.userNameFrom.firstName} ${friendbean.userNameFrom.lastName}</h3>
                                                            <h5 class="headinginline">(${friendbean.userNameFrom.userName})</h5>

                                                        </c:if>
                                                    </td>
                                             </c:when>
                                            <c:when test="${status.count % 4==2}">
                                                    <td align="right" >
                                                        <img src="/Dexter/_images/Vaibhav.jpg" alt="Vaibhav">
                                                    </td>
                                                    <td>
                                                        <c:if test="${userRecord.userName==friendbean.userNameFrom.userName}">

                                                                <h3 class="headinginline">${friendbean.userNameTo.firstName} ${friendbean.userNameTo.lastName}</h3>
                                                                <h5 class="headinginline">(${friendbean.userNameTo.userName})</h5>

                                                        </c:if>
                                                        <c:if test="${userRecord.userName==friendbean.userNameTo.userName}">
                                                            <h3 class="headinginline">${friendbean.userNameFrom.firstName} ${friendbean.userNameFrom.lastName}</h3>
                                                            <h5 class="headinginline">(${friendbean.userNameFrom.userName})</h5>

                                                        </c:if>
                                                    </td>
                                             </c:when>
                                            <c:when test="${status.count % 4==3}">
                                                    <td align="right" >
                                                        <img src="/Dexter/_images/Vaibhav.jpg" alt="Vaibhav">
                                                    </td>
                                                    <td>
                                                        <c:if test="${userRecord.userName==friendbean.userNameFrom.userName}">

                                                                ${friendbean.userNameTo.firstName} ${friendbean.userNameTo.lastName}<br>
                                                                ${friendbean.userNameTo.userName}

                                                        </c:if>
                                                        <c:if test="${userRecord.userName==friendbean.userNameTo.userName}">
                                                            ${friendbean.userNameFrom.firstName} ${friendbean.userNameFrom.lastName}<br>
                                                            ${friendbean.userNameFrom.userName}

                                                        </c:if>
                                                    </td>
                                             </c:when>

                                             <c:when test="${status.count % 4==0}">
                                                    <td align="right" >
                                                        <img src="/Dexter/_images/Garima.jpg" alt="Garima">
                                                    </td>
                                                    <td>
                                                        <c:if test="${userRecord.userName==friendbean.userNameFrom.userName}">

                                                                ${friendbean.userNameTo.firstName} ${friendbean.userNameTo.lastName}<br>
                                                                ${friendbean.userNameTo.userName}

                                                        </c:if>
                                                        <c:if test="${userRecord.userName==friendbean.userNameTo.userName}">
                                                            ${friendbean.userNameFrom.firstName} ${friendbean.userNameFrom.lastName}<br>
                                                            ${friendbean.userNameFrom.userName}

                                                        </c:if>
                                                    </td>
                                                </tr>
                                              </c:when>
                                       </c:choose>

                                    </c:forEach>
                                </table><br>
                                
                            </blockquote>

                         </div>
                         <br>
                        <br>
                        <br>
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
